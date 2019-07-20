package com.shu.jwxt.rabbitmq;

import com.shu.jwxt.Service.LessonService;
import com.shu.jwxt.entity.Lesson;
import com.shu.jwxt.entity.UserTimetable;
import com.shu.jwxt.mapper.LessonMapper;
import com.shu.jwxt.mapper.UserTimetableMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author yang
 * @date 2019/6/29 21:16
 */
@Service
@Slf4j
public class MQReceiver {

    private final LessonService lessonService;
    private final UserTimetableMapper userTimetableMapper;
    private final LessonMapper lessonMapper;

    public MQReceiver(LessonService lessonService, UserTimetableMapper userTimetableMapper, LessonMapper lessonMapper) {
        this.lessonService = lessonService;
        this.userTimetableMapper = userTimetableMapper;
        this.lessonMapper = lessonMapper;
    }

    @RabbitListener(queues = MQConfig.QUEUE)//指明监听的是哪一个queue
    public void receive(String message) {
        log.info("receive message:" + message);
        String[] split = message.split(":");
        Integer userId = Integer.valueOf(split[0]);
        Integer lessonId = Integer.valueOf(split[1]);
        UserTimetable userTimetable = new UserTimetable();
        userTimetable.setUserId(userId);
        userTimetable.setLessonId(lessonId);
        //再次检查是否重复抢课
        if (!lessonService.checkLesson(userId, lessonId)) {
            return;
        }
        Lesson lesson = new Lesson();
        lesson.setLessonId(lessonId);
        Lesson lesson1 = lessonMapper.selectOne(lesson);
        //再次检查课程余量
        if (lesson1.getCapacity() < 1) {
            return;
        }
        lesson1.setCapacity(lesson1.getCapacity() - 1);
        //抢课成功更新
        userTimetable.setSelectTime(new Date());
        lessonMapper.updateByPrimaryKey(lesson1);
        userTimetableMapper.insert(userTimetable);
        log.info("用户:{}抢课:{}成功", userId, lessonId);
    }
}
