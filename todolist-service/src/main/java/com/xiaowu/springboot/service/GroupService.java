package com.xiaowu.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaowu.springboot.domain.GroupX;
import com.xiaowu.springboot.dto.GroupDTO;
import com.xiaowu.springboot.dto.GroupPageQueryDTO;
import com.xiaowu.springboot.result.PageResult;
import com.xiaowu.springboot.vo.GroupVO;

import java.util.List;

public interface GroupService extends IService<GroupX> {

    /*
     * @description: 新增组
     */
    void insertGroup(GroupDTO groupDTO);

    /**
     * @description: 任务分页
     */
    PageResult pageQuery(GroupPageQueryDTO groupPageQueryDTO);

    /**
     * @description: 任务回显
     */
    GroupVO getById2(Long id);

    /**
     * @description: 任务更新
     */
    void update2(GroupDTO groupDTO);

    /**
     * @description: 任务删除
     */
    void delete(List<Long> ids);
}
