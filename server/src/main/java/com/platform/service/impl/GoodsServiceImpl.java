package com.platform.service.impl;

import com.platform.DAO.Goods;
import com.platform.DAO.GoodsInfo;
import com.platform.mapper.GoodsMapper;
import com.platform.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Service
public class GoodsServiceImpl implements IGoodsService{

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public GoodsInfo getGoods(int uid,int rid,GoodsInfo goods) {
        int page=goods.getPagenum();
        int size=goods.getPagesize();
        int min=(page-1)*size;
        int max=page*size;
        String name=goods.getName();
        goods.setTotal(goodsMapper.getTotal(uid,rid,name));
        goods.setGoods(goodsMapper.getGoods(uid,rid,name,min,max));
        return goods;
    }



    @Override
    public Boolean addGoods(Goods goods) {
        String Nowtime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        Date time=Date.valueOf(Nowtime);
        goods.setCreated_time(time);
        return goodsMapper.addGoods(goods);
    }

    @Override
    public Boolean updateGoods(Goods goods) {
        return goodsMapper.updateGoods(goods);
    }

    @Override
    public Boolean deleteGoods(int id) {
        return goodsMapper.deleteGoods(id);
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
