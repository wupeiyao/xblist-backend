package com.xiaowu.springboot.task;

import com.xiaowu.springboot.dto.DeleteTypeDTO;
import com.xiaowu.springboot.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xiaowu.springboot.service.TaskSettingsService;


@Slf4j
@Component
public class WorkTask {

    @Autowired
    private TaskSettingsService taskSettingsService;

    @Autowired
    private TaskService taskService;


    // 每天凌晨 4 点执行清除任务
    @Scheduled(cron = "0 0 4 * * ?")
    public void clearTasks() {
        // 从数据库查询所有用户的定时设置
        taskSettingsService.getAllUserTaskSettings().forEach(settings -> {
            Long userId = settings.getUserId();

            // 根据用户的定时设置执行不同的清除任务
            if (settings.getSetting1() == 1) {
                log.info("开始清除用户 {} 的未完成任务", userId);
                clearUnfinishedTasks(userId);
            }
            if (settings.getSetting2() == 1) {
                log.info("开始清除用户 {} 的已完成任务", userId);
                clearCompletedTasks(userId);
            }
            if (settings.getSetting3() == 1) {
                log.info("开始清除用户 {} 的所有任务", userId);
                clearAllTasks(userId);
            }
        });
    }

    private void clearUnfinishedTasks(Long userId) {
        log.info("清除用户 {} 的未完成任务", userId);
        long type = 0;
        DeleteTypeDTO deleteTypeDTO = new DeleteTypeDTO();
        deleteTypeDTO.setUserId(userId);
        deleteTypeDTO.setTypeId(type);
        taskService.deleteByStatus(deleteTypeDTO);
    }

    private void clearCompletedTasks(Long userId) {
        log.info("清除用户 {} 的已完成任务", userId);
        long type = 1;
        DeleteTypeDTO deleteTypeDTO = new DeleteTypeDTO();
        deleteTypeDTO.setUserId(userId);
        deleteTypeDTO.setTypeId(type);
        taskService.deleteByStatus(deleteTypeDTO);
    }

    private void clearAllTasks(Long userId) {
        log.info("清除用户 {} 的所有任务", userId);
        // 调用任务服务层，清除所有的任务
        taskService.delete(userId);
    }
}
