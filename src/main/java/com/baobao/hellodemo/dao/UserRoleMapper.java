package com.baobao.hellodemo.dao;

import com.baobao.hellodemo.entity.UserRole;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper

public interface UserRoleMapper {


    List<UserRole>  queryByUserNameList( List<String> userNameList);

    @Insert("INSERT INTO UserRoleRbac VALUES(null,#{userName},#{roleId})")
    int addByRoleId(UserRole userRole);

    @Delete("DELETE FROM UserRoleRbac WHERE userName=#{userName} ")
    int deleByUserName(String userName);


    int deleByUNames(List<String> userName);

    @Delete("DELETE FROM UserRoleRbac WHERE roleId=#{roleId} ")
    int deleURByRId(Integer roleId);


    int deleURByRIds(List<Integer> roleIds);


    @Select("SELECT roleId FROM UserRoleRbac WHERE userName=#{userName}")
    List<Integer> findRIdsByUName(String userName);





}
