package com.xiaowu.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.xiaowu.springboot.domain.GroupX;
import com.xiaowu.springboot.dto.GroupPageQueryDTO;
import com.xiaowu.springboot.vo.GroupVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupMapper extends BaseMapper<GroupX> {

    void insertGroup(GroupX group);

    Page<GroupVO> pageQuery(GroupPageQueryDTO groupPageQueryDTO);

    /**
     * @description: 任务删除
     */
    List<GroupX> selectGroups(@Param("ids") List<Long> ids);
}
