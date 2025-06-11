package com.xiaowu.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaowu.springboot.annotation.AutoFill;
import com.xiaowu.springboot.domain.Category;
import com.xiaowu.springboot.dto.CategoryDTO;
import com.xiaowu.springboot.dto.CategoryPageQueryDTO;
import com.xiaowu.springboot.enumeration.OperationType;
import com.xiaowu.springboot.mapper.CategoryMapper;
import com.xiaowu.springboot.result.PageResult;
import com.xiaowu.springboot.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/9/25 23:28
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


    @Resource
    private CategoryMapper categoryMapper;
    /**
     * @description: 新增类别
     * @author: xiaowu
     * @time: 2024/9/30 18:57
     */
    @AutoFill(value = OperationType.INSERT)
    @Override
    public void insert(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        categoryMapper.insert(category);
    }

    /**
     * @description: 类别分页
     * @author: xiaowu
     * @time: 2024/9/30 18:57
     */
    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());

        Page<Category> categoryPage = categoryMapper.pageQuery(categoryPageQueryDTO);

        return new PageResult(categoryPage.getTotal(),categoryPage.getResult());
    }

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectAll();
    }

    @AutoFill(value = OperationType.UPDATE)
    @Override
    public void update2(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        categoryMapper.updateById2(category);
    }
}
