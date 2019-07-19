package com.shu.jwxt.Service;

import com.shu.jwxt.entity.User;
import com.shu.jwxt.vo.UserVo;

/**
 * @author yang
 * @date 2019/7/18 13:42
 */
public interface UserService {
    void insert(User user);

    User findByUserId(Integer userId);

    void update(User user);

    User login(UserVo userVo,String password);

    void register(UserVo uservo, String password);

    UserVo getCache(String cookieValue);

    void setCache(String token, UserVo uservo, Long cookieMaxAge);

    void delete(String s);
}
