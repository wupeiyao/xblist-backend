package com.xiaowu.springboot.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaowu.springboot.domain.Category;
import com.xiaowu.springboot.dto.CategoryDTO;
import com.xiaowu.springboot.dto.CategoryPageQueryDTO;
import com.xiaowu.springboot.result.PageResult;

import java.util.List;

public interface CategoryService extends IService<Category> {

    /**
     * @description: 新增类别
     * @author: xiaowu
     * @time: 2024/9/30 18:57
      */
    void insert(CategoryDTO categoryDTO);

    /**
     * @description: 类别分页
     * @author: xiaowu
     * @time: 2024/9/30 18:57
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * @description: 所有类别
     * @author: xiaowu
     * @time: 2024/9/30 18:57
     */
    List<Category> selectAll();

    /**
     * @description: 更新类别
     * @author: xiaowu
     * @time: 2024/9/30 18:57
     */
    void update2(CategoryDTO categoryDTO);
}
