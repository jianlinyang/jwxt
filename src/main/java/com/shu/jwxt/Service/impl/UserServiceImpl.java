package com.shu.jwxt.Service.impl;

import com.shu.jwxt.Service.UserService;
import com.shu.jwxt.entity.User;
import com.shu.jwxt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yang
 * @date 2019/7/18 13:43
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }
}
