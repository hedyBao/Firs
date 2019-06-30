package com.baobao.hellodemo.Test;

import com.baobao.hellodemo.entity.User;
import com.baobao.hellodemo.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testQuery() throws Exception{
        List<User> users = userMapper.getAllUserName();
        System.out.println(users.toString());

    }

}
