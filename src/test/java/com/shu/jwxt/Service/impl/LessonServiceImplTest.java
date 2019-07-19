package com.shu.jwxt.Service.impl;

import com.shu.jwxt.Service.LessonService;
import com.shu.jwxt.vo.LessonVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author yang
 * @date 2019/7/19 19:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LessonServiceImplTest {
    @Autowired
    private LessonService lessonService;
    @Test
    public void getLessonVos() {
        List<LessonVo> lessonVos = lessonService.getLessonVos(1, 3, 17723942);
    }
}