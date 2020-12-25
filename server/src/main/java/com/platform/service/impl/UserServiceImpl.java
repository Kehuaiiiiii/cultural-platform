package com.platform.service.impl;

import com.platform.domain.Goods;
import com.platform.domain.Menu;
import com.platform.domain.User;
import com.platform.mapper.MenuMapper;
import com.platform.mapper.UserMapper;
import com.platform.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public User Login(String zh, String pwd) {
        return userMapper.login(zh,pwd);
    }

    @Override
    public Boolean addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public Boolean updateUser(User user) { return userMapper.updateUser(user);  }

    @Override
    public User getUserInfo(User user) {
        return userMapper.getUserInfo(user);
    }





    @Override
    public ArrayList<Menu> getMenu(int rid) {
        ArrayList<Menu> menu = menuMapper.getMenu(rid);
        if(!StringUtils.isEmpty(menu)) {
            Map<Integer,Menu> map=new HashMap<>();
            for (int x = 0; x < menu.size(); x++) {
                map.put(menu.get(x).getId(),menu.get(x));
            }
            for (int i = 0; i < menu.size(); i++) {
                if (menu.get(i).getPid()!=0){
                    int p=menu.get(i).getPid();
                    menu.get(i).setPath(map.get(p).getAuthName());
                    ArrayList<Menu> list=map.get(p).getChildren()==null?new ArrayList<>():map.get(p).getChildren();
                    list.add(menu.get(i));
                    map.get(p).setChildren(list);
                    map.remove(menu.get(i).getId());
                }
            }
            ArrayList<Menu> menus=new ArrayList<>();
            for (Menu value : map.values()) {
                menus.add(value);
            }
            return menus;
        }
        return null;
    }
}
