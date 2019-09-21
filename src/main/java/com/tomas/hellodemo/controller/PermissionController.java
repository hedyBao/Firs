package com.tomas.hellodemo.controller;


import com.tomas.hellodemo.controller.bo.PermissionBO;
import com.tomas.hellodemo.dao.PermissionMapper;
import com.tomas.hellodemo.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PermissionController{




    @Autowired
    private PermissionMapper permissionMapper;
    @RequestMapping("/per/all")
    public List<Permission> queryAllPermissions(){
        List<Permission> permissions = permissionMapper.queryAllPermissions();
        return permissions.stream().map(permission -> {
            PermissionBO permissionBO = new PermissionBO();
            permissionBO.setPermissionId(permission.getPermissionId());
            permissionBO.setPermissionName(permission.getPermissionName());
            return permissionBO;
        }).collect(Collectors.toList());
    }






}
