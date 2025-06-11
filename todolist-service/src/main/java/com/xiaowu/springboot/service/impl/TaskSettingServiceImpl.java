package com.xiaowu.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaowu.springboot.domain.Task;
import com.xiaowu.springboot.domain.TaskSettings;
import com.xiaowu.springboot.dto.TaskSettingsDTO;
import com.xiaowu.springboot.dto.TaskSettingsUpdateDTO;
import com.xiaowu.springboot.mapper.TaskMapper;
import com.xiaowu.springboot.mapper.TaskSettingsMapper;
import com.xiaowu.springboot.service.TaskService;
import com.xiaowu.springboot.service.TaskSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/25 23:23
 */
@Service
@Slf4j
public class TaskSettingServiceImpl extends ServiceImpl<TaskSettingsMapper, TaskSettings> implements TaskSettingsService {

    @Autowired
    private TaskSettingsMapper taskSettingsMapper;
    /*
     * 获得所有参数
     */
    @Override
    public TaskSettings getTheSettings(TaskSettingsDTO taskSettingsDTO) {
        return taskSettingsMapper.selectByUserId(taskSettingsDTO);
    }

    @Override
    public void insert(Long userId) {
        taskSettingsMapper.addTaskSettings(userId);
    }

    @Override
    public List<TaskSettings> getAllUserTaskSettings() {
        return taskSettingsMapper.getAll();
    }

    @Override
    public void updateTaskSettings(TaskSettingsUpdateDTO taskSettingsUpdateDTO) {
        TaskSettings taskSettings = new TaskSettings();

        BeanUtils.copyProperties(taskSettingsUpdateDTO, taskSettings);

        taskSettingsMapper.updateByUserId(taskSettings);

    }
}
