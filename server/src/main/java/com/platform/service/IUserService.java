package com.platform.service;

import com.platform.DAO.Menu;
import com.platform.DAO.User;

import java.util.ArrayList;
import java.util.List;

public interface IUserService {
    User Login(String username, String password);

    Boolean addUser(User user);

    Boolean updateUser(User user);

    List<User> getUserInfo(String username, int pagenum, int pagesize);

    List<Menu> getMenu(int rid);

    Integer getTotal(String username);
}
