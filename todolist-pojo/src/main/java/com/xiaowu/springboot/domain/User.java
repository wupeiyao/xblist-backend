package com.xiaowu.springboot.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @time: 2024/9/23 19:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
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
   private String role;
}
