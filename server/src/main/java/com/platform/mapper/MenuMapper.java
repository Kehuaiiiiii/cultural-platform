package com.platform.mapper;

import com.platform.DAO.Menu;

import java.util.ArrayList;

public interface MenuMapper {

    ArrayList<Menu> getMenu(int rid);
}
