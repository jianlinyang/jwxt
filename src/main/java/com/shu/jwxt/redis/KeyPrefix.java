package com.shu.jwxt.redis;

/**
 * @author yang
 * @date 2019/7/19 12:37
 */
public enum KeyPrefix {
    /**
     * RedisKey前缀
     */
    USER_KEY("userKey:"),
    ADMIN_KEY("adminKey:"),
    LESSON_KEY("lessonKey:")
    ;
    private String key;

    KeyPrefix(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
