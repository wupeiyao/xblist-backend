package com.xiaowu.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.xiaowu.springboot.domain.Category;
import com.xiaowu.springboot.dto.CategoryPageQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * @description: 类别分页
     * @author: xiaowu
     * @time: 2024/9/30 18:57
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * @description: all类别
     * @author: xiaowu
     * @time: 2024/9/30 18:57
     */
    List<Category> selectAll();

    void updateById2(Category category);
}
