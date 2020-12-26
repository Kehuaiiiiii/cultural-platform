package com.platform.service;

import com.platform.DAO.Goods;
import com.platform.DAO.GoodsInfo;

public interface IGoodsService {
    GoodsInfo getGoods(GoodsInfo goodsInfo);

    Boolean addGoods(Goods goods);

    Boolean updateGoods(Goods goods);

    Boolean deleteGoods(int id);
}
