package com.platform.service.impl;

import com.platform.domain.Goods;
import com.platform.domain.GoodsInfo;
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
    public GoodsInfo getGoods(GoodsInfo goodsInfo) {
        GoodsInfo goods=new GoodsInfo();
        int page=goodsInfo.getPagenum();
        int size=goodsInfo.getPagesize();
        goods.setPagenum(page);
        goods.setPagesize(size);
        int min=(page-1)*size;
        int max=page*size;
        String name=goodsInfo.getName();
        goods.setTotal(goodsMapper.getTotal(name));
        goods.setGoods(goodsMapper.getGoods(name,min,max));
        /*if(goodsInfo.getQuery()!=null) {
            Goods good=goodsInfo.getQuery();
            goods.setTotal(goodsMapper.getTotal(good));
            goods.setGoods(goodsMapper.getGoods(good,min,max));
        }else{
            goods.setTotal(goodsMapper.getAllTotal());
            goods.setGoods(goodsMapper.getAllGoods(min,max));
        }*/
        return goods;
    }

    @Override
    public Boolean addGoods(Goods goods) {
        String Nowtime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        Date time=Date.valueOf(Nowtime);
        goods.setAdd_time(time);
        goods.setUpd_time(time);
        goods.setVersion_id(1);
        goods.setGoods_state(1);
        return goodsMapper.addGoods(goods);
    }

    @Override
    public Boolean updateGoods(Goods goods) {
        String Nowtime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        Date time=Date.valueOf(Nowtime);
        goods.setUpd_time(time);
        int v=goods.getVersion_id();
        v++;
        goods.setVersion_id(v);
        return goodsMapper.updateGoods(goods);
    }

    @Override
    public Boolean deleteGoods(int id) {
        return goodsMapper.deleteGoods(id);
    }


}
