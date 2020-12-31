package com.platform.service.impl;

import com.platform.DAO.Orders;
import com.platform.DAO.OrdersInfo;
import com.platform.mapper.OrderMapper;
import com.platform.mapper.UserMapper;
import com.platform.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean addOrder(Orders orders) {
        Date dt = new Date();
        SimpleDateFormat sdf =   new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        orders.setCreated_time(currentTime);
        orders.setModified_time(currentTime);
        Integer res = orderMapper.addOrder(orders);
        System.out.println("增加了："+res+" 条记录");
        return res > 0;
    }

    @Override
    public Boolean updOrderSend(Orders orders) {
        Date dt = new Date();
        SimpleDateFormat sdf =   new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        orders.setModified_time(currentTime);
        Integer res = orderMapper.updOrderSend(orders);
        System.out.println("更新了："+res+" 条记录");
        return res > 0;
    }

    @Override
    public Boolean updOrderPay(Orders orders) {
        Date dt = new Date();
        SimpleDateFormat sdf =   new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        orders.setModified_time(currentTime);
        Integer res = orderMapper.updOrderPay(orders);
        System.out.println("更新了："+res+" 条记录");
        return res > 0;
    }

    @Override
    public OrdersInfo getOrderInfo(int uid, OrdersInfo orders) {
        int page=orders.getPagenum();
        int size=orders.getPagesize();
        int min=(page-1)*size;
        int max=page*size;
        int rid=userMapper.getRid(uid);
        if(rid>1){
            orders.setTotal(orderMapper.getTotal(uid));
            orders.setOrders(orderMapper.getOrders(uid,min,max));
        }else{
            orders.setTotal(orderMapper.getAllTotal());
            orders.setOrders(orderMapper.getAllOrders(min,max));
        }
        return orders;
    }

    @Override
    public OrdersInfo searchOrder(String username,OrdersInfo ordersInfo) {
        int page=ordersInfo.getPagenum();
        int size=ordersInfo.getPagesize();
        int min=(page-1)*size;
        int max=page*size;
        List<Integer> list = userMapper.getUid(username);
        ordersInfo.setOrders(orderMapper.getOrdAdmin(list,min,max));
        ordersInfo.setTotal(orderMapper.getTotalAdmin(list));
        return ordersInfo;
    }

    @Override
    public boolean deleteOrder(int id) {
        Integer res = orderMapper.deleteOrder(id);
        System.out.println("删除了："+res+" 条记录");
        return res > 0;
    }

}
