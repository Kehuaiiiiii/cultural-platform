package com.platform.domain;

import java.util.ArrayList;

public class Menu {
    private int id;        //菜单ID
    private int pid;        //菜单父类ID
    private String authName;    //菜单名称
    private int rid;        //权限ID
    private String path;    //父类菜单名称

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private ArrayList<Menu> children; //子菜单

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public ArrayList<Menu> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Menu> children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

}
