package com.xiaowu.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/29 0:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginLog {

    private Long uid;
    private String token;
    private String role;
}
