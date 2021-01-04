package com.platform.service.impl;

import com.platform.DAO.Goods;
import com.platform.DAO.Orders;
import com.platform.DAO.OrdersInfo;
import com.platform.DAO.User;
import com.platform.DTO.OrderInfo;
import com.platform.VO.GetOrderInfoRequest;
import com.platform.VO.GetOrderInfoResponse;
import com.platform.mapper.GoodsMapper;
import com.platform.mapper.OrderMapper;
import com.platform.mapper.UserMapper;
import com.platform.service.IOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Boolean addOrder(Orders orders) {
        Date dt = new Date();
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        orders.setCreated_time(currentTime);
        orders.setModified_time(currentTime);
        Integer res = orderMapper.addOrder(orders);
        System.out.println("增加了：" + res + " 条记录");
        return res > 0;
    }

    @Override
    public Boolean updOrderSend(Orders orders) {
        Integer res = orderMapper.updOrderSend(orders);
        System.out.println("更新了：" + res + " 条记录");
        return res > 0;
    }

    @Override
    public Boolean updOrderPay(Orders orders) {
        Integer res = orderMapper.updOrderPay(orders);
        System.out.println("更新了：" + res + " 条记录");
        return res > 0;
    }

    @Override
    public GetOrderInfoResponse getOrderInfo(int uid, GetOrderInfoRequest request) {
        Integer payStatus = request.getPayStatus();
        Integer sendStatus = request.getSendStatus();
        int offset = (request.getPagenum() - 1) * request.getPagesize();
        int size = request.getPagesize();
        int rid = userMapper.getRid(uid);
        GetOrderInfoResponse result = new GetOrderInfoResponse();
        List<OrderInfo> orderInfos = new ArrayList<>();
        List<Orders> orders;
        if (rid > 1) {
            result.setTotal(orderMapper.getTotal(uid, payStatus, sendStatus));
            orders = orderMapper.getOrders(uid, payStatus, sendStatus, offset, size);

        } else {
            User user = new User();
            if(!StringUtils.isEmpty(request.getName())) {
                List<User> userList = userMapper.getUserInfo(request.getName(), 0, 1);
                if(CollectionUtils.isEmpty(userList)) {
                    user.setUid(-1);
                } else {
                    user = userList.get(0);
                }
            }
            result.setTotal(orderMapper.getAllTotal(user.getUid(), payStatus, sendStatus));
            orders = orderMapper.getAllOrders(user.getUid(), payStatus, sendStatus, offset, size);
        }
        for (Orders order : orders) {
            Goods goods = goodsMapper.getGoodsById(order.getGoods_id());
            User seller = userMapper.getUserById(order.getSeller_id());
            User buyer = userMapper.getUserById(order.getBuyer_id());
            OrderInfo orderInfo = OrderInfo.builder()
                    .id(order.getId())
                    .buyer_name(buyer.getNickname())
                    .seller_name(seller.getNickname())
                    .goods_name(goods.getName())
                    .goods_number(order.getGoods_number())
                    .goods_price(goods.getPrice())
                    .order_price(order.getOrder_price())
                    .pay_status(order.getPay_status())
                    .send_status(order.getSend_status())
                    .created_time(order.getCreated_time())
                    .address(order.getAddress())
                    .build();
            orderInfos.add(orderInfo);
        }
        result.setOrderList(orderInfos);
        return result;
    }

    @Override
    public OrdersInfo searchOrder(String username, OrdersInfo ordersInfo) {
        int page = ordersInfo.getPagenum();
        int size = ordersInfo.getPagesize();
        int min = (page - 1) * size;
        int max = size;
        List<Integer> list = userMapper.getUid(username);
        ordersInfo.setOrders(orderMapper.getOrdAdmin(list, min, max));
        ordersInfo.setTotal(orderMapper.getTotalAdmin(list));
        return ordersInfo;
    }

    @Override
    public boolean deleteOrder(int id) {
        Integer res = orderMapper.deleteOrder(id);
        System.out.println("删除了：" + res + " 条记录");
        return res > 0;
    }

}
