package com.xiaowu.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaowu.springboot.domain.User;
import com.xiaowu.springboot.dto.UserDTO;
import com.xiaowu.springboot.dto.UserLoginDTO;
import com.xiaowu.springboot.dto.UserRegisterDTO;

public interface UserService extends IService<User> {

    /*
     * @description: 登录检查
      */
    User loginCheck(UserLoginDTO userLoginDTO);

    /*
     * @description: 注册
     */
    int insertUser(UserRegisterDTO userRegisterDTO);

    /*
     * @description: 更新
     */
    int updateUser(UserDTO userDTO);

    /*
     * @description: 用户信息回显
     */
    User selectUserById(Long id);
}
