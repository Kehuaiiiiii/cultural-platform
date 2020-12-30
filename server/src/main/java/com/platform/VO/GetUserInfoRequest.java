package com.platform.VO;

import lombok.Data;

@Data
public class GetUserInfoRequest {
    private String name;

    private int pagenum;

    private int pagesize;
}
