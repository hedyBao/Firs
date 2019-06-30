package com.baobao.hellodemo.service;

import com.baobao.hellodemo.dao.*;
import com.baobao.hellodemo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class GetUserListService {

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

    public List<User> userLists(){
        List<User> users = userMapper.getAllUserName();
        return users;

    }

    public int insertUser(User user){

        return userMapper.insertUser(user);
    }

    public int deleteUser(Integer userId){
        return userMapper.deleteUser(userId);
    }

    public User findByUserName(String userName){
        User user = userMapper.findByUserName(userName);
        return user;
    }

    public List<UserRole> queryByUserNameList(List<String> userNameList){
        List<UserRole> userRoleList= userRoleMapper.queryByUserNameList(userNameList);
        return userRoleList;
    }

    public List<Role> queryByRoleIdList(List<Integer> roleIdList){
        List<Role> roleNameList= roleMapper.queryByRoleIdList(roleIdList);
        return roleNameList;
    }

    public void addByRoleId(UserRole userRole){
        try {
             userRoleMapper.addByRoleId(userRole);
        }catch (Exception e){
            if(e instanceof org.springframework.dao.DuplicateKeyException){
                return;
            }
            throw  e;
        }

    }

    public int deleByUserName(String userName){
        return userRoleMapper.deleByUserName(userName);
    }


    public List<Role> queryAll(){
        List<Role> roles = roleMapper.queryAll();
        return roles;
    }


    public List<RolePermission> queryByRoleIds(List<Integer> roleIdList){
        List<RolePermission> rolePermissionList = rolePermissionMapper.queryByRoleIds(roleIdList);
        return rolePermissionList;
    }


    public List<Permission> queryByPermissionIdList(List<Integer> permissionIdList){
        List<Permission> permissionList =permissionMapper.queryByPermissionIdList(permissionIdList);
        return permissionList;
    }

    public List<Permission> queryAllPermissions(){
        List<Permission> permissions = permissionMapper.queryAllPermissions();
        return permissions;
    }

    public void insertRole(Role role){
        roleMapper.insertRole(role);

    }

    public int deleByRoleId(Integer roleId){
        return roleMapper.deleByRoleId(roleId);
    }


    public void addByPermissionId(RolePermission rolePermission){
        rolePermissionMapper.addByPermissionId(rolePermission);
    }

    public int deleByRoleName(String roleName){
        return rolePermissionMapper.deleByRoleName(roleName);
    }


    public Role findByRoleName(String roleName){
        Role role=roleMapper.findByRoleName(roleName);
        return role;
    }

    public List<Role> getAllRoleName(){
        List<Role> roles = roleMapper.getAllRoleName();
        return roles;
    }

    public int deleByUserIds(List<Integer> userIds){
        return userMapper.deleByUserIds(userIds);
    }

    public int deleByRoleIds(List<Integer> roleIds){
        return roleMapper.deleByRoleIds(roleIds);
    }

    public String findUserNameByUserId(Integer userId){
        return userMapper.findUserNameByUserId(userId);
    }

    public String findRoleNameByRoleId(Integer roleId){
        return roleMapper.findRoleNameByRoleId(roleId);
    }



    public List<User> findUsersByIds(List<Integer> userIds){
        List<User> users= userMapper.findUsersByIds(userIds);
        return users;
    }

    public int deleByUNames(List<String> userName){
        return userRoleMapper.deleByUNames(userName);
    }

    public List<Role> findRsByIds(List<Integer> roleIds){
        return roleMapper.findRsByIds(roleIds);
    }

    public int deleByRNames(List<String> roleNames){
        return rolePermissionMapper.deleByRNames(roleNames);
    }


    public int deleURByRId(Integer roleId){
        return userRoleMapper.deleURByRId(roleId);
    }

    public int deleURByRIds(List<Integer> roleIds){
        return userRoleMapper.deleURByRIds(roleIds);
    }

    public int deleRPByRId(Integer roleId){
        return rolePermissionMapper.deleRPByRId(roleId);
    }


}
