package com.xiaowu.springboot.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaowu.springboot.domain.TaskGroup;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/30 14:55
 */
@Data
public class GroupVO implements Serializable {

    private Long id;

    //组名称
    private String name;

    private Integer status;

    private Long userId;
    private String description;

    private Long categoryId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    private List<TaskGroup> taskGroups = new ArrayList<>();

}
