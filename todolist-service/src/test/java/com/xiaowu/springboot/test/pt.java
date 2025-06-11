package com.xiaowu.springboot.test;

import com.xiaowu.springboot.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/12/23 23:44
 */
@SpringBootTest
public class pt {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        String encode1 = bCryptPasswordEncoder.encode("123456");

        System.out.println(encode);
        System.out.println(encode1);
    }
}
