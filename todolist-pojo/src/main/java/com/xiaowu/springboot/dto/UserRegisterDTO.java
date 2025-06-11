package com.xiaowu.springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/27 20:01
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDTO implements Serializable {
    private String username;
    private String email;
    private String phone;
    private String sex;
    private String password;
    private String avatarUrl;//头像路径
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
}
