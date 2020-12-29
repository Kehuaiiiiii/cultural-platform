package com.platform.mapper;

import com.platform.DAO.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper{

    User login(@Param("username") String username, @Param("password") String password);

    Boolean addUser(User user);

    Boolean updateUser(User user);

    List<User> getUserInfo();

    Integer getRid(int uid);

    List<Integer> getUid(@Param("username") String username);
}
