package com.xiaowu.springboot.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/10/5 20:14
 */
@Data
public class TaskLabelDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private Integer taskId;
}
