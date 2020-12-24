package com.platform.mapper;

import com.platform.domain.Menu;

import java.util.ArrayList;

public interface MenuMapper {
    ArrayList<Menu> getMenu(int rid);
}
