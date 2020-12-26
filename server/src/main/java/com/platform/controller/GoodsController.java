package com.platform.controller;

import com.platform.domain.Goods;
import com.platform.domain.GoodsInfo;
import com.platform.domain.JwtResult;
import com.platform.domain.Menu;
import com.platform.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @GetMapping("getGoods")
    public JwtResult<GoodsInfo> getGoods(GoodsInfo goodsInfo){
        JwtResult<GoodsInfo> result = new JwtResult<>();
        if(!StringUtils.isEmpty(goodsService.getGoods(goodsInfo))){
            result.setCode(200);
            result.setData(goodsService.getGoods(goodsInfo));
            result.setMsg("请求成功");
        }else{
            result.setCode(304);
            result.setMsg("请求失败");
        }
        return result;
    }

    @GetMapping("addGoods")
    public JwtResult<String> addGoods(Goods goods){
        JwtResult<String> result = new JwtResult<>();
        if(goodsService.addGoods(goods)){
            result.setCode(200);
            result.setMsg("增加商品成功");
        }else{
            result.setCode(304);
            result.setMsg("增加商品失败");
        }
        return result;
    }

    @GetMapping("updateGoods")
    public JwtResult<String> updateGoods(Goods goods){
        JwtResult<String> result = new JwtResult<>();
        if(goodsService.updateGoods(goods)){
            result.setCode(200);
            result.setMsg("更新商品成功");
        }else{
            result.setCode(304);
            result.setMsg("更新商品失败");
        }
        return result;
    }

    @GetMapping("deleteGoods")
    public JwtResult<String> delete(int id){
        JwtResult<String> result = new JwtResult<>();
        if(goodsService.deleteGoods(id)){
            result.setCode(200);
            result.setMsg("删除商品成功");
        }else{
            result.setCode(304);
            result.setMsg("删除商品失败");
        }
        return result;
    }
}
