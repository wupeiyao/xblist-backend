package com.xiaowu.springboot.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description: 任务和组的关系
 * @author: xiaowu
 * @time: 2024/9/23 21:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskGroup implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long taskId;
    private Long groupId;
    private String taskName;

}
