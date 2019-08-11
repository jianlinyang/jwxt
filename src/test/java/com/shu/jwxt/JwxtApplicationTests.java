package com.shu.jwxt;

import com.shu.jwxt.Service.LessonService;
import com.shu.jwxt.vo.LessonVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwxtApplicationTests {
    @Autowired
    private LessonService lessonService;

    @Test
    public void getLessonVos() {
        List<LessonVo> lessonVos = lessonService.getLessonVos(1, 3, 17723942);
    }
}
