package com.baobao.hellodemo.controller;


import com.baobao.hellodemo.entity.LoginUser;
import org.springframework.web.bind.annotation.*;

@RestController
//这是一个响应网络请求的类
public class LoginController {


    @RequestMapping("/login")
    public LoginResponse login(@RequestBody LoginUser user){
        LoginResponse response = new LoginResponse();
        response.setCode(200);
        response.setMsg("success");
        response.setUser(user);
        return response ;
    }

}
