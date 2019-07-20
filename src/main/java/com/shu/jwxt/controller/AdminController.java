package com.shu.jwxt.controller;

import com.shu.jwxt.Service.AdminService;
import com.shu.jwxt.entity.Admin;
import com.shu.jwxt.redis.KeyPrefix;
import com.shu.jwxt.result.Result;
import com.shu.jwxt.utils.CookieUtils;
import com.shu.jwxt.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yang
 * @date 2019/7/20 13:01
 */
@RestController
@Slf4j
//@Api("管理员相关Api")
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private static final Integer COOKIE_MAX_AGE = 24 * 3600 * 14;
    public static final String COOKIE_NAME = "admin";

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

//    @ApiOperation(value = "管理员登录", notes = "管理员登录")
    @PostMapping("/login")
    public Result login(HttpServletRequest request, HttpServletResponse response,
                        String userName, String password) {
        Admin login = adminService.login(userName, password);
        //拿到后放入缓存
        String uuid = UUIDUtil.uuid();
        CookieUtils.setCookie(request, response, COOKIE_NAME, uuid, COOKIE_MAX_AGE);
        adminService.setCache(KeyPrefix.ADMIN_KEY.getKey() + uuid, userName, Long.valueOf(COOKIE_MAX_AGE));
        log.info("管理员:{}通过数据库登录成功", userName);
        return Result.success(userName);
    }

//    @ApiOperation(value = "添加课程容量", notes = "添加课程容量")
    @PutMapping("/upDateLesson")
    public Result upDateLesson(int lessonId, int num) {
        adminService.upDateLesson(lessonId, num);
        log.info("管理员:更新课程{}成功", lessonId);
        return Result.success();
    }
}
