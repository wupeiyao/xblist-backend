package com.xiaowu.springboot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/27 18:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginVO implements Serializable {

    private Long id;
    private String token;
    private String username;
    private String avatarUrl;//头像路径

}
