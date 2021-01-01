package com.platform.VO;

import com.platform.DTO.OrderInfo;
import lombok.Data;

import java.util.List;

@Data
public class GetOrderInfoResponse {
    private int total;
    private List<OrderInfo> orderList;
}
