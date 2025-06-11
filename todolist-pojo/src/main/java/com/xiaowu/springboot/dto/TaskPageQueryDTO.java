package com.xiaowu.springboot.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/30 23:58
 */
@Data
public class TaskPageQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private int page;
    private int size;
    private String name;
    private Long userId;
    private Long categoryId;
    private Integer status;

}
