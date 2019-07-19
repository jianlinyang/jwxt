package com.shu.jwxt.Service;

import com.shu.jwxt.entity.Lesson;
import com.shu.jwxt.vo.LessonVo;
import com.shu.jwxt.vo.UserVo;

import java.util.List;

/**
 * @author yang
 * @date 2019/7/19 15:52
 */
public interface LessonService {

    UserVo getUserVo(String s)  ;

    List<LessonVo> getLessonVos(int pageNum, int pageSize, Integer userId);

    List<Lesson> selectAllLesson(int pageNum, int pageSize);
}
