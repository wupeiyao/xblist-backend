package com.xiaowu.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaowu.springboot.constant.MessageConstant;
import com.xiaowu.springboot.domain.Category;
import com.xiaowu.springboot.domain.GroupX;
import com.xiaowu.springboot.dto.GroupDTO;
import com.xiaowu.springboot.dto.GroupPageDTO;
import com.xiaowu.springboot.dto.GroupPageQueryDTO;
import com.xiaowu.springboot.result.PageResult;
import com.xiaowu.springboot.result.Result;
import com.xiaowu.springboot.service.CategoryService;
import com.xiaowu.springboot.service.GroupService;
import com.xiaowu.springboot.vo.GroupVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 任务分组接口z
 * @author: xiaowu
 * @time: 2024/9/24 23:33
 */
@RequestMapping("/group")
@Slf4j
@RestController("TaskGroupController")
@Tag(name = "任务分组管理", description = "分组相关操作")
public class GroupController {

    @Resource
    private GroupService groupService;
    @Resource
    private CategoryService categoryService;


    @Operation(summary = "新增任务组")
    @PostMapping("/add")
    public Result<String> insert(@RequestBody GroupDTO groupDTO){
        groupService.insertGroup(groupDTO);
        return Result.success(MessageConstant.INSERT_SUCCESS);
    }

    @Operation(summary = "组分页")
    @GetMapping("/list")
    public Result<PageResult> list(@RequestBody GroupPageQueryDTO groupPageQueryDTO){

        if (groupPageQueryDTO.getUserId() == null){
            return Result.error("userId cannot be null");
        }
            PageResult pageResult = groupService.pageQuery(groupPageQueryDTO);

            return Result.success(pageResult);
    }

    @Operation(summary = "组分页（弃用！）")
    @GetMapping("/list2")
    public Result<Page> list2(@RequestBody GroupPageQueryDTO groupPageQueryDTO){

        Page<GroupX> groupInfo
                = new Page<>(groupPageQueryDTO.getPage(), groupPageQueryDTO.getSize());
        Page<GroupPageDTO> groupPageInfoNew = new Page<>();
        LambdaQueryWrapper<GroupX> wrapper = new LambdaQueryWrapper<>();

        wrapper.like(groupPageQueryDTO.getName() != null, GroupX::getName, groupPageQueryDTO.getName() );
        wrapper.orderByDesc(GroupX::getUpdateTime);

        groupService.page(groupInfo,wrapper);

        //对象拷贝
        BeanUtils.copyProperties(groupInfo,groupPageInfoNew ,"records");
        List<GroupX> records = groupInfo.getRecords();

        List<GroupPageDTO> list = records.stream().map((item) ->{
            GroupPageDTO groupPageDTO = new GroupPageDTO();
            BeanUtils.copyProperties(item,groupPageDTO);

            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if(categoryId != null){
                String categoryName = category.getCname();
                groupPageDTO.setCategoryName(categoryName);
            }
            return groupPageDTO;
        }).toList();

        groupPageInfoNew.setRecords(list);
        return Result.success(groupPageInfoNew);


    }

    @Operation(summary = "任务回显")
    @GetMapping("/{id}")
    public Result<GroupVO> getById(@PathVariable(name = "id") Long id){
        GroupVO groupVO  = groupService.getById2(id);

        return Result.success(groupVO);
    }

    //todo 未测试
    @Operation(summary = "组更新")
    @PostMapping("/update")
    public Result<String> update(@RequestBody GroupDTO groupDTO){
        groupService.update2(groupDTO);
        return Result.success(MessageConstant.UPDATE_SUCCESS);
    }

    /**
     * @description: 批量删除
     * @param ids
     * @author: xiaowu
     * @time: 2024/9/30 14:54
     */
    @Operation(summary = "组批量删除")
    @PostMapping("/delete")
    @CacheEvict(cacheNames = "groupCash",allEntries = true)
    public Result<String> delete(@RequestParam(name = "ids") List<Long> ids){
        groupService.delete(ids);
        return Result.success(MessageConstant.DELETE_SUCCESS);
    }






}
