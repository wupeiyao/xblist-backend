package com.xiaowu.springboot.controller;

import com.xiaowu.springboot.annotation.AutoFill;
import com.xiaowu.springboot.constant.MessageConstant;
import com.xiaowu.springboot.domain.Category;
import com.xiaowu.springboot.dto.CategoryDTO;
import com.xiaowu.springboot.dto.CategoryPageQueryDTO;
import com.xiaowu.springboot.enumeration.OperationType;
import com.xiaowu.springboot.result.PageResult;
import com.xiaowu.springboot.result.Result;
import com.xiaowu.springboot.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 类别
 * @author: xiaowu
 * @time: 2024/9/24 23:31
 */
@Tag(name = "类别管理")
@RestController("CategoryController")
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Operation(summary = "新增类别")
    @PostMapping("/add")
    public Result<String> insertCategory(@RequestBody CategoryDTO categoryDTO, @RequestHeader String token) {
        categoryService.insert(categoryDTO);
        return Result.success(MessageConstant.INSERT_SUCCESS);
    }

    @Operation(summary = "类别分页")
    @GetMapping("/page")
    public Result<PageResult> listCategory(@RequestBody CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("分页查询：{}",categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @Operation(summary = "类别all")
    @GetMapping("/list")
    public Result<List<Category>> listAllCategory() {
        List<Category> list = categoryService.selectAll();
        return Result.success(list);
    }

    @Operation(summary = "类别批量删除")
    @PostMapping("/delete")
    public Result<String> deleteCategory(@RequestParam(name = "id") Integer id, @RequestHeader String token) {
        boolean b = categoryService.removeById(id);
        if(b){
            return Result.success(MessageConstant.DELETE_SUCCESS);
        }
        return Result.error(MessageConstant.DELETE_ERROR);
    }

    @Operation(summary = "类别更新")
    @PostMapping("/update")
    public Result<String> updateCategory(@RequestBody CategoryDTO categoryDTO, @RequestHeader String token) {
        categoryService.update2(categoryDTO);
        return Result.success(MessageConstant.UPDATE_SUCCESS);
    }

}
