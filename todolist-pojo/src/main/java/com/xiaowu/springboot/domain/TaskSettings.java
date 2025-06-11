package com.xiaowu.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/11/13 19:39
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSettings {

    private Long id;
    private Long userId;
    private Integer setting1; // 每天凌晨4点自动清除未完成任务
    private Integer setting2; // 每天凌晨4点自动清除已完成任务
    private Integer setting3; // 每天凌晨4点自动清除所有任务
    private Integer setting4; //
    private Integer setting5; //
}
