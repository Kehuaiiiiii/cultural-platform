package com.platform.controller;

import com.platform.domain.*;
import com.platform.service.IUserService;
import com.platform.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("login")
    public JwtResult<String> Login(HttpServletRequest request) {
        JwtResult<String> result = new JwtResult<>();
        String zh = request.getParameter("username");
        String pwd = request.getParameter("password");
        if (!StringUtils.isEmpty(zh) && !StringUtils.isEmpty(pwd)) {
            User user = userService.Login(zh, pwd);
            if (user != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("rid",user.getRid());
                map.put("uid",user.getUid());
                String token = JwtUtil.generateToken(map);
                result.setCode(200);
                result.setData(token);
                result.setMsg("请求成功");
            } else {
                result.setCode(301);
                result.setData("用户名或密码错误");
            }
        } else {
            result.setCode(302);
            result.setData("输入错误");
        }
        return result;
    }

    @GetMapping("getMenu")
    public JwtResult<ArrayList<Menu>> getMenu(int rid){
        //Claims claims=JwtUtil.verifyAndGetClaimsByToken(token);
        JwtResult<ArrayList<Menu>> result = new JwtResult<>();
        //int rid=(int)claims.get("rid");
        if(!StringUtils.isEmpty(userService.getMenu(rid))){
            result.setCode(200);
            result.setData(userService.getMenu(rid));
            result.setMsg("请求成功");
        }else {
            result.setCode(302);
            result.setMsg("请求失败");
        }
        return result;
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
        if (!StringUtils.isEmpty(userService.getUserInfo(user)))
            return userService.getUserInfo(user);
        return null;
    }

    @GetMapping("getAllGoods")
    public List<Goods> getAllGoods(){
        if(!StringUtils.isEmpty(userService.getAllGoods()))
            return userService.getAllGoods();
        return null;
    }


}
