package com.shu.jwxt.Service.impl;

import com.shu.jwxt.Service.UserService;
import com.shu.jwxt.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author yang
 * @date 2019/7/18 13:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserService userService;
    @Test
    public void insert() {
        User user = new User();
        user.setUserId(17723942);
        user.setUserName("yang");
        user.setPassword("yyyy13");
        user.setCreatDate(new Date());
        userService.insert(user);
    }
}