package com.shu.jwxt.controller;

import com.shu.jwxt.Service.UserService;
import com.shu.jwxt.entity.User;
import com.shu.jwxt.redis.KeyPrefix;
import com.shu.jwxt.result.CodeMsg;
import com.shu.jwxt.result.Result;
import com.shu.jwxt.utils.CookieUtils;
import com.shu.jwxt.utils.UUIDUtil;
import com.shu.jwxt.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yang
 * @date 2019/7/18 12:55
 */
@RestController
@Slf4j
public class LoginController {
    private final UserService userService;
    private static final Integer COOKIE_MAX_AGE = 24 * 3600 * 14;
    public static final String COOKIE_NAME = "token";

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public Result login(HttpServletRequest request, HttpServletResponse response,
                        UserVo uservo, @RequestParam String password) {
//        UserVo cache = cacheCheck(request);
//        if (cache != null) {
//            return Result.success(cache);
//        }
        User login = userService.login(uservo, password);
        uservo.setUserName(login.getUserName());
        //拿到后放入缓存
        String uuid = UUIDUtil.uuid();
        CookieUtils.setCookie(request, response, COOKIE_NAME, uuid, COOKIE_MAX_AGE);
        userService.setCache(KeyPrefix.USER_KEY.getKey() + uuid, uservo, Long.valueOf(COOKIE_MAX_AGE));
        log.info("用户:{}通过数据库登录成功", uservo.getUserId());
        return Result.success(uservo);
    }

    @PostMapping("/register")
    public Result register(UserVo uservo, @RequestParam String password) {
        userService.register(uservo, password);
        log.info("用户:{}注册成功", uservo.getUserId());
        return Result.success();
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        //先从缓存拿
        String cookieValue = CookieUtils.getCookieValue(request, COOKIE_NAME);
        if (!StringUtils.isEmpty(cookieValue)) {
            //删除缓存
            CookieUtils.deleteCookie(request, response, COOKIE_NAME);
            UserVo userVo = userService.getCache(KeyPrefix.USER_KEY.getKey() +cookieValue);
            userService.delete(KeyPrefix.USER_KEY.getKey() + cookieValue);
            log.info("用户:{}注销成功", userVo.getUserId());
        }
        return Result.success();
    }

    @GetMapping("/hasLogin")
    public Result hasLogin(HttpServletRequest request) {
        UserVo cache = cacheCheck(request);
        if (cache != null) {
            return Result.success(cache);
        }
        return Result.error(CodeMsg.SESSION_ERROR);
    }

    private UserVo cacheCheck(HttpServletRequest request) {
        String cookieValue = CookieUtils.getCookieValue(request, COOKIE_NAME);
        if (!StringUtils.isEmpty(cookieValue)) {
            UserVo cache = userService.getCache(KeyPrefix.USER_KEY.getKey() + cookieValue);
            if (cache != null) {
                log.info("用户:{}通过缓存登录成功", cache.getUserId());
                return cache;
            }
        }
        return null;
    }
}
