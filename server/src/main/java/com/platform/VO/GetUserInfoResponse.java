package com.platform.VO;

import com.platform.DAO.User;
import lombok.Data;

import java.util.List;

@Data
public class GetUserInfoResponse {
    private int total;
    private List<User> userList;
}
