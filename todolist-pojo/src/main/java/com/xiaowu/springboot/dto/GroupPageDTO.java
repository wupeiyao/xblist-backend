package com.xiaowu.springboot.dto;

import com.xiaowu.springboot.domain.TaskGroup;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/30 16:18
 */
@Data
public class GroupPageDTO implements Serializable {

        private List<TaskGroup> taskGroups;

        private String categoryName;
}
