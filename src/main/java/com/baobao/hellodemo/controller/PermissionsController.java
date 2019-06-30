package com.baobao.hellodemo.controller;

import com.baobao.hellodemo.entity.Permission;
import com.baobao.hellodemo.entity.RolePermission;
import com.baobao.hellodemo.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PermissionsController {


    @Autowired
    private PermissionsService permissionsService;



    @RequestMapping("/permissions")
    @ResponseBody
    public List<Permission> queryPermissions(@RequestParam("userId") Integer userId){
        //    1.通过userId 在User拿到UserName；
        String userName= permissionsService.findUserNameByUserId(userId);
       //    2.通过UserName，在userRole拿到RoleIds;
        List<Integer> roleIds= permissionsService.findRIdsByUName(userName);
       //    3.通过RoleIds,在RolePermission拿到PermisionIds;
        List<RolePermission> rps= permissionsService.queryByRoleIds(roleIds);
//        4.在List<RolePermission>中拿到List<permissionId>;
        List<Integer> permissionIds = rps.stream().map(i -> i.getPermissionId()).collect(Collectors.toList());

       //    5.通过PermisionIds，在Permission 拿到Permissions;

        List<Permission> permissions= permissionsService.queryByPermissionIdList(permissionIds);

        return permissions;

    }






}
