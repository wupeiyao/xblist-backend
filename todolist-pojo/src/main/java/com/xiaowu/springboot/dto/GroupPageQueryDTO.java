package com.xiaowu.springboot.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/30 14:49
 */
@Data
public class GroupPageQueryDTO implements Serializable {

    private Integer page;
    private Integer size;
    private String name;

    private Long userId;

    private Long categoryId;
    private Integer status;
}
