package com.shu.jwxt.Service.impl;

import com.shu.jwxt.Service.LessonService;
import com.shu.jwxt.Service.UserService;
import com.shu.jwxt.mapper.LessonMapper;
import com.shu.jwxt.mapper.SpMapper;
import com.shu.jwxt.mapper.UserTimetableMapper;
import com.shu.jwxt.redis.KeyPrefix;
import com.shu.jwxt.vo.LessonVo;
import com.shu.jwxt.vo.UserVo;
import org.springframework.stereotype.Service;

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

    public LessonServiceImpl(UserService userService, LessonMapper lessonMapper,
                             UserTimetableMapper userTimetableMapper, SpMapper spMapper) {
        this.userService = userService;
        this.lessonMapper = lessonMapper;
        this.userTimetableMapper = userTimetableMapper;
        this.spMapper = spMapper;
    }

    @Override
    public UserVo getUserVo(String s) {
        return userService.getCache(KeyPrefix.USER_KEY.getKey() + s);
    }

    @Override
    public LessonVo select(Integer userId) {
        LessonVo lessonVo = new LessonVo();
        return null;
    }

    @Override
    public List<LessonVo> getLessonVos(int pageNum, int pageSize, Integer userId) {
        int offset = (pageNum - 1) * pageSize;
        List<LessonVo> lessonVos = spMapper.listLessonVo(userId, offset, pageSize);
        return lessonVos;
    }
}
