package com.baobao.hellodemo.dao;

import com.baobao.hellodemo.entity.RolePermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface RolePermissionMapper {

    List<RolePermission> queryByRoleIds(List<Integer> roleIdList);

    @Insert("INSERT INTO RolePermissionRbac(id,roleId, permissionId) VALUES(null, #{roleId}, #{permissionId})")
    void addByPermissionId(RolePermission rolePermission);

    @Delete("DELETE FROM RolePermissionRbac where roleId = #{roleId}")
    int deleByRoleName(String roleName);


    int deleByRNames(List<String> roleNames);


    @Delete("DELETE FROM RolePermissionRbac where roleId = #{roleId}")
    int deleRPByRId(Integer roleId);




}
