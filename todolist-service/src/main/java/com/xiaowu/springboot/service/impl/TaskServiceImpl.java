package com.xiaowu.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaowu.springboot.annotation.AutoFill;
import com.xiaowu.springboot.constant.MessageConstant;
import com.xiaowu.springboot.domain.Task;
import com.xiaowu.springboot.domain.TaskLabel;
import com.xiaowu.springboot.dto.DeleteTypeDTO;
import com.xiaowu.springboot.dto.TaskAddDTO;
import com.xiaowu.springboot.dto.TaskDTO;
import com.xiaowu.springboot.dto.TaskPageQueryDTO;
import com.xiaowu.springboot.enumeration.OperationType;
import com.xiaowu.springboot.exception.DeletionNotAllowedException;
import com.xiaowu.springboot.mapper.GroupMapper;
import com.xiaowu.springboot.mapper.GroupTaskMapper;
import com.xiaowu.springboot.mapper.TaskLabelMapper;
import com.xiaowu.springboot.mapper.TaskMapper;
import com.xiaowu.springboot.result.PageResult;
import com.xiaowu.springboot.service.TaskService;
import com.xiaowu.springboot.vo.TaskVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/25 23:23
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Resource
    private TaskMapper taskMapper;

    @Resource
    private TaskLabelMapper taskLabelMapper;

    @Resource
    private GroupMapper groupMapper;

    @Resource
    private GroupTaskMapper groupTaskMapper;


    @Transactional
    @AutoFill(value = OperationType.INSERT)
    @Override
    public void createTask(TaskAddDTO taskAddDTO) {

        Task task = new Task();

        Long userId = taskAddDTO.getUserId();
        BeanUtils.copyProperties(taskAddDTO, task);

        taskMapper.insert(task);

        List<TaskLabel> taskLabels = taskAddDTO.getTaskLabels();
        if (taskLabels != null && taskLabels.size() > 0) {
            taskLabels.forEach(taskLabel -> {
                taskLabel.setUserId(userId);
            });
            taskLabelMapper.insert(taskLabels);
        }
    }

    @Override
    public TaskVO getTaskById(Long id) {

        Task task = taskMapper.selectById(id);


        // LambdaQueryWrapper<TaskLabel> wrapper = new LambdaQueryWrapper<>();
        // wrapper.eq(TaskLabel::getTaskId, id);
        List<TaskLabel> taskLabels = taskLabelMapper.selectList2(id);

        TaskVO taskVO = new TaskVO();
        BeanUtils.copyProperties(task, taskVO);
        taskVO.setTaskLabels(taskLabels);
        return taskVO;
    }

    @Override
    public List<Task> getAllTasks(Long userId) {

        return taskMapper.selectList(new LambdaQueryWrapper<Task>().eq(Task::getUserId, userId));
    }

    @Transactional
    @AutoFill(value = OperationType.UPDATE)
    @Override
    public void updateTask(TaskDTO taskDTO) {

        Task task = new Task();
        Long id = taskDTO.getId();
        BeanUtils.copyProperties(taskDTO, task);
        taskMapper.updateById(task);

        // //删除原来的
        // taskLabelMapper.deleteById(id);
        //
        // List<TaskLabel> taskLabels = taskDTO.getTaskLabels();
        // if (taskLabels != null && taskLabels.size() > 0) {
        //
        //     taskLabels.forEach(taskLabel -> {
        //         taskLabel.setTaskId(id);
        //     });
        //     taskLabelMapper.insert(taskLabels);
        // }

    }

    @Transactional
    @Override
    public void deleteTasks(List<Long> ids) {

        List<Long> groupTasksIds = groupTaskMapper.getGroupTaskIds(ids);
        if (groupTasksIds != null && groupTasksIds.size() > 0) {
            throw new DeletionNotAllowedException(MessageConstant.TASK_BE_RELATED_RROUP);
        }

        // 删除
        taskMapper.deleteByIds(ids);
        taskLabelMapper.deleteByIds(ids);
    }

    @Override
    public PageResult pageQuery(TaskPageQueryDTO taskPageQueryDTO) {
        int page = taskPageQueryDTO.getPage();
        int size = taskPageQueryDTO.getSize();

        PageHelper.startPage(page, size);
        Page<TaskVO> taskVOS = taskMapper.pageQuery(taskPageQueryDTO);

        Integer total = taskMapper.getCount(taskPageQueryDTO);


        System.out.println("Total: " + taskVOS.getTotal());
        System.out.println("Records: " + taskVOS.getResult().size());


        return new PageResult(total, taskVOS.getResult());
    }

    /*
     * @description:  删除7天
     */
    @Override
    public void delete7Day(DeleteTypeDTO deleteTypeDTO) {
        // 获取当前时间
        // LocalDateTime currentTime = LocalDateTime.now();  // 获取当前时间
        Long userId = deleteTypeDTO.getUserId();
        Long typeId = deleteTypeDTO.getTypeId();
        // 根据 typeId 执行不同的业务逻辑
        switch (typeId.intValue()) {
            case 1:
                // 执行第一个业务逻辑
                int seven = 7;
                taskMapper.delete7DayTask(userId, seven);
                break;
            case 2:
                // 执行第二个业务逻辑
                int halfMounth = 15;
                taskMapper.delete7DayTask(userId, halfMounth);
                break;
            case 3:
                // 执行第三个业务逻辑
                int month = 30;
                taskMapper.delete7DayTask(userId, month);
                break;
            default:
                // 如果 typeId 不匹配任何已知的类型，处理默认逻辑
                break;
        }
    }

    @Override
    public void deleteByStatus(DeleteTypeDTO deleteTypeDTO) {
        Long userId = deleteTypeDTO.getUserId();
        Long typeId = deleteTypeDTO.getTypeId();
        if(typeId == 1){

            taskMapper.deleteByStatus(userId,typeId);

        }else if(typeId == 0){
            taskMapper.deleteByStatus(userId,typeId);
        }

    }

    @Override
    public void delete(Long userId) {
        taskMapper.deleteAll(userId);
    }


}
