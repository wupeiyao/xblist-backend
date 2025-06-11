package com.xiaowu.springboot;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.xiaowu.springboot")
@EnableTransactionManagement
@EnableCaching//开启注解缓存功能
@EnableScheduling
@Slf4j
@MapperScan("com.xiaowu.springboot.mapper")
public class JavaTodolistApplication {
    public static void main(String[] args) {
        log.info("你好，吴培垚");
        ConfigurableApplicationContext run = SpringApplication.run(JavaTodolistApplication.class, args);
        System.out.println("" + JavaTodolistApplication.class);
    }
}
