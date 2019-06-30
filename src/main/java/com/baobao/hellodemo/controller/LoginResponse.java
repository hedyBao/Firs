package com.baobao.hellodemo.controller;

import com.baobao.hellodemo.entity.LoginUser;

public class LoginResponse extends BaseResponse {
    private LoginUser user;

    public LoginUser getUser() {
        return user;
    }

    public void setUser(LoginUser user) {
        this.user = user;
    }
}
