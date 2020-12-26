package com.platform.controller;

import com.platform.VO.HttpResult;
import com.platform.VO.LoginRequest;
import com.platform.VO.LoginResponse;
import com.platform.DAO.*;
import com.platform.constant.HttpError;
import com.platform.service.IUserService;
import com.platform.util.HttpResultUtil;
import com.platform.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("login")
    public HttpResult<LoginResponse> Login(@RequestBody LoginRequest request) {
        // 参数检查
        String username = request.getUsername();
        String password = request.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return HttpResultUtil.error(HttpError.PARAMETER_ILLEGAL_CODE, HttpError.PARAMETER_ILLEGAL_MSG);
        }
        System.out.println("Login: " + username + " " + password);
        User user = userService.Login(username, password);
        if (user == null) {
            return HttpResultUtil.error(301, "用户名或密码错误");
        }

        LoginResponse response = new LoginResponse();
        BeanUtils.copyProperties(user, response);

        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("rid", user.getRid());
        tokenMap.put("uid", user.getUid());
        String token = JwtUtil.generateToken(tokenMap);
        response.setToken(token);

        return HttpResultUtil.success(response);
    }

    @GetMapping("getMenu")
    public HttpResult<ArrayList<Menu>> getMenu(String token) {
//        Claims claims = JwtUtil.verifyAndGetClaimsByToken(token);
//        HttpResult<ArrayList<Menu>> result = new HttpResult<>();
//        int rid = (int) claims.get("rid");
//        if (!StringUtils.isEmpty(userService.getMenu(rid))) {
//            result.setCode(200);
//            result.setData(userService.getMenu(rid));
//            result.setMsg("请求成功");
//        } else {
//            result.setCode(302);
//            result.setMsg("请求失败");
//        }
//        return result;
        return null;
    }

    @GetMapping("logout")
    public String Logout(HttpServletRequest request) {
        return "退出成功";
    }

    @GetMapping("addUser")
    public String addAdmin(User user) {
        if (userService.addUser(user))
            return "创建成功";
        return "创建失败";
    }

    @GetMapping("updateUser")
    public String updateAdmin(User user) {

        if (userService.updateUser(user))
            return "更新成功";
        return "更新失败";
    }

    @GetMapping("getUserInfo")
    public User getAdminInfo(User user) {
//        if (!StringUtils.isEmpty(userService.getUserInfo(user)))
//            return userService.getUserInfo(user);
//        return null;
        return null;
    }


}
