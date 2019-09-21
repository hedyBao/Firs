package com.tomas.hellodemo.dao;

import com.tomas.hellodemo.entity.Permission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface PermissionMapper {

    List<Permission> queryByPermissionIdList(List<Integer> permissionIdList);

    @Select("SELECT * FROM PermissionRbac where 1=1")
    List<Permission> queryAllPermissions();

    @Insert("INSERT INTO PermissionRbac(permissionId, permissionName, url) VALUES(#{permissionId}, #{permissionName}, #{url})")
    void addPermission(Permission permission);

    @Select("SELECT * FROM PermissionRbac where 1=1")
    List<Permission> AllPermissions();

    @Delete("DELETE FROM PermissionRbac WHERE permissionId = #{permissionId} ")
    void deleByPermissionId(Integer permissionId);


    void deleByPIds(List<Integer> permissionIds);

    @Update("UPDATE PermissionRbac SET permissionId=#{permissionId},permissionName = #{permissionName},url = #{url} WHERE permissionId=#{permissionId}")
    void updateByPId(Permission permission);

    @Insert("INSERT INTO PermissionRbac(url) VALUES(#{url})")
    void addUrl(String url);


    @Select("SELECT * FROM PermissionRbac where permissionName = #{permissionName}")
    Permission findByPermissionName(String permissionName);

    @Select("SELECT permissionName FROM PermissionRbac where permissionId = #{permissionId}")
    String findPermissionNameByPermissionId(Integer permissionId);





}
