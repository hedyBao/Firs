package com.tomas.hellodemo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.tomas.EmbeddedZooKeeper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication

@MapperScan(basePackages = "com.tomas.hellodemo.dao")
public class HelloDemoApplicaiton {
    public static void main(String[] args) {
        new EmbeddedZooKeeper(2181, false).start();
        SpringApplication.run(HelloDemoApplicaiton.class, args);
    }
}

