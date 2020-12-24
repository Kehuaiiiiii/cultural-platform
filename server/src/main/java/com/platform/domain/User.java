package com.platform.domain;

public class User {
    private int uid;            //用户ID
    private String username;    //用户账户
    private String password;    //密码
    private int rid;            //角色ID 1：管理员 2：商家 3：用户
    private String token;       //jwt标识

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }
}
