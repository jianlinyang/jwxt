package com.shu.jwxt.Service.impl;

import com.shu.jwxt.Service.UserService;
import com.shu.jwxt.entity.User;
import com.shu.jwxt.exception.GlobalException;
import com.shu.jwxt.mapper.UserMapper;
import com.shu.jwxt.redis.RedisService;
import com.shu.jwxt.result.CodeMsg;
import com.shu.jwxt.utils.MD5Util;
import com.shu.jwxt.utils.MapperUtils;
import com.shu.jwxt.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author yang
 * @date 2019/7/18 13:43
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final RedisService redisService;


    public UserServiceImpl(UserMapper userMapper, RedisService redisService) {
        this.redisService = redisService;
        this.userMapper = userMapper;
    }

    @Override
    public UserVo getCache(String cookieValue) {
        String s = redisService.get(cookieValue);
        UserVo userVo = null;
        if (!StringUtils.isEmpty(s)) {
            try {
                userVo = MapperUtils.json2pojo(s, UserVo.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userVo;
    }

    @Override
    public void setCache(String token, UserVo uservo, Long cookieMaxAge) {
        redisService.set(token, uservo, cookieMaxAge);
    }

    @Override
    public User login(Integer userId, String password) {
        User dbUser = findByUserId(userId);
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
    public void delete(String s) {
        redisService.delete(s);
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
