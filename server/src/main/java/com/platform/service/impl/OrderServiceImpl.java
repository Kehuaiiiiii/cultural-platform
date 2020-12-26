package com.platform.service.impl;

import com.platform.DAO.Orders;
import com.platform.DAO.OrdersInfo;
import com.platform.mapper.OrderMapper;
import com.platform.mapper.UserMapper;
import com.platform.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean addOrder(Orders orders) {
        return orderMapper.addOrder(orders);
    }

    @Override
    public Boolean updateOrder(Orders orders) {
        return orderMapper.updateOrder(orders);
    }


    @Override
    public OrdersInfo getOrderInfo(int uid, OrdersInfo orders) {
        OrdersInfo ordersInfo=new OrdersInfo();
        int page=orders.getPagenum();
        int size=orders.getPagesize();
        int min=(page-1)*size;
        int max=page*size;
        ordersInfo.setPagenum(page);
        ordersInfo.setPagesize(size);
        int rid=userMapper.getRid(uid);
        if(rid>1){
            ordersInfo.setTotal(orderMapper.getTotal(uid));
            ordersInfo.setOrders(orderMapper.getOrders(uid,min,max));
        }else{
            ordersInfo.setTotal(orderMapper.getAllTotal());
            ordersInfo.setOrders(orderMapper.getAllOrders(min,max));
        }
        /*if(orders.getQuery()!=null) {
            Orders order=orders.getQuery();
            ordersInfo.setTotal(orderMapper.getTotal(order));
            ordersInfo.setOrders(orderMapper.getOrders(order,min,max));
        }else{
            ordersInfo.setTotal(orderMapper.getAllTotal());
            ordersInfo.setOrders(orderMapper.getAllOrders(min,max));
        }*/
        return ordersInfo;
    }
}
