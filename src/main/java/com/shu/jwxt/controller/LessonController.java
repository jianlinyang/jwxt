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
//@Api("课程相关Api")
@RequestMapping("/home")
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    //    @ApiOperation(value = "查询已选课程", notes = "查询已选课程")
    @GetMapping("/myLesson")
    public Result myLesson(HttpServletRequest request,
                           @RequestParam int pageNum, @RequestParam int pageSize) {
        UserVo userVo = lessonService.getUserVo(CookieUtils.getCookieValue(request, LoginController.COOKIE_NAME));
        List<LessonVo> lessonVos = lessonService.getLessonVos(pageNum, pageSize, userVo.getUserId());
        return Result.success(lessonVos);
    }

    //    @ApiOperation(value = "查询所有课程", notes = "查询所有课程")
    @GetMapping("/listLesson")
    public Result listLesson(@RequestParam int pageNum, @RequestParam int pageSize) {
        List<Lesson> lessons = lessonService.selectAllLesson(pageNum, pageSize);
        return Result.success(lessons);
    }

    //    @ApiOperation(value = "选课", notes = "选课")
    @PostMapping("/selectLesson")
    public Result listLesson(HttpServletRequest request, @RequestParam int lessonId) {
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
//    @ApiOperation(value = "查询选课结果", notes = "查询选课结果")
    @GetMapping("/result")
    public Result selectLessonResult(HttpServletRequest request, @RequestParam int lessonId) {
        UserVo userVo = lessonService.getUserVo(CookieUtils.getCookieValue(request, LoginController.COOKIE_NAME));
        Integer res = lessonService.getResult(userVo.getUserId(), lessonId);
        return Result.success(res);
    }
}
