package com.platform.controller;

import com.platform.DAO.Orders;
import com.platform.DAO.OrdersInfo;
import com.platform.VO.HttpResult;
import com.platform.service.IOrderService;
import com.platform.util.HttpContextUtil;
import com.platform.util.HttpResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 获取订单信息，商家和买家只能获得属于自己的订单
     * @param request
     * @param orders
     * @return
     */
    @GetMapping("getOrder")
    public HttpResult<OrdersInfo> getOrderInfo(HttpServletRequest request, OrdersInfo orders){
        int uid=HttpContextUtil.getUid(request);
        OrdersInfo ordersInfo=orderService.getOrderInfo(uid,orders);
        if(StringUtils.isEmpty(ordersInfo))
            return HttpResultUtil.error(301, "获取订单失败");
        return HttpResultUtil.success(ordersInfo);
    }

    /**
     * 更新订单信息
     * @param orders
     * @return
     */
    @GetMapping("updateOrder")
    public HttpResult<String> updateOrder(Orders orders){
        if(!orderService.updateOrder(orders))
            return HttpResultUtil.error(301, "更新订单失败");
        return HttpResultUtil.success("更新订单成功");
    }

    /**
     * 查询订单，根据用户名查询
     * @param username
     * @param orInfo
     * @return
     */
    @GetMapping("searchOrder")
    public  HttpResult<OrdersInfo> searchOrder(String username,OrdersInfo orInfo){//
        OrdersInfo ordersInfo=orderService.searchOrder(username,orInfo);
        if(StringUtils.isEmpty(ordersInfo))
            return HttpResultUtil.error(301, "搜索订单失败");
        return HttpResultUtil.success(ordersInfo);
    }
}
