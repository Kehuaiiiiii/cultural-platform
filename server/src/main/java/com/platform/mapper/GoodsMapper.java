package com.platform.mapper;

import com.platform.DAO.Goods;
import com.platform.DAO.GoodsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface GoodsMapper {

    List<Goods> getGoods(@Param("uid")int uid,@Param("rid") int rid, @Param("name") String name, @Param("min")int min,@Param("max")int max);

    int getTotal(@Param("uid")int uid,@Param("rid") int rid, @Param("name") String name);

    Integer addGoods(Goods goods);

    Integer updateGoods(Goods goods);

    Integer deleteGoods(int id);

    List<Goods> getVerGoods(@Param("min")int min,@Param("max")int max);

    int getVerTotal();

    Goods getGoodsById(@Param("id") int id);
}
