package com.shu.jwxt.Service.impl;

import com.shu.jwxt.Service.UserService;
import com.shu.jwxt.entity.User;
import com.shu.jwxt.exception.GlobalException;
import com.shu.jwxt.mapper.UserMapper;
import com.shu.jwxt.result.CodeMsg;
import com.shu.jwxt.result.Result;
import com.shu.jwxt.utils.MD5Util;
import com.shu.jwxt.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public User login(UserVo userVo, String password) {
        User dbUser = findByUserId(userVo.getUserId());
        if (dbUser == null || !MD5Util.md5(password).equals(dbUser.getPassword())) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        return dbUser;
    }

    @Override
    public void register(UserVo uservo, String password) {
        if (findByUserId(uservo.getUserId()) != null) {
            throw new GlobalException(CodeMsg.USER_EXIST);
        }
        //加密
        String s = MD5Util.md5(password);
        User user = new User();
        user.setPassword(s);
        user.setUserId(uservo.getUserId());
        user.setUserName(uservo.getUserName());
        insert(user);
    }

    @Override
    public void insert(User user) {
        user.setCreatDate(new Date());
        userMapper.insert(user);
    }

    @Override
    public User findByUserId(Integer userId) {
        User user = new User();
        user.setUserId(userId);
        User dbUser = userMapper.selectOne(user);
        return dbUser;
    }

    @Override
    public void update(User user) {
        user.setUpdateDate(new Date());
        userMapper.updateByPrimaryKey(user);
    }
}
