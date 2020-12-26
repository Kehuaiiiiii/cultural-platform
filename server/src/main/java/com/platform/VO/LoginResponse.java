package com.platform.VO;

import lombok.Data;

@Data
public class LoginResponse {

    private int uid;

    private String username;

    private int rid;

    private String token;
}
