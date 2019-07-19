package com.shu.jwxt.controller;

import com.shu.jwxt.Service.LessonService;
import com.shu.jwxt.entity.Lesson;
import com.shu.jwxt.result.Result;
import com.shu.jwxt.utils.CookieUtils;
import com.shu.jwxt.vo.LessonVo;
import com.shu.jwxt.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yang
 * @date 2019/7/19 15:51
 */
@RestController
@Slf4j
@RequestMapping("/home")
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/myLesson")
    public Result myLesson(HttpServletRequest request, int pageNum, int pageSize) {
        UserVo userVo = lessonService.getUserVo(CookieUtils.getCookieValue(request, LoginController.COOKIE_NAME));
        List<LessonVo> lessonVos = lessonService.getLessonVos(pageNum, pageSize, userVo.getUserId());
        return Result.success(lessonVos);
    }

    @GetMapping("/listLesson")
    public Result listLesson(int pageNum, int pageSize) {
        List<Lesson> lessons = lessonService.selectAllLesson(pageNum, pageSize);
        return Result.success(lessons);
    }
}
