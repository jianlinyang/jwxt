package com.shu.jwxt.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "jwxt.lesson")
public class Lesson {
    /**
     * 课程id
     */
    @Id
    @Column(name = "lesson_id")
    private Integer lessonId;

    /**
     * 课程名
     */
    @Column(name = "lesson_name")
    private String lessonName;

    /**
     * 任课老师
     */
    @Column(name = "lesson_teacher")
    private String lessonTeacher;

    /**
     * 可选人数
     */
    private Integer capacity;

    /**
     * 开课时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 结课时间
     */
    @Column(name = "end_time")
    private Date endTime;

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
     * 获取课程名
     *
     * @return lesson_name - 课程名
     */
    public String getLessonName() {
        return lessonName;
    }

    /**
     * 设置课程名
     *
     * @param lessonName 课程名
     */
    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    /**
     * 获取任课老师
     *
     * @return lesson_teacher - 任课老师
     */
    public String getLessonTeacher() {
        return lessonTeacher;
    }

    /**
     * 设置任课老师
     *
     * @param lessonTeacher 任课老师
     */
    public void setLessonTeacher(String lessonTeacher) {
        this.lessonTeacher = lessonTeacher;
    }

    /**
     * 获取可选人数
     *
     * @return capacity - 可选人数
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * 设置可选人数
     *
     * @param capacity 可选人数
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * 获取开课时间
     *
     * @return start_time - 开课时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开课时间
     *
     * @param startTime 开课时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结课时间
     *
     * @return end_time - 结课时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结课时间
     *
     * @param endTime 结课时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}