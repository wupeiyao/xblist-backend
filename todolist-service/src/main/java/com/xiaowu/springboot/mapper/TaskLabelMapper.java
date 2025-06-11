package com.xiaowu.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaowu.springboot.domain.TaskLabel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskLabelMapper extends BaseMapper<TaskLabel> {

    List<TaskLabel> selectList2(Long id);
}
