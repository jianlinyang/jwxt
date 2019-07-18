package com.shu.jwxt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.shu.jwxt.mapper")
public class JwxtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwxtApplication.class, args);
    }

}
