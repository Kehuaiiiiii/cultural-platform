package com.platform.controller;

import com.platform.domain.Goods;
import com.platform.domain.JwtResult;
import com.platform.domain.Merchant;
import com.platform.service.IMerchantService;
import com.platform.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("merchant")
public class MerchantController {

    @Autowired
    private IMerchantService merchantService;

    @GetMapping("login")
    public JwtResult<String> Login(HttpServletRequest request){
        JwtResult<String> result=new JwtResult<>();
        String zh= request.getParameter("username");
        String pwd=request.getParameter("password");
        if(!StringUtils.isEmpty(zh)&&!StringUtils.isEmpty(pwd)){
            Merchant merchant = merchantService.Login(zh, pwd);
            if(merchant!=null) {
                Map<String, Object> map = new HashMap<>();
                map.put("userName", "Oven");
                String token = JwtUtil.generateToken(map);
                result.setCode(200);
                result.setData(token);
            }
            else{
                result.setCode(301);
                result.setData("用户名或密码错误");
            }
        }else{
            result.setCode(302);
            result.setData("输入错误");
        }
        return result;
    }

    @GetMapping("logout")
    public String Logout(HttpServletRequest request){
        return "退出成功";
    }

    @GetMapping("addMerchant")
    public String addAdmin(Merchant merchant){
        if(merchantService.addMerchant(merchant))
            return "创建成功";
        return "创建失败";
    }

    @GetMapping("updateMerchant")
    public String updateAdmin(Merchant merchant){

        if(merchantService.updateMerchant(merchant))
            return "更新成功";
        return "更新失败";
    }

    @GetMapping("getMerchantInfo")
    public Merchant getAdminInfo(Merchant merchant){
        if(!StringUtils.isEmpty(merchantService.getMerchantInfo(merchant)))
            return merchantService.getMerchantInfo(merchant);
        return null;
    }

    @GetMapping("getAllGoods")
    public List<Goods> getAllGoods(){
        if(!StringUtils.isEmpty(merchantService.getAllGoods()))
            return merchantService.getAllGoods();
        return null;
    }


}
