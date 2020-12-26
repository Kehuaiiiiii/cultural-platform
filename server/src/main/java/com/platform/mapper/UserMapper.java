package com.platform.mapper;

import com.platform.DAO.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper{

    User login(@Param("username") String username, @Param("password") String password);

    Boolean addUser(User user);

    Boolean updateUser(User user);

    User getUserInfo(User user);

    Integer getRid(int uid);

}
