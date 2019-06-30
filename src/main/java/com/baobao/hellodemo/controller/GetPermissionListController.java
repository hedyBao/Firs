package com.baobao.hellodemo.controller;

import com.baobao.hellodemo.controller.bo.PermissionBO;
import com.baobao.hellodemo.entity.Permission;
import com.baobao.hellodemo.service.GetPermissionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GetPermissionListController {

    @Autowired
    private GetPermissionListService getPermissionListService;


    @RequestMapping("/per/listpage")
    @ResponseBody
    public List<PermissionBO> queryAllPermissions(@RequestParam(value = "permissionName",required = false) String permissionName){
        List<Permission> permissions= new ArrayList<>();
        List<PermissionBO> permissionBOS= new ArrayList<>();

        if(permissionName==null|| permissionName.length()==0){
            permissions = getPermissionListService.queryAllPermissions();


        }else {
            Permission permission= getPermissionListService.findByPermissionName(permissionName);
            if(permission != null){
                permissions.add(permission);
            }
        }

        if(permissions == null || permissions.size()==0){
            return new ArrayList<>();
        }else {
            permissions.forEach(i ->{
                PermissionBO permissionBO = new PermissionBO();
                permissionBO.setPermissionId(i.getPermissionId());
                permissionBO.setPermissionName(i.getPermissionName());
                permissionBO.setUrl(i.getUrl());
                permissionBOS.add(permissionBO);
            });
        }
        return permissionBOS;
    }

    @RequestMapping("/per/add")
    @ResponseBody
    public void addPermission(@RequestParam("permissionId") Integer permissionId, @RequestParam("permissionName") String permissionName,@RequestParam("url") String url){
        Permission permission= new Permission();
        permission.setPermissionId(permissionId);
        permission.setPermissionName(permissionName);
        permission.setUrl(url);
        getPermissionListService.addPermission(permission);
    }


    @RequestMapping("/permission/all")
    @ResponseBody
    public List<Permission> AllPermissions(){
        List<Permission> permissions = getPermissionListService.queryAllPermissions();
        return permissions;

    }
    @RequestMapping("/per/remove")
    @ResponseBody
    public void deleByPermissionId(@RequestParam("permissionId") Integer permissionId){
        Permission permission= new Permission();
        permission.setPermissionId(permissionId);
        getPermissionListService.deleByPermissionId(permissionId);

    }

    @RequestMapping("/per/batchremove")
    @ResponseBody
    public void deleByPIds(@RequestParam("permissionIds") List<Integer> permissionIds){
        getPermissionListService.deleByPIds(permissionIds);

    }
    @RequestMapping("/per/edit")
    @ResponseBody
    public void updateByPId(@RequestBody Permission permission){
         getPermissionListService.updateByPId(permission);
    }






}
