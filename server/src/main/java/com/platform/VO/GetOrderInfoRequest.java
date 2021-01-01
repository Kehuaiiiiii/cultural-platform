package com.platform.VO;

import lombok.Data;

@Data
public class GetOrderInfoRequest {
    private int pagenum;        //当前页码
    private int pagesize;       //每页订单数量
}
