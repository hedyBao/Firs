package com.tomas.hellodemo.controller;

import com.tomas.hellodemo.entity.Permission;

import java.util.List;

public interface PerDubboService {
    List<Permission> queryPermissions(Integer userId);
}
