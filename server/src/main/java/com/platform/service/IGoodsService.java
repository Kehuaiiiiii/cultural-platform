package com.platform.service;

import com.platform.domain.Goods;
import com.platform.domain.GoodsInfo;

import java.util.List;

public interface IGoodsService {
    GoodsInfo getGoods(GoodsInfo goodsInfo);

    Boolean addGoods(Goods goods);

    Boolean updateGoods(Goods goods);

    Boolean deleteGoods(int id);
}
