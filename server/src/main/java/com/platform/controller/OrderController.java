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

    @GetMapping("getOrder")
    public HttpResult<OrdersInfo> getOrderInfo(int uid, OrdersInfo orders){
        HttpResult<OrdersInfo> result = new HttpResult<>();
        if(!StringUtils.isEmpty(orderService.getOrderInfo(uid,orders))){
            result.setCode(200);
            result.setData(orderService.getOrderInfo(uid,orders));
            result.setMsg("请求成功");
        }else{
            result.setCode(304);
            result.setMsg("请求失败");
        }
        return result;
    }

    @GetMapping("updateOrder")
    public HttpResult<String> updateOrder(Orders orders){
        if(!orderService.updateOrder(orders))
            HttpResultUtil.error(301, "更新订单失败");
        return HttpResultUtil.success("更新订单成功");
    }


    @GetMapping("searchOrder")
    public  HttpResult<OrdersInfo> searchOrder(int rid,String username,OrdersInfo orInfo){//HttpServletRequest request
        //int rid= HttpContextUtil.getUid(request);
        if(rid!=1) HttpResultUtil.error(302, "无管理员权限");
        OrdersInfo ordersInfo=orderService.searchOrder(username,orInfo);
        if(StringUtils.isEmpty(ordersInfo))
            HttpResultUtil.error(301, "搜索订单失败");
        HttpResult<OrdersInfo> result=new HttpResult<>();
        result.setCode(200);
        result.setData(ordersInfo);
        return result;
    }
}
