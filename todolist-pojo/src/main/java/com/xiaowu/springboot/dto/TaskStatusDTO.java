package com.xiaowu.springboot.dto;

import lombok.Data;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/10/22 17:32
 */
@Data
public class TaskStatusDTO {

    private Long id;
    private Long userId;
    private Integer status;
    private Integer isImportant;

}
