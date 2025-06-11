package com.xiaowu.springboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaowu.springboot.domain.TaskLabel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/29 12:53
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskAddDTO implements Serializable {

    private Long userId;
    private String taskContent;
    private String taskTitle;
    private Long categoryId;
    private Integer isImportant;//0：不重要；1：重要
    private Date finishTime;
    private Integer status;
    //标签
    private List<TaskLabel> taskLabels = new ArrayList<TaskLabel>();
}
