package com.platform.controller;

import com.platform.DAO.Orders;
import com.platform.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("addOrder")
    public Boolean addOrder(Orders orders){
        return orderService.addOrder(orders);
    }

    @GetMapping("updateOrder")
    public Boolean updateOrder(Orders orders){
        return orderService.updateOrder(orders);
    }

    @GetMapping("getOrderInfo")
    public List<Orders> getOrderInfo(Orders orders){
        if(!StringUtils.isEmpty(orderService.getOrderInfo(orders)))
            return orderService.getOrderInfo(orders);
        return null;
    }
}
