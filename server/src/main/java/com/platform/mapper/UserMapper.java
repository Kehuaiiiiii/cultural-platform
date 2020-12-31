package com.platform.mapper;

import com.platform.DAO.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper{

    User login(@Param("username") String username, @Param("password") String password);

    Integer addUser(User user);

    Boolean updateUser(User user);

    List<User> getUserInfo(@Param("username") String username, @Param("offset") int offset, @Param("size") int size);

    int getTotal(@Param("username") String username);

    Integer getRid(int uid);

    List<Integer> getUid(@Param("username") String username);

    Integer deleteUser(int uid);

}
