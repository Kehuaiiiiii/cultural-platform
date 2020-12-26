package com.platform.controller;

import com.platform.domain.JwtResult;
import com.platform.domain.Orders;
import com.platform.domain.OrdersInfo;
import com.platform.domain.User;
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
    public JwtResult<OrdersInfo> getOrderInfo(int uid, OrdersInfo orders){
        JwtResult<OrdersInfo> result = new JwtResult<>();
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
