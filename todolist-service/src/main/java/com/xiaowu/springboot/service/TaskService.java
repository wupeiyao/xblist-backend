package com.xiaowu.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaowu.springboot.domain.Task;
import com.xiaowu.springboot.dto.DeleteTypeDTO;
import com.xiaowu.springboot.dto.TaskAddDTO;
import com.xiaowu.springboot.dto.TaskDTO;
import com.xiaowu.springboot.dto.TaskPageQueryDTO;
import com.xiaowu.springboot.result.PageResult;
import com.xiaowu.springboot.vo.TaskVO;

import java.util.List;

public interface TaskService extends IService<Task> {
    /*
     * @description:  新增任务
      */
    void createTask(TaskAddDTO taskAddDTO);

    /*
     * @description:  任务回显
     */
    TaskVO getTaskById(Long id);

    /*
     * @description:  任务列表
     */
    List<Task> getAllTasks(Long userId);

    /*
     * @description:  更新
     */
    void updateTask(TaskDTO taskDTO);

    /*
     * @description:  批量删除
     */
    void deleteTasks(List<Long> ids);

    PageResult pageQuery(TaskPageQueryDTO taskPageQueryDTO);

    /*
     * @description:  删除7天
     */
    void delete7Day(DeleteTypeDTO deleteTypeDTO);

    void deleteByStatus(DeleteTypeDTO deleteTypeDTO);

    void delete(Long userId);
}
