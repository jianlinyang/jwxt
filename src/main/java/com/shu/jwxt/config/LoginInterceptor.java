package com.shu.jwxt.config;

import com.shu.jwxt.Service.UserService;
import com.shu.jwxt.exception.GlobalException;
import com.shu.jwxt.redis.KeyPrefix;
import com.shu.jwxt.result.CodeMsg;
import com.shu.jwxt.utils.CookieUtils;
import com.shu.jwxt.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.shu.jwxt.controller.LoginController.COOKIE_NAME;

/**
 * @author yang
 * @date 2019/7/19 14:47
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    private final UserService userService;

    public LoginInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String cookieValue = CookieUtils.getCookieValue(request, COOKIE_NAME);
        if (!StringUtils.isEmpty(cookieValue)) {
            UserVo cache = userService.getCache(KeyPrefix.USER_KEY.getKey() + cookieValue);
            if (cache != null) {
                log.info("您已登录");
                throw new GlobalException(CodeMsg.HAS_LOGIN);
            }
        }
        return true;
    }
}
