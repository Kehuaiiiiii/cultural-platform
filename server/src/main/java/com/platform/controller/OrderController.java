package com.platform.controller;

import com.platform.VO.HttpResult;
import com.platform.DAO.Orders;
import com.platform.DAO.OrdersInfo;
import com.platform.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Boolean updateOrder(Orders orders){
        return orderService.updateOrder(orders);
    }


}
