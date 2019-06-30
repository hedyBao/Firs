package com.baobao.hellodemo.dao;

import com.baobao.hellodemo.entity.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoleMapper {
    List<Role> queryByRoleIdList(List<Integer> roleIdList);

    @Select("SELECT * FROM RoleRbac where 1=1")
    List<Role> queryAll();

    @Insert("INSERT INTO RoleRbac(roleId, roleName) VALUES(#{roleId}, #{roleName})")
    void insertRole(Role role);


    @Delete("DELETE FROM RoleRbac WHERE roleId = #{roleId} ")
    int deleByRoleId(Integer roleId);

    @Select("SELECT * FROM RoleRbac WHERE roleName=#{roleName}")
    Role findByRoleName(String roleName);


    @Select("SELECT * FROM RoleRbac")
    List<Role> getAllRoleName();


    int deleByRoleIds(List<Integer> roleIds);

    @Select("SELECT roleName FROM RoleRbac WHERE roleId=#{roleId}")
    String findRoleNameByRoleId(Integer roleId);

    List<Role> findRsByIds(List<Integer> roleIds);



}
