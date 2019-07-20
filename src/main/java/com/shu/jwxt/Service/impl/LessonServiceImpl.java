package com.shu.jwxt.Service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shu.jwxt.Service.LessonService;
import com.shu.jwxt.Service.UserService;
import com.shu.jwxt.entity.Lesson;
import com.shu.jwxt.entity.UserTimetable;
import com.shu.jwxt.exception.GlobalException;
import com.shu.jwxt.mapper.LessonMapper;
import com.shu.jwxt.mapper.SpMapper;
import com.shu.jwxt.mapper.UserTimetableMapper;
import com.shu.jwxt.rabbitmq.MQSender;
import com.shu.jwxt.redis.KeyPrefix;
import com.shu.jwxt.redis.RedisService;
import com.shu.jwxt.result.CodeMsg;
import com.shu.jwxt.vo.LessonVo;
import com.shu.jwxt.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author yang
 * @date 2019/7/19 15:52
 */
@Service
public class LessonServiceImpl implements LessonService {

    private final UserService userService;
    private final LessonMapper lessonMapper;
    private final UserTimetableMapper userTimetableMapper;
    private final SpMapper spMapper;
    private final MQSender mqSender;
    private final RedisService redisService;

    public LessonServiceImpl(UserService userService, LessonMapper lessonMapper,
                             UserTimetableMapper userTimetableMapper, SpMapper spMapper, MQSender mqSender, RedisService redisService) {
        this.userService = userService;
        this.lessonMapper = lessonMapper;
        this.userTimetableMapper = userTimetableMapper;
        this.spMapper = spMapper;
        this.mqSender = mqSender;
        this.redisService = redisService;
    }

    @Override
    public UserVo getUserVo(String s) {
        return userService.getCache(KeyPrefix.USER_KEY.getKey() + s);
    }

    @Override
    public List<LessonVo> getLessonVos(int pageNum, int pageSize, Integer userId) {
        int offset = (pageNum - 1) * pageSize;
        List<LessonVo> lessonVos = spMapper.listLessonVo(userId, offset, pageSize);
        return lessonVos;
    }

    @Override
    public List<Lesson> selectAllLesson(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, "lesson_id");
        List<Lesson> lessons = lessonMapper.selectAll();
        PageInfo<Lesson> pageInfo = new PageInfo<>(lessons);
        return pageInfo.getList();
    }

    @Override
    public void select(Integer userId, int lessonId) {
        //检查是否重复抢课
        if (!checkLesson(userId, lessonId)) {
            throw new GlobalException(CodeMsg.REPEATE_MIAO_SHA);
        }
        //检查库存
        if (!getLessonOver(lessonId)) {
            throw new GlobalException(CodeMsg.MIAO_SHA_OVER);
        }
        //预减库存
        redisService.decrease(KeyPrefix.LESSON_KEY.getKey() + lessonId);
        String msg = userId + ":" + lessonId;
        mqSender.send(msg);
    }

    @Override
    public boolean checkLesson(Integer userId, int lessonId) {
        UserTimetable userTimetable = new UserTimetable();
        userTimetable.setUserId(userId);
        userTimetable.setLessonId(lessonId);
        UserTimetable userTimetable1 = userTimetableMapper.selectOne(userTimetable);
        return userTimetable1 == null;
    }

    @Override
    public Integer getResult(Integer userId, int lessonId) {
        if (!checkLesson(userId, lessonId)) {
            return lessonId;
        } else {
            boolean isOver = getLessonOver(lessonId);
            if (!isOver) {//抢卖完了
                return -1;
            } else {        //正在抢
                return 0;
            }
        }
    }

    @Override
    public boolean getLessonOver(int lessonId) {
        String s = redisService.get(KeyPrefix.LESSON_KEY.getKey() + lessonId);
        if(StringUtils.isEmpty(s)) {
            return false;
        }
        return Integer.valueOf(s) > 0;
    }
}
