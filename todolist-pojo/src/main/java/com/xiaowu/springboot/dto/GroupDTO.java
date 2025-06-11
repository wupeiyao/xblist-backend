package com.xiaowu.springboot.dto;

import com.xiaowu.springboot.domain.TaskGroup;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/30 0:49
 */
@Data
public class GroupDTO implements Serializable {
    private Long id;
    private String name;
    private Long userId;
    private String description;

    private Long categoryId;

    private Integer status;

    private List<TaskGroup> taskGroups = new ArrayList<TaskGroup>();
}
