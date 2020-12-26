package com.platform.DAO;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    /**
     * 菜单 id
     */
    private int id;

    /**
     * 父菜单 id
     */
    private int pid;

    /**
     * 对应角色 id
     */
    private int rid;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 子菜单
     */
    private List<Menu> children;

}
