package com.xiaowu.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.github.pagehelper.PageHelper;
import com.xiaowu.springboot.domain.Task;
import com.xiaowu.springboot.dto.*;
import com.xiaowu.springboot.result.PageResult;
import com.xiaowu.springboot.result.Result;
import com.xiaowu.springboot.service.TaskService;
import com.xiaowu.springboot.vo.TaskVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @description: 任务
 * @author: xiaowu
 * @time: 2024/9/24 23:31
 */
@Tag(name = "任务管理", description = "任务相关操作")
@RequestMapping("/task")
@Slf4j
@RestController("TaskController")
public class TaskController {


    @Autowired
    private TaskService taskService;

    @Resource
    private RedisTemplate redisTemplate;

    @Operation(summary = "新增任务")
    @PostMapping("/add")
    public Result createTask(@RequestBody TaskAddDTO taskAddDTO) {
        taskService.createTask(taskAddDTO);
        log.info("{}", taskAddDTO);
        String key = "task_" + taskAddDTO.getUserId();

        redisTemplate.delete(key);
        return Result.success("新增任务成功");
    }

    @Operation(summary = "任务回显")
    @GetMapping("/{id}")
    public Result<TaskVO> getTask(@PathVariable(name = "id") Long id) {
        TaskVO taskVO = taskService.getTaskById(id);
        return Result.success(taskVO);
    }


    @Operation(summary = "任务列表")
    @GetMapping("/list")
    public Result<List<Task>> getAllTasks(@RequestParam(name = "userId") Long userId) {

        String key = "task_" + userId;

        List<Task> list = (List<Task>) redisTemplate.opsForValue().get(key);
        if (list != null && list.size() > 0) {
            return Result.success(list);
        }

        List<Task> tasks = taskService.getAllTasks(userId);
        redisTemplate.opsForValue().set(key, tasks);
        return Result.success(tasks);
    }

    @Operation(summary = "任务信息更新")
    @PostMapping("/update")
    public Result<Object> updateTask( @RequestBody TaskDTO task) {

        taskService.updateTask( task);

        cleanCache("task_*");
        cleanCache("taskPage_*");
            return Result.success("更新成功");


    }

    @Operation(summary = "任务批量删除")
    @PostMapping("/delete")
    public Result<Void> deleteTask(@RequestParam(name = "ids") List<Long> ids) {

        taskService.deleteTasks(ids);
        cleanCache("task_*");
        cleanCache("taskPage_*");
        return Result.success();
    }

    @Operation(summary = "任务分页")
    @PostMapping("/page")
    public Result<PageResult> pageQuery(@RequestBody TaskPageQueryDTO taskPageQueryDTO) {

        String key = "taskPage_" + taskPageQueryDTO.getUserId();

        PageResult result = (PageResult) redisTemplate.opsForValue().get(key);
        if (result != null ) {
            return Result.success(result);
        }
        PageResult pageResult = taskService.pageQuery(taskPageQueryDTO);

        return Result.success(pageResult);
    }

    //清理缓存时间
    private void cleanCache(String pattern){
        //所有菜品缓存数据删除
        Set keys = redisTemplate.keys(pattern);
        assert keys != null;
        redisTemplate.delete(keys);
    }

    @Operation(summary = "修改任务状态")
    @PostMapping("/updatestatus")
    public Result updateStatus(@RequestBody TaskStatusDTO taskStatusDTO){

        Task task = new Task();
        BeanUtils.copyProperties(taskStatusDTO, task);
        taskService.updateById(task);

        cleanCache("task_*");
        cleanCache("taskPage_*");
        return Result.success();
    }

    @Operation(summary = "删除任务")
    @PostMapping("/deleteone")
    public Result deleteByIds(@RequestParam(name = "id") Long id) {
        taskService.removeById(id);

        cleanCache("task_*");
        cleanCache("taskPage_*");
        return Result.success();
    }

    @Operation(summary = "删除任务按照天数")
    @PostMapping("/delbyday")
    public Result deleteByCondition(@RequestBody DeleteTypeDTO deleteTypeDTO){

        log.info("{}",deleteTypeDTO);
        taskService.delete7Day(deleteTypeDTO);
        return Result.success();
    }

    @Operation(summary = "删除任务按照完成状态")
    @PostMapping("/delbystatus")
    public Result deleteByStatus(@RequestBody DeleteTypeDTO deleteTypeDTO){

        taskService.deleteByStatus(deleteTypeDTO);
        return Result.success();
    }

    @Operation(summary = "删除所有任务")
    @PostMapping("/delall")
    public Result deleteAll(@RequestBody DeleteTypeDTO deleteTypeDTO){

        Long userId = deleteTypeDTO.getUserId();
        taskService.delete(userId);
        return Result.success();
    }
}
