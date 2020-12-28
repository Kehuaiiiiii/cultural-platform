package com.platform.controller;

import com.platform.VO.HttpResult;
import com.platform.VO.LoginRequest;
import com.platform.VO.LoginResponse;
import com.platform.DAO.*;
import com.platform.VO.MenuResponse;
import com.platform.constant.HttpError;
import com.platform.service.IUserService;
import com.platform.util.HttpContextUtil;
import com.platform.util.HttpResultUtil;
import com.platform.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public HttpResult<MenuResponse> getMenu(HttpServletRequest request) {
        int rid = HttpContextUtil.getRid(request);
        System.out.println("rid: " + rid);
        List<Menu> menuList = userService.getMenu(rid);
        if (CollectionUtils.isEmpty(menuList)) {
            return HttpResultUtil.error(301, "未设置角色权限");
        }
        MenuResponse response = new MenuResponse();
        response.setMenuList(menuList);
        return HttpResultUtil.success(response);
    }

    @GetMapping("logout")
    public String Logout(HttpServletRequest request) {
        return "退出成功";
    }

    @GetMapping("addUser")
    public HttpResult<String> addAdmin(User user) {
        if (userService.addUser(user))
            return HttpResultUtil.error(301, "添加失败");
        return HttpResultUtil.success("添加成功");
    }

    @GetMapping("updateUser")
    public HttpResult<String> updateAdmin(User user) {

        if (userService.updateUser(user))
            return HttpResultUtil.error(301, "更新失败");
        return HttpResultUtil.success("更新成功");
    }

    @GetMapping("getUserInfo")
    public User getAdminInfo(User user) {
        //if (!StringUtils.isEmpty(userService.getUserInfo(user)))
        //    return userService.getUserInfo(user);
        return null;
    }


}
