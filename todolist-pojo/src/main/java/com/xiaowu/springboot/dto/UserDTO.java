package com.xiaowu.springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaowu.springboot.domain.TaskLabel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/28 0:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    private String username;
    private String email;
    private String phone;
    private String sex;
    private String password;
    private String avatarUrl;//头像路径
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    private String address;
    private String signature;//签名
    private String university;
    private String secondarySchool;
    private String elementarySchool;
    private String company;
    private String introduce;
    private String major;
}
