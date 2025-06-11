package com.xiaowu.springboot.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/30 18:50
 */
@Data
public class CategoryDTO implements Serializable {
    private Long id;

    private String cname;
}
