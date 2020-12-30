package com.platform.DAO;

import lombok.Data;

@Data
public class User {
    /**
     * 用户 id
     */
    private Integer uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色ID 1：管理员 2：商家 3：用户
     */
    private int rid;

    /**
     * 角色名称
     */
    private String nickname;
}
