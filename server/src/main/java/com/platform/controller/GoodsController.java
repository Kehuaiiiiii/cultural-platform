package com.platform.controller;


import com.platform.DAO.Goods;
import com.platform.DAO.GoodsInfo;
import com.platform.VO.HttpResult;
import com.platform.service.IGoodsService;
import com.platform.util.HttpContextUtil;
import com.platform.util.HttpResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    /**
     * 获得商品，商家只能获得自己店铺的商品
     *
     * @param request
     * @param goodsInfo
     * @return
     */
    @GetMapping("getGoods")
    public HttpResult<GoodsInfo> getGoods(HttpServletRequest request, GoodsInfo goodsInfo) {
        int rid = HttpContextUtil.getRid(request);
        int uid = HttpContextUtil.getUid(request);
        GoodsInfo goods = goodsService.getGoods(uid, rid, goodsInfo);
        if (StringUtils.isEmpty(goods))
            return HttpResultUtil.error(301, "获取商品失败");
        return HttpResultUtil.success(goods);
    }

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    @GetMapping("addGoods")
    public HttpResult<String> addGoods(HttpServletRequest request, Goods goods) {
        int uid = HttpContextUtil.getUid(request);
        goods.setUid(uid);
        if (!goodsService.addGoods(goods))
            return HttpResultUtil.error(303, "添加商品失败");
        return HttpResultUtil.success("添加商品成功");
    }


    /**
     * 更新商品
     *
     * @param goods
     * @return
     */
    @GetMapping("updateGoods")
    public HttpResult<String> updateGoods(Goods goods) {
        if (!goodsService.updateGoods(goods))
            return HttpResultUtil.error(304, "更新商品失败");
        return HttpResultUtil.success("更新商品成功");
    }

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @GetMapping("deleteGoods")
    public HttpResult<String> delete(int id) {
        if (!goodsService.deleteGoods(id))
            return HttpResultUtil.error(303, "删除商品失败");
        return HttpResultUtil.success("删除商品成功");
    }

    /**
     * 获得待审核的商品
     *
     * @param goodsInfo
     * @return GoodsInfo
     */
    @GetMapping("getVerGoods")
    public HttpResult<GoodsInfo> getVerGoods(GoodsInfo goodsInfo) {
        GoodsInfo goods = goodsService.getVerGoods(goodsInfo);
        if (StringUtils.isEmpty(goods))
            return HttpResultUtil.error(301, "获取审核商品失败");
        return HttpResultUtil.success(goods);
    }
}
