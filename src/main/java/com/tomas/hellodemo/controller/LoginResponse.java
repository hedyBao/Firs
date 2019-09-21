package com.tomas.hellodemo.controller;

import com.tomas.hellodemo.entity.LoginUser;

public class LoginResponse extends BaseResponse {
    private LoginUser user;

    public LoginUser getUser() {
        return user;
    }

    public void setUser(LoginUser user) {
        this.user = user;
    }
}
