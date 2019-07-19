package com.shu.jwxt.mapper;

import com.shu.jwxt.vo.LessonVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yang
 * @date 2019/7/19 18:00
 */
@Mapper
public interface SpMapper {

//    @Select("SELECT l.lesson_name,l.lesson_teacher,l.start_time,l.end_time from lesson as l " +
//            "WHERE lesson_id in (SELECT user_timetable.lesson_id FROM user_timetable WHERE user_id = #{userId})" +
//            "ORDER BY l.lesson_id LIMIT #{offset},#{pageSize}")
//    List<LessonVo> listLessonVo(@Param("userId") int userId, @Param("offset") int offset, @Param("pageSize") int pageSize);
    List<LessonVo> listLessonVo( int userId, int offset,  int pageSize);
}
