package com.xiaowu.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.xiaowu.springboot.domain.Task;
import com.xiaowu.springboot.dto.TaskPageQueryDTO;
import com.xiaowu.springboot.vo.TaskVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {


    Page<TaskVO> pageQuery(TaskPageQueryDTO taskPageQueryDTO);

    Integer getCount(TaskPageQueryDTO taskPageQueryDTO);


    void delete7DayTask(@Param("userId") Long userId, @Param("currentTime") Integer currentTime);

    void deleteByStatus(@Param("userId") Long userId, @Param("typeId") Long typeId);

    void deleteAll(@Param("userId") Long userId);
}
