package com.platform.mapper;

import com.platform.DAO.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface GoodsMapper {

    List<Goods> getGoods(@Param("name") String name, @Param("min")int min,@Param("max")int max);

    int getTotal(@Param("name") String name);

    //int getAllTotal();

    //List<Goods> getAllGoods(@Param("min")int min,@Param("max")int max);

    Boolean addGoods(Goods goods);

    Boolean updateGoods(Goods goods);

    Boolean deleteGoods(int id);
}
