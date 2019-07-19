package com.shu.jwxt.redis;

import com.shu.jwxt.utils.MapperUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yang
 * @date 2019/6/28 0:18
 */
@Component
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(String key, Object object, Long seconds) {
        String s = "";
        try {
            s = MapperUtils.obj2json(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisTemplate.opsForValue().set(key, s, seconds, TimeUnit.SECONDS);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String s) {
        redisTemplate.delete(s);
    }
}
