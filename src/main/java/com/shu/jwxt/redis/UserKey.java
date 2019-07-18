package com.shu.jwxt.redis;

/**
 * @author yang
 * @date 2019/6/28 14:16
 */
public class UserKey extends BasePrefix {
    public static final Integer TOKEN_EXPIRE = 3600 * 24 * 2;

    private UserKey(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static UserKey token = new UserKey(TOKEN_EXPIRE, "token");
}
