package com.shu.jwxt.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "jwxt.user_timetable")
public class UserTimetable {
    /**
     * 课表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 课程id
     */
    @Column(name = "lesson_id")
    private Integer lessonId;

    /**
     * 选课时间
     */
    @Column(name = "select_time")
    private Date selectTime;

    /**
     * 获取课表id
     *
     * @return id - 课表id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置课表id
     *
     * @param id 课表id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取课程id
     *
     * @return lesson_id - 课程id
     */
    public Integer getLessonId() {
        return lessonId;
    }

    /**
     * 设置课程id
     *
     * @param lessonId 课程id
     */
    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    /**
     * 获取选课时间
     *
     * @return select_time - 选课时间
     */
    public Date getSelectTime() {
        return selectTime;
    }

    /**
     * 设置选课时间
     *
     * @param selectTime 选课时间
     */
    public void setSelectTime(Date selectTime) {
        this.selectTime = selectTime;
    }
}