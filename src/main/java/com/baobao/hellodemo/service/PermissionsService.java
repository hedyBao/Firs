package com.baobao.hellodemo.service;

import com.baobao.hellodemo.dao.*;
import com.baobao.hellodemo.entity.Permission;
import com.baobao.hellodemo.entity.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private PermissionMapper permissionMapper;




    public String findUserNameByUserId(Integer userId){
        return userMapper.findUserNameByUserId(userId);
    }

    public List<Integer> findRIdsByUName(String userName){
        List<Integer> roleIds= userRoleMapper.findRIdsByUName(userName);
        return roleIds;
    }

    public List<RolePermission> queryByRoleIds(List<Integer> roleIdList){
        List<RolePermission> rp =rolePermissionMapper.queryByRoleIds(roleIdList);
        return rp;

    }
    public List<Permission> queryByPermissionIdList(List<Integer> permissionIdList){
        List<Permission> p =permissionMapper.queryByPermissionIdList(permissionIdList);
        return p;

    }

}
