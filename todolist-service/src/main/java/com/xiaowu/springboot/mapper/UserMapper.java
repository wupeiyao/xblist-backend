package com.xiaowu.springboot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaowu.springboot.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
