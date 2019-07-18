package com.shu.jwxt.controller;

import com.shu.jwxt.Service.UserService;
import com.shu.jwxt.entity.User;
import com.shu.jwxt.result.CodeMsg;
import com.shu.jwxt.result.Result;
import com.shu.jwxt.utils.MD5Util;
import com.shu.jwxt.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public Result login(HttpServletRequest request, HttpServletResponse response,
                        UserVo uservo, @RequestParam String password) {
        User login = userService.login(uservo, password);
        uservo.setUserName(login.getUserName());
        return Result.success(login.getUserName());
    }

    @PostMapping("/register")
    public Result register(UserVo uservo, @RequestParam String password) {
        userService.register(uservo, password);
        return Result.success();
    }
}
