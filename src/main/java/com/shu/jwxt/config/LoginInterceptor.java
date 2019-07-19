package com.shu.jwxt.config;

import com.shu.jwxt.Service.UserService;
import com.shu.jwxt.redis.KeyPrefix;
import com.shu.jwxt.utils.CookieUtils;
import com.shu.jwxt.vo.UserVo;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.shu.jwxt.controller.LoginController.COOKIE_NAME;

/**
 * @author yang
 * @date 2019/7/19 14:10
 */
public class LoginInterceptor implements HandlerInterceptor {
    private final UserService userService;

    public LoginInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //已登录则直接回首页,防止重复登录
        String cookieValue = CookieUtils.getCookieValue(request, COOKIE_NAME);
        if (!StringUtils.isEmpty(cookieValue)) {
            UserVo cache = userService.getCache(KeyPrefix.USER_KEY.getKey() + cookieValue);
            if (cache != null) {
                response.sendRedirect("/home");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
