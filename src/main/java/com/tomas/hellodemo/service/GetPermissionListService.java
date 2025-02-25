package com.tomas.hellodemo.service;

import com.tomas.hellodemo.dao.PermissionMapper;
import com.tomas.hellodemo.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPermissionListService {

    @Autowired
    private PermissionMapper permissionMapper;

    public List<Permission> queryAllPermissions(){
        List<Permission> permissionList= permissionMapper.queryAllPermissions();
        return permissionList;
    }


    public void addPermission(Permission permission){
        permissionMapper.addPermission(permission);
    }

    public List<Permission> AllPermissions(){
        List<Permission> permissionList = permissionMapper.AllPermissions();
        return permissionList;
    }

    public void deleByPermissionId(Integer permissionId){
        permissionMapper.deleByPermissionId(permissionId);
    }

    public void deleByPIds(List<Integer> permissionIds){
        permissionMapper.deleByPIds(permissionIds);
    }

    public void updateByPId(Permission permission){
         permissionMapper.updateByPId(permission);
    }

    public void addUrl(String url){
         permissionMapper.addUrl(url);
    }

    public Permission findByPermissionName(String permissionName){
        Permission permission = permissionMapper.findByPermissionName(permissionName);
        return permission;
    }


    public String findPermissionNameByPermissionId(Integer permissionId){

        return  permissionMapper.findPermissionNameByPermissionId(permissionId);

    }
}
