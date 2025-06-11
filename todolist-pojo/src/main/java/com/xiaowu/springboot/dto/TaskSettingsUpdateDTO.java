package com.xiaowu.springboot.dto;

import lombok.Data;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/11/14 20:46
 */
@Data
public class TaskSettingsUpdateDTO {
    private Long userId;    // 用户 ID
    private Integer setting1;  // 每天凌晨4点自动清除未完成任务（1启用，0禁用）
    private Integer setting2;  // 每天凌晨4点自动清除已完成任务（1启用，0禁用）
    private Integer setting3;  // 每天凌晨4点自动清除所有任务（1启用，0禁用））
}
