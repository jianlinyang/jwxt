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
    List<LessonVo> listLessonVo( int userId, int offset,  int pageSize);
}
