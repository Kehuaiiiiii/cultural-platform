package com.platform.service.impl;

import com.platform.domain.Goods;
import com.platform.domain.GoodsInfo;
import com.platform.mapper.GoodsMapper;
import com.platform.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService{

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public GoodsInfo getAllGoods(GoodsInfo goodsInfo) {
        GoodsInfo goods=new GoodsInfo();
        int page=goodsInfo.getPagenum();
        int size=goodsInfo.getPagesize();
        int min=(page-1)*size;
        int max=page*size-1;
        if(goodsInfo.getGood()!=null) {
            Goods good=goodsInfo.getGood();
            goods.setTotal(goodsMapper.getTotal(good));
            goods.setGoods(goodsMapper.getGoods(good,min,max));
        }else{
            goods.setTotal(goodsMapper.getAllTotal());
            goods.setGoods(goodsMapper.getAllGoods(min,max));
        }
        return goods;
    }


}
