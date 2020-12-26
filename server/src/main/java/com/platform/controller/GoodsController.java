package com.platform.controller;


import com.platform.DAO.Goods;
import com.platform.DAO.GoodsInfo;
import com.platform.VO.HttpResult;
import com.platform.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @GetMapping("getGoods")
    public HttpResult<GoodsInfo> getGoods(GoodsInfo goodsInfo){
        HttpResult<GoodsInfo> result = new HttpResult<>();
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
    public HttpResult<String> addGoods(Goods goods){
        HttpResult<String> result = new HttpResult<>();
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
    public HttpResult<String> updateGoods(Goods goods){
        HttpResult<String> result = new HttpResult<>();
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
    public HttpResult<String> delete(int id){
        HttpResult<String> result = new HttpResult<>();
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
