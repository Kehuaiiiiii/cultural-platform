package com.platform.VO;

import lombok.Data;

@Data
public class AddOrderRequest {
    private Integer uid;
    private Integer id;
    private Integer number;
    private Double total;
    private String address;
}
