package com.shu.jwxt.config;

import com.shu.jwxt.Service.UserService;
import com.shu.jwxt.controller.LoginController;
import com.shu.jwxt.exception.GlobalException;
import com.shu.jwxt.redis.KeyPrefix;
import com.shu.jwxt.result.CodeMsg;
import com.shu.jwxt.utils.CookieUtils;
import com.shu.jwxt.vo.UserVo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yang
 * @date 2019/7/19 13:22
 */
@Component
public class AccessInterceptor implements HandlerInterceptor {
    private final UserService userService;

    public AccessInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String cookieValue = CookieUtils.getCookieValue(request, LoginController.COOKIE_NAME);
        //cookie为空请先登录
        if (StringUtils.isEmpty(cookieValue)) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        UserVo cache = userService.getCache(KeyPrefix.USER_KEY.getKey() + cookieValue);
        if (cache == null) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        } else {
            //已登录则跳转至首页
            response.sendRedirect("/hasLogin");
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
