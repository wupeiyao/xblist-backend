package com.xiaowu.springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaowu.springboot.domain.TaskLabel;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/29 19:01
 */
@Data
public class TaskDTO {

    private Long id;
    private Long userId;
    private String taskContent;
    private String taskTitle;
    private Long categoryId;
    private Date finishTime;
    private Integer status;
    private Integer isImportant;//0：不重要；1：重要
    private List<TaskLabel> taskLabels = new ArrayList<TaskLabel>();

}
