package com.shu.jwxt.config;

import com.shu.jwxt.Service.AdminService;
import com.shu.jwxt.exception.GlobalException;
import com.shu.jwxt.redis.KeyPrefix;
import com.shu.jwxt.result.CodeMsg;
import com.shu.jwxt.utils.CookieUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.shu.jwxt.controller.AdminController.COOKIE_NAME;

/**
 * @author yang
 * @date 2019/7/20 13:32
 */
@Component
public class AdminInterceptor implements HandlerInterceptor {
    private final AdminService adminService;

    public AdminInterceptor(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String cookieValue = CookieUtils.getCookieValue(request, COOKIE_NAME);
        if (!StringUtils.isEmpty(cookieValue)) {
            String cache = adminService.getCache(KeyPrefix.ADMIN_KEY.getKey() + cookieValue);
            if (!StringUtils.isEmpty(cache)) {
                return true;
            }
        }
        throw new GlobalException(CodeMsg.SESSION_ERROR);
    }
}
