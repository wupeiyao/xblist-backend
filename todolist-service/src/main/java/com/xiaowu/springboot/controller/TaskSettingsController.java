package com.xiaowu.springboot.controller;

import com.xiaowu.springboot.domain.TaskSettings;
import com.xiaowu.springboot.dto.TaskSettingsDTO;
import com.xiaowu.springboot.dto.TaskSettingsUpdateDTO;
import com.xiaowu.springboot.result.Result;
import com.xiaowu.springboot.service.TaskService;
import com.xiaowu.springboot.service.TaskSettingsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/11/13 19:41
 */
@RestController
@Tag(name = "任务设置管理", description = "任务设置相关操作")
@RequestMapping("/task")
@Slf4j
public class TaskSettingsController {

    @Autowired
    private TaskSettingsService taskSettingsService;


    @PostMapping("/settings")
    public Result changeStatus(@RequestBody TaskSettingsDTO taskSettingsDTO) {

        TaskSettings taskSettings = taskSettingsService.getTheSettings(taskSettingsDTO);
        return Result.success(taskSettings);
    }


    @PostMapping("/updatetasksetting")
    public Result updateSettings(@RequestBody TaskSettingsUpdateDTO taskSettingsUpdateDTO) {

        try {
            // 更新数据库中的任务设置
            taskSettingsService.updateTaskSettings(taskSettingsUpdateDTO);
            return Result.success("设置更新成功");
        } catch (Exception e) {
            log.error("更新任务设置失败", e);
            return Result.error("更新设置失败");
        }
    }
}
