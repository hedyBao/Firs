package com.baobao.hellodemo.controller;


import com.baobao.hellodemo.controller.bo.RoleBO;
import com.baobao.hellodemo.dao.RoleMapper;
import com.baobao.hellodemo.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class RoleController {



    @Autowired
    RoleMapper roleMapper;


    @RequestMapping("/role/all")
    public List<RoleBO> queryAll(){
        List<Role> roles = roleMapper.queryAll();

        return roles.stream().map( role ->{
            RoleBO roleBO = new RoleBO();
            roleBO.setRoleId(role.getRoleId());
            roleBO.setRoleName(role.getRoleName());
            return roleBO;
        }).collect(Collectors.toList());
    }

 }
