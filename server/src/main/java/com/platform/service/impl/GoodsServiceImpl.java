package com.platform.service.impl;

import com.platform.DAO.Goods;
import com.platform.DAO.GoodsInfo;
import com.platform.mapper.GoodsMapper;
import com.platform.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class GoodsServiceImpl implements IGoodsService{

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public GoodsInfo getGoods(int uid,int rid,GoodsInfo goods) {
        int page=goods.getPagenum();
        int size=goods.getPagesize();
        int min=(page-1)*size;
        int max=size;
        String name=goods.getName();
        goods.setTotal(goodsMapper.getTotal(uid,rid,name));
        goods.setGoods(goodsMapper.getGoods(uid,rid,name,min,max));
        return goods;
    }



    @Override
    public Boolean addGoods(Goods goods) {
        Date dt = new Date();
        SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        goods.setCreated_time(currentTime);
        Integer res = goodsMapper.addGoods(goods);
        System.out.println("增加了："+res+" 条记录");
        return res > 0;
    }

    @Override
    public Boolean updateGoods(Goods goods) {
        Integer res = goodsMapper.updateGoods(goods);
        System.out.println("更新了："+res+" 条记录");
        return res > 0;
    }

    @Override
    public Boolean deleteGoods(int id) {
        Integer res = goodsMapper.deleteGoods(id);
        System.out.println("删除了："+res+" 条记录");
        return res > 0;
    }

    @Override
    public GoodsInfo getVerGoods(GoodsInfo goodsInfo) {
        int page=goodsInfo.getPagenum();
        int size=goodsInfo.getPagesize();
        int min=(page-1)*size;
        int max=page*size;
        goodsInfo.setTotal(goodsMapper.getVerTotal());
        goodsInfo.setGoods(goodsMapper.getVerGoods(min,max));
        return goodsInfo;
    }
}
