package com.baobao.hellodemo.controller;

import com.baobao.hellodemo.controller.bo.RolePermissionBO;
import com.baobao.hellodemo.controller.bo.UserRoleBO;
import com.baobao.hellodemo.entity.*;
import com.baobao.hellodemo.service.GetUserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class GetUserListController {
    @Autowired
    private GetUserListService getUserListService;

    @RequestMapping("/user/listpage")
    @ResponseBody
    public List<UserRoleBO> userRbacList(@RequestParam(value = "name", required = false) String name) {
        List<User> users= new ArrayList<>();
//        long a = System.currentTimeMillis();
        List<UserRoleBO> userRoleBOS =new ArrayList<>();

        if(name == null || name.length() == 0) {
            users = getUserListService.userLists();

        }else{
            User user=getUserListService.findByUserName(name);
            if(user != null){
                users.add(user);
            }
        }
//        long b = System.currentTimeMillis();

        if(users == null || users.size() ==0){
            return new ArrayList<>();
        }else{
//            第一步：在UserList中拿到userNameList，并传给下一步作为参数;
        List<String> userNameList = users.stream().map(  i-> i.getUserName()).collect(Collectors.toList());
//            第二步：先通过userNameList在UserRole中获取UserRoleList，再拿到RoleIdList，并传给下一步作为参数；
        final List<UserRole> userRoleList=getUserListService.queryByUserNameList(userNameList);
//            Map<String, List<UserRole>> map
        List<Integer> roleIdList = userRoleList.stream().map( i->i.getRoleId()).collect(Collectors.toList());
//            long c = System.currentTimeMillis();
//            第三步：通过roleIdList在Role中拿到roleList,再通过λ函数获取RoleNameList
        final List<Role> roleList=getUserListService.queryByRoleIdList(roleIdList);
//        long d = System.currentTimeMillis();
//        Map<String, List<Integer>> map = new HashMap<>();

        users.forEach( i ->{
            UserRoleBO bo = new UserRoleBO();
            bo.setUserId(i.getUserId());
            bo.setUserName(i.getUserName());
            bo.setPhone(i.getPhone());
            bo.setRoleIds(new ArrayList<>());
            bo.setRoleNames("");
            userRoleBOS.add(bo);

            for(UserRole userRole : userRoleList){
                if(bo.getUserName().equals(userRole.getUserName())){
                    bo.getRoleIds().add(userRole.getRoleId());
                }
            }
        });
        userRoleBOS.forEach(i ->{
            i.getRoleIds().forEach(j ->{
                for(Role role : roleList){
                    if(j.equals(role.getRoleId())){
                        i.setRoleNames(i.getRoleNames() + role.getRoleName() + " ");
                    }
                }
            });
        });


//            System.out.println(b-a);
//            System.out.println(c-b);
//            System.out.println(d-c);

            //System.out.println(System.currentTimeMillis()-b);


        }
        return userRoleBOS;


    }

    @RequestMapping("/user/add")
    @ResponseBody
    public BaseResponse insertUser(@RequestParam("userName") String name, @RequestParam("passWord") String passWord, @RequestParam("phone") String phone) {
        try {
            User user = new User();
            user.setUserName(name);
            user.setPassWord(passWord);
            user.setPhone(phone);


            getUserListService.insertUser(user);
            return new BaseResponse(200, "success");
        }catch (Exception e){

            return new BaseResponse(500,e.getMessage());
        }
    }
    @RequestMapping("/user/remove")
    @ResponseBody
    public int deleteUser(@RequestParam("userId") Integer userId){

        // 1.通过userId,拿到userName；
        String userName = getUserListService.findUserNameByUserId(userId);

        //2.通过userName，删除表UserRole里的数据;通过RoleId ,删除表Role里的数据；
        getUserListService.deleByUserName(userName);
        return getUserListService.deleteUser(userId);
    }


    @RequestMapping("/user/edit")
    @ResponseBody
    public BaseResponse addByRoleId(@RequestBody UserRoleBO userRoleBO){
           getUserListService.deleByUserName(userRoleBO.getUserName());

        List<Integer> roleIdList = userRoleBO.getRoleIds();
         for(int i = 0; i < roleIdList.size(); i++){
             UserRole userRole= new UserRole();
             userRole.setUserName(userRoleBO.getUserName());
             Integer roleId = roleIdList.get(i);
             userRole.setRoleId(roleId);
             getUserListService.addByRoleId(userRole);

         }
         return new BaseResponse(200, "success");

    }


//    @RequestMapping("role/listpage")
//    @ResponseBody
//    public List<Role> queryAll(){
//        List<Role> roles= new ArrayList<>();
//        roles=getUserListService.queryAll();
//        return roles;
//
//    }
    @RequestMapping("role/listpage")
    @ResponseBody
    public List<RolePermissionBO> rolePermissionBOList(@RequestParam (value = "roleName", required = false) String roleName){
        List<Role> roles= new ArrayList<>();
        List<RolePermissionBO> rpBOS =new ArrayList<>();
        if(roleName == null|| roleName.length() == 0){
            roles = getUserListService.getAllRoleName();

        }else {
            Role role=getUserListService.findByRoleName(roleName);
            if(roles!= null){
                roles.add(role);
            }
        }

        if(roles == null|| roles.size() == 0){
            return new ArrayList<>();

        }else {
//        1.在表Role中拿到RoleIdList
            List<Integer> roleIdList = roles.stream().map(i -> i.getRoleId()).collect(Collectors.toList());
//        2.通过RoleIdList,拿到表RolePermission中的全部List，并获取PermissionIdList;

            final List<RolePermission> rolePermissionList= getUserListService.queryByRoleIds(roleIdList);
            List<Integer> permissionIdList= rolePermissionList.stream().map(i -> i.getPermissionId()).collect(Collectors.toList());

//        3.通过PermissionIdList，拿到表Permission中的PermissionNameList。

            final List<Permission> permissionList = getUserListService.queryByPermissionIdList(permissionIdList);

            roles.forEach(i->{
                        RolePermissionBO rBO =new RolePermissionBO();
                        rBO.setRoleId(i.getRoleId());
                        rBO.setRoleName(i.getRoleName());
                        rBO.setPermissionIds(new ArrayList<>());
                        rBO.setPermissionName("");
                        rpBOS.add(rBO);
                        for(RolePermission rp:rolePermissionList){
                            if(rBO.getRoleId().equals(rp.getRoleId())){
                                rBO.getPermissionIds().add(rp.getPermissionId());
                            }
                        }
            });

            rpBOS.forEach(i ->{
                i.getPermissionIds().forEach(j ->{
                    for(Permission p : permissionList){
                        if(j.equals(p.getPermissionId())){
                            i.setPermissionName(i.getPermissionName() + p.getPermissionName() + " ");
                        }
                    }
                });
            });
        }
        return rpBOS;
    }

    @RequestMapping("role/add")
    @ResponseBody
    public BaseResponse insertRole(@RequestParam("roleId") Integer roleId, @RequestParam("roleName") String roleName){
        try {
            Role role = new Role();
            role.setRoleId(roleId);
            role.setRoleName(roleName);
            getUserListService.insertRole(role);
            return new BaseResponse(200,"success");

        }catch (Exception e){
            return new BaseResponse(500,e.getMessage());

        }
    }
    @RequestMapping("role/remove")
    @ResponseBody
    public int deleRole(@RequestParam("roleId") Integer roleId){
//        通过roleId ,删除userRole 表里的roleID；
            getUserListService.deleURByRId(roleId);
//            String roleName = getUserListService.findRoleNameByRoleId(roleId);
//            getUserListService.deleByRoleName(roleName);
            return getUserListService.deleByRoleId(roleId);


    }
//
    @RequestMapping("role/edit")
    public BaseResponse addByPermissionId(@RequestBody RolePermissionBO rolePermissionBO){

        getUserListService.deleRPByRId(rolePermissionBO.getRoleId());


        List<Integer> permissionIdList = rolePermissionBO.getPermissionIds();
        for(int i=0; i< permissionIdList.size(); i++){
            RolePermission rolePermission= new RolePermission();
            rolePermission.setRoleId(rolePermissionBO.getRoleId());
            rolePermission.setPermissionId(permissionIdList.get(i));
            getUserListService.addByPermissionId(rolePermission);
        }
        return new BaseResponse(200,"success");

    }

    @RequestMapping("user/batchremove")
    @ResponseBody
    public int  deleByUserIds(@RequestParam("userIds") List<Integer> userIds){
//        1.通过userids获取usernames
        List<User> users= getUserListService.findUsersByIds(userIds);
        List<String> userNames = users.stream().map(i -> i.getUserName()).collect(Collectors.toList());
//        2.通过usernames删除userRole里的数据
        getUserListService.deleByUNames(userNames);
        return getUserListService.deleByUserIds(userIds);
    }
//
//
    @RequestMapping("role/batchremove")
    @ResponseBody
    public int deleByRoleIds(@RequestParam("roleIds") List<Integer> roleIds){
////        List<Role> roles= getUserListService.findRsByIds(roleIds);
//        List<String> roleNames = roles.stream().map(i->i.getRoleName()).collect(Collectors.toList());

         getUserListService.deleURByRIds(roleIds);

        return getUserListService.deleByRoleIds(roleIds);

    }


}