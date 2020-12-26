package com.platform.service;

import com.platform.DAO.Menu;
import com.platform.DAO.User;

import java.util.ArrayList;

public interface IUserService {
    User Login(String username, String password);

    Boolean addUser(User user);

    Boolean updateUser(User user);

    User getUserInfo(User user);

    ArrayList<Menu> getMenu(int rid);
}
