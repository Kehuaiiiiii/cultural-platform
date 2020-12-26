package com.platform.mapper;

import com.platform.DAO.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface GoodsMapper {

    List<Goods> getGoods(Goods goods, @Param("min")int min,@Param("max")int max);

    int getTotal(Goods goods);

    int getAllTotal();

    List<Goods> getAllGoods(@Param("min")int min,@Param("max")int max);
}
