package com.xiaowu.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaowu.springboot.annotation.AutoFill;
import com.xiaowu.springboot.domain.GroupX;
import com.xiaowu.springboot.domain.TaskGroup;
import com.xiaowu.springboot.dto.GroupDTO;
import com.xiaowu.springboot.dto.GroupPageQueryDTO;
import com.xiaowu.springboot.enumeration.OperationType;
import com.xiaowu.springboot.mapper.GroupMapper;
import com.xiaowu.springboot.mapper.GroupTaskMapper;
import com.xiaowu.springboot.result.PageResult;
import com.xiaowu.springboot.service.GroupService;
import com.xiaowu.springboot.vo.GroupVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/25 23:39
 */
@Service
@Slf4j
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupX> implements GroupService {


    @Resource
    private GroupMapper groupMapper;

    @Resource
    private GroupTaskMapper groupTaskMapper;;


    /**
     * @description: 新增套餐
     * @return:
     * @author: xiaowu
     * @time: 2024/9/30 17:02
     */
    @Transactional
    @AutoFill(value = OperationType.INSERT)
    @Override
    public void insertGroup(GroupDTO groupDTO) {

        GroupX group = new GroupX();
        List<TaskGroup> taskGroups = groupDTO.getTaskGroups();

        BeanUtils.copyProperties(groupDTO, group);

        groupMapper.insertGroup(group);
        for (TaskGroup taskGroup : taskGroups) {
            taskGroup.setGroupId(group.getId());
            groupTaskMapper.insert(taskGroup);
        }
    }

    @Override
    public PageResult pageQuery(GroupPageQueryDTO groupPageQueryDTO) {
        Integer page = groupPageQueryDTO.getPage();
        Integer size = groupPageQueryDTO.getSize();
        PageHelper.startPage(page, size);
        Page<GroupVO> groupVOS = groupMapper.pageQuery(groupPageQueryDTO);
        return new PageResult(groupVOS.getTotal(), groupVOS.getResult());
    }

    /**
     * @description: 任务回显
     */
    @Override
    public GroupVO getById2(Long id) {
        GroupX groups = groupMapper.selectById(id);

        List<TaskGroup> taskGroups = groupTaskMapper.getGroupTasks(groups.getId());
        GroupVO groupVO = new GroupVO();
        BeanUtils.copyProperties(groups, groupVO);
        groupVO.setTaskGroups(taskGroups);
        return groupVO;
    }

    /**
     * @description: 任务更新
     */
    @Transactional
    @AutoFill(value = OperationType.UPDATE)
    @Override
    public void update2(GroupDTO groupDTO) {
        GroupX groupX = new GroupX();
        List<TaskGroup> taskGroups = groupDTO.getTaskGroups();
        BeanUtils.copyProperties(groupDTO, groupX);
        groupMapper.updateById(groupX);

        Long groupXId = groupX.getId();
        groupTaskMapper.deleteById(groupXId);

        for (TaskGroup taskGroup : taskGroups) {
            taskGroup.setGroupId(groupXId);
            groupTaskMapper.insert(taskGroup);
        }
    }

    /**
     * @description: 任务删除
     */
    @Transactional
    @Override
    public void delete(List<Long> ids) {
        List<GroupX> groupXES = groupMapper.selectGroups(ids);

        for (GroupX groupX : groupXES) {
            Long id = groupX.getId();
            groupMapper.deleteById(id);
            groupTaskMapper.deleteById(id);
        }
    }
}
