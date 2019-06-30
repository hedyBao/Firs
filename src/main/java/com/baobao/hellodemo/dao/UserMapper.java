package com.baobao.hellodemo.dao;

import com.baobao.hellodemo.entity.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM UserRbac")
    List<User> getAllUserName();

    @Insert("INSERT INTO UserRbac(userName,passWord,phone) VALUES(#{userName},#{passWord},#{phone})")
    public int insertUser(User user);


    @Delete("DELETE FROM UserRbac WHERE userId=#{userId}" )
    public int deleteUser(Integer userId);

    @Select("SELECT * FROM UserRbac WHERE userName=#{userName}")
    public User findByUserName(String userName);


    int deleByUserIds(List<Integer> userIds);

    @Select("SELECT userName FROM UserRbac WHERE userId=#{userId}")
    String findUserNameByUserId(Integer userId);


//    List<String> findUNamesByUIds(List<Integer> userIds);

    List<User> findUsersByIds(List<Integer> userIds);


}
