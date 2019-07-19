package com.shu.jwxt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author yang
 * @date 2019/7/19 15:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonVo {

    /**
     * 课程名
     */
    private String lessonName;

    /**
     * 任课老师
     */
    private String lessonTeacher;

    /**
     * 开课时间
     */
    private Date startTime;

    /**
     * 结课时间
     */
    private Date endTime;
}
