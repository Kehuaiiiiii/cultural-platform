package com.platform.VO;

import com.platform.DAO.Menu;
import lombok.Data;

import java.util.List;

@Data
public class MenuResponse {
    private List<Menu> menuList;
}
