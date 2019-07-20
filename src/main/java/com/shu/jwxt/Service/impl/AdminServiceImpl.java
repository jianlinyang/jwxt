package com.shu.jwxt.Service.impl;

import com.shu.jwxt.Service.AdminService;
import com.shu.jwxt.entity.Admin;
import com.shu.jwxt.entity.Lesson;
import com.shu.jwxt.exception.GlobalException;
import com.shu.jwxt.mapper.AdminMapper;
import com.shu.jwxt.mapper.LessonMapper;
import com.shu.jwxt.redis.KeyPrefix;
import com.shu.jwxt.redis.RedisService;
import com.shu.jwxt.result.CodeMsg;
import com.shu.jwxt.utils.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author yang
 * @date 2019/7/20 13:04
 */
@Service
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;
    private final RedisService redisService;
    private final LessonMapper lessonMapper;

    public AdminServiceImpl(AdminMapper adminMapper, RedisService redisService, LessonMapper lessonMapper) {
        this.adminMapper = adminMapper;
        this.redisService = redisService;
        this.lessonMapper = lessonMapper;
    }


    @Override
    public Admin login(String userName, String password) {
        Admin admin = new Admin();
        admin.setUserName(userName);
        Admin dbUser = adminMapper.selectOne(admin);
        if (dbUser == null || !MD5Util.md5(password).equals(dbUser.getPassword())) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        return dbUser;
    }

    @Override
    public void setCache(String s, String userName, Long valueOf) {
        redisService.set(s, userName, valueOf);
    }

    @Override
    public String getCache(String cookieValue) {
        String s = redisService.get(cookieValue);
        if (!StringUtils.isEmpty(s)) {
            return s;
        }
        return null;
    }

    @Override
    @Transactional
    public void upDateLesson(int lessonId, int num) {
        Lesson lesson = new Lesson();
        lesson.setLessonId(lessonId);
        Lesson lesson1 = lessonMapper.selectOne(lesson);
        lesson1.setCapacity(lesson1.getCapacity()+num);
        redisService.set(KeyPrefix.LESSON_KEY.getKey()+lessonId,lesson1.getCapacity());
        lessonMapper.updateByPrimaryKey(lesson1);
    }
}
