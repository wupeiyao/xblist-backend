package com.xiaowu.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaowu.springboot.domain.TaskGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupTaskMapper extends BaseMapper<TaskGroup> {


    /**
     * @description: 批量删除
     * @param ids
     * @author: xiaowu
     * @time: 2024/6/17 14:54
     */
    List<Long> getGroupTaskIds(@Param("ids") List<Long> ids);

    /**
     * @description: 回显
     * @param id
     * @author: xiaowu
     * @time: 2024/6/17 14:54
     */
    List<TaskGroup> getGroupTasks(Long id);
}
