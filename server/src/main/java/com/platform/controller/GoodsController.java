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
    public HttpResult<GoodsInfo> getGoods(GoodsInfo goodsInfo) {
        HttpResult<GoodsInfo> result = new HttpResult<>();
        if (!StringUtils.isEmpty(goodsService.getAllGoods(goodsInfo))) {
            result.setCode(200);
            result.setData(goodsService.getAllGoods(goodsInfo));
            result.setMsg("请求成功");
        } else {
            result.setCode(304);
            result.setMsg("请求失败");
        }
        return result;
    }

    @GetMapping("addGoods")
    public HttpResult<String> addGoods(Goods goods) {
        HttpResult<String> result = new HttpResult<>();
        if (1 == 1) {//goodsService.addGoods(goods)
            result.setCode(200);
            result.setMsg("增加商品成功");
        } else {
            result.setCode(304);
            result.setMsg("增加商品失败");
        }
        return result;
    }
}
