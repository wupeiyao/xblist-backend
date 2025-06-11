package com.xiaowu.springboot.service;


import com.baomidou.mybatisplus.extension.service.IService;

import com.xiaowu.springboot.domain.TaskSettings;
import com.xiaowu.springboot.dto.TaskSettingsDTO;
import com.xiaowu.springboot.dto.TaskSettingsUpdateDTO;

import java.util.List;

public interface TaskSettingsService extends IService<TaskSettings> {

    /*
      * 获得所有参数
      */
    TaskSettings getTheSettings(TaskSettingsDTO taskSettingsDTO);

    void insert(Long userId);

    List<TaskSettings> getAllUserTaskSettings();

    void updateTaskSettings(TaskSettingsUpdateDTO taskSettingsUpdateDTO);
}
