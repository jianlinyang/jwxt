package com.shu.jwxt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang
 * @date 2019/7/16 21:28
 */
@RestController
@Slf4j
public class TestController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/")
    public String res() {
        redisTemplate.opsForValue().set("hello", "hello");
        return "hello";
    }
}

