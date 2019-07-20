package com.shu.jwxt.controller;

import com.shu.jwxt.Service.LessonService;
import com.shu.jwxt.entity.Lesson;
import com.shu.jwxt.result.Result;
import com.shu.jwxt.utils.CookieUtils;
import com.shu.jwxt.vo.LessonVo;
import com.shu.jwxt.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/selectLesson")
    public Result listLesson(HttpServletRequest request,int lessonId){
        UserVo userVo = lessonService.getUserVo(CookieUtils.getCookieValue(request, LoginController.COOKIE_NAME));
        lessonService.select(userVo.getUserId(), lessonId);
        return Result.success();
    }

    /**
     * 客户端做一个轮询，查看是否成功与失败，失败了则不用继续轮询。
     * 秒杀成功，返回订单的Id。
     * 库存不足直接返回-1。
     * 排队中则返回0。
     * 查看是否生成秒杀订单。
     */
    @GetMapping("/result")
    @ResponseBody
    public Result selectLessonResult(HttpServletRequest request,int lessonId) {
        UserVo userVo = lessonService.getUserVo(CookieUtils.getCookieValue(request, LoginController.COOKIE_NAME));
        Integer res= lessonService.getResult(userVo.getUserId(), lessonId);
        return Result.success(res);
    }
}
