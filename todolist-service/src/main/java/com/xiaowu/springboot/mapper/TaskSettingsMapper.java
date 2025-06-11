package com.xiaowu.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaowu.springboot.domain.TaskSettings;
import com.xiaowu.springboot.dto.TaskSettingsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TaskSettingsMapper extends BaseMapper<TaskSettings> {


    TaskSettings selectByUserId(TaskSettingsDTO taskSettingsDTO);


    void addTaskSettings(@Param("userId") Long userId);

    List<TaskSettings> getAll();

    // 更新任务设置
    @Update("UPDATE task_settings SET setting1 = #{setting1}, setting2 = #{setting2}, setting3 = #{setting3} WHERE user_id = #{userId}")
    void updateByUserId(TaskSettings taskSettings);
}
