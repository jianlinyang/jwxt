package com.shu.jwxt.controller;

import com.shu.jwxt.Service.UserService;
import com.shu.jwxt.entity.User;
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

    public static final String TOKEN = "token";
    public static final Integer COOKIE_MAX_AGE = 24 * 3600 * 14;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public Result login(HttpServletRequest request, HttpServletResponse response,
                        UserVo uservo, @RequestParam String password) {
        //先从缓存拿
        String cookieValue = CookieUtils.getCookieValue(request, TOKEN);
        if (!StringUtils.isEmpty(cookieValue)) {
            UserVo cache = userService.getCache(cookieValue);
            if (cache != null) {
                return Result.success(cache);
            }
        }
        //缓存没有再去数据库拿
        User login = userService.login(uservo, password);
        uservo.setUserName(login.getUserName());
        //拿到后放入缓存
        String uuid = UUIDUtil.uuid();
        CookieUtils.setCookie(request, response, TOKEN, uuid, COOKIE_MAX_AGE);
        userService.setCache(uuid, uservo, Long.valueOf(COOKIE_MAX_AGE));
        return Result.success(uservo);
    }

    @PostMapping("/register")
    public Result register(UserVo uservo, @RequestParam String password) {
        userService.register(uservo, password);
        return Result.success();
    }
}
