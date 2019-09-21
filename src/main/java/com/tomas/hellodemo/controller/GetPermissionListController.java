package com.tomas.hellodemo.controller;

import com.tomas.hellodemo.HashMapCache.PermissionHashMap;
import com.tomas.hellodemo.RedisUtils.RedisCacheForPermission;
import com.tomas.hellodemo.controller.bo.PermissionBO;
import com.tomas.hellodemo.entity.Permission;
import com.tomas.hellodemo.service.GetPermissionListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GetPermissionListController {

    @Autowired
    private GetPermissionListService getPermissionListService;
    @Autowired
    private PermissionHashMap permissionHashMap;

    @Autowired
    private RedisCacheForPermission redisCacheForPermission;

    @RequestMapping("/per/add")
    @ResponseBody
    public void addPermission(@RequestParam("permissionId") Integer permissionId, @RequestParam("permissionName") String permissionName,@RequestParam("url") String url){
        try{
            Permission permission= new Permission();
            permission.setPermissionId(permissionId);
            permission.setPermissionName(permissionName);
            permission.setUrl(url);
//            新增：先加在数据库里
            getPermissionListService.addPermission(permission);
            String key = permission.getPermissionName();
            permissionHashMap.addPermission(key,permission);
//            再加在缓存里
            redisCacheForPermission.set(key,permission);

        }catch (Exception e){
            Logger logger= LoggerFactory.getLogger(GetPermissionListController.class);
            logger.error("fail",e);
        }
    }



    @RequestMapping("/per/listpage")
    @ResponseBody
    public List<PermissionBO> queryAllPermissions(@RequestParam(value = "permissionName",required = false) String permissionName){
        List<Permission> permissions= new ArrayList<>();
        List<PermissionBO> permissionBOS= new ArrayList<>();

        if(permissionName==null|| permissionName.length()==0){
            permissions = getPermissionListService.queryAllPermissions();


        }else {
            Permission result =permissionHashMap.queryPermission(permissionName);

            if(result == null){
//                这一步已经是在缓存里查不到的时候，再去数据库里查：
                Permission permission= getPermissionListService.findByPermissionName(permissionName);
                if(permission != null){
                    permissions.add(permission);
                    permissionHashMap.addPermission(permissionName,permission);
                    redisCacheForPermission.set(permissionName,permission);
                }

            }else{
                permissions.add(result);
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



    @RequestMapping("/permission/all")
    @ResponseBody
    public List<Permission> AllPermissions(){
        List<Permission> permissions = getPermissionListService.queryAllPermissions();
        return permissions;

    }
    @RequestMapping("/per/remove")
    @ResponseBody
    public void deleByPermissionId(@RequestParam("permissionId") Integer permissionId){
        String key = getPermissionListService.findPermissionNameByPermissionId(permissionId);
        redisCacheForPermission.del(key);
        Permission permission= new Permission();
        permission.setPermissionId(permissionId);
        getPermissionListService.deleByPermissionId(permissionId);

    }

    @RequestMapping("/per/batchremove")
    @ResponseBody
    public void deleByPIds(@RequestParam("permissionIds") List<Integer> permissionIds){

        for(Integer permissionId:permissionIds){
            String key = getPermissionListService.findPermissionNameByPermissionId(permissionId);
            redisCacheForPermission.del(key);
        }


        getPermissionListService.deleByPIds(permissionIds);

    }
    @RequestMapping("/per/edit")
    @ResponseBody
    public void updateByPId(@RequestBody Permission permission){
         getPermissionListService.updateByPId(permission);
         String key= permission.getPermissionName();

         redisCacheForPermission.set(key,permission);


    }






}
