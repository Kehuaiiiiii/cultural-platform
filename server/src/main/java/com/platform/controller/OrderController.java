package com.platform.controller;

import com.platform.DAO.Orders;
import com.platform.DAO.OrdersInfo;
import com.platform.VO.GetOrderInfoRequest;
import com.platform.VO.GetOrderInfoResponse;
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
     *
     * @param request
     * @param orders
     * @return
     */
    @GetMapping("getOrder")
    public HttpResult<GetOrderInfoResponse> getOrderInfo(HttpServletRequest request, GetOrderInfoRequest orders) {
        int uid = HttpContextUtil.getUid(request);
        GetOrderInfoResponse result = orderService.getOrderInfo(uid, orders.getPagenum(), orders.getPagesize());
        if (result == null)
            return HttpResultUtil.error(301, "获取订单失败");
        return HttpResultUtil.success(result);
    }

    /**
     * 更新订单 发货状态
     *
     * @param orders
     * @return
     */
    @GetMapping("updOrderSend")
    public HttpResult<String> updateOrder(Orders orders) {
        if (!orderService.updOrderSend(orders))
            return HttpResultUtil.error(301, "更新订单发货状态失败");
        return HttpResultUtil.success("更新订单发货状态成功");
    }

    /**
     * 更新订单 支付状态
     *
     * @param orders
     * @return
     */
    @GetMapping("updOrderPay")
    public HttpResult<String> update(Orders orders) {
        if (!orderService.updOrderPay(orders))
            return HttpResultUtil.error(301, "更新订单支付状态失败");
        return HttpResultUtil.success("更新订单支付状态成功");
    }

    /**
     * 查询订单，根据用户名查询
     *
     * @param username pagenum pagesize
     * @param orInfo
     * @return
     */
    @GetMapping("searchOrder")
    public HttpResult<OrdersInfo> searchOrder(String username, OrdersInfo orInfo) {
        OrdersInfo ordersInfo = orderService.searchOrder(username, orInfo);
        if (StringUtils.isEmpty(ordersInfo))
            return HttpResultUtil.error(301, "搜索订单失败");
        return HttpResultUtil.success(ordersInfo);
    }

    /**
     * 创建订单
     *
     * @param orders
     * @return
     */
    @GetMapping("addOrder")
    public HttpResult<String> addOrder(Orders orders) {
        if (!orderService.addOrder(orders))
            return HttpResultUtil.error(301, "添加订单失败");
        return HttpResultUtil.success("添加订单成功");
    }

    /**
     * 删除订单
     *
     * @param id
     * @return
     */
    @GetMapping("deleteOrder")
    public HttpResult<String> deleteOrder(int id) {
        if (!orderService.deleteOrder(id))
            return HttpResultUtil.error(301, "删除订单失败");
        return HttpResultUtil.success("删除订单成功");
    }
}
