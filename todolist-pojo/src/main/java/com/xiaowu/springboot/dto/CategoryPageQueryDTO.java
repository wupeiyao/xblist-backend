package com.xiaowu.springboot.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/30 19:04
 */
@Data
public class CategoryPageQueryDTO implements Serializable {
    private int page;

    //每页记录数
    private int pageSize;

    //分类名称
    private String name;
}
