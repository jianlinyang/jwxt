package com.shu.jwxt.Service;

import com.shu.jwxt.entity.Admin;

/**
 * @author yang
 * @date 2019/7/20 13:04
 */
public interface AdminService {
    Admin login(String userName, String password);

    void  setCache(String s, String userName, Long valueOf);

    String getCache(String cookieValue);

    void upDateLesson(int lessonId, int num);
}
