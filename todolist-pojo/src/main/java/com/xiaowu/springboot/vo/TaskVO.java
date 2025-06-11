package com.xiaowu.springboot.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaowu.springboot.domain.TaskLabel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/29 13:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//任务回显
public class TaskVO {

    private Long id;
    private Long userId;
    private String taskContent;
    private String taskTitle;
    private Long categoryId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private Integer isImportant;//0：不重要；1：重要

    private List<TaskLabel> taskLabels = new ArrayList<TaskLabel>();
}
