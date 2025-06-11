package com.xiaowu.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/27 17:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginDTO implements Serializable {

    private String username;
    private String password;
    private String captcha;      // 用户输入的验证码
    private String captchaCode;  // 验证码唯一标识（比如 UUID）

}
