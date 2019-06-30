package com.baobao.hellodemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

@MapperScan(basePackages = "com.baobao.hellodemo.dao")
public class HelloDemoApplicaiton {
    public static void main(String[] args) {
        SpringApplication.run(HelloDemoApplicaiton.class, args);
    }
}

