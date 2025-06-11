package com.xiaowu.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaowu.springboot.constant.MessageConstant;
import com.xiaowu.springboot.domain.TaskSettings;
import com.xiaowu.springboot.domain.User;
import com.xiaowu.springboot.dto.UserDTO;
import com.xiaowu.springboot.dto.UserLoginDTO;
import com.xiaowu.springboot.dto.UserRegisterDTO;
import com.xiaowu.springboot.exception.UserNotLoginException;
import com.xiaowu.springboot.mapper.TaskSettingsMapper;
import com.xiaowu.springboot.mapper.UserMapper;
import com.xiaowu.springboot.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @description: 用户登录
 * @author: xiaowu
 * @time: 2024/6/20 12:43
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private TaskSettingsMapper taskSettingsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User loginCheck(UserLoginDTO userLoginDTO) {

        if(userLoginDTO == null){
            log.info(MessageConstant.LOGIN_FAILED);
            throw new UserNotLoginException(MessageConstant.LOGIN_FAILED);
        }

        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        //md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        queryWrapper.eq(User::getUsername, username);
        queryWrapper.eq(User::getPassword, password);
        User findUser = userMapper.selectOne(queryWrapper);

        if (findUser == null){
            log.info(MessageConstant.LOGIN_FAILED);
            throw new UserNotLoginException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        return findUser;
    }

    //前端传值不安全
    //加密
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(UserRegisterDTO userRegisterDTO) {

        if(userRegisterDTO == null){
            log.info(MessageConstant.LOGIN_FAILED);
            throw new UserNotLoginException(MessageConstant.LOGIN_FAILED);
        }


        String password = userRegisterDTO.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password1 = encoder.encode(password);
        User user = new User();
        //属性拷贝
        BeanUtils.copyProperties(userRegisterDTO,user);
        user.setPassword(password1);

        user.setRole(MessageConstant.USER_DEFAULT);


        int insert = userMapper.insert(user);

        if (insert > 0) {
            // 获取刚插入用户的 ID
            Long userId = user.getId();

            addTaskSetting(userId);

            return insert; // 返回用户插入结果
        }

        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(UserDTO userDTO) {

        if(userDTO.getPassword() != null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            userDTO.setPassword(encoder.encode(userDTO.getPassword()));

            User user = new User();
            BeanUtils.copyProperties(userDTO,user);

            return userMapper.updateById(user);
        }

        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        return userMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User selectUserById(Long id) {
        return userMapper.selectOne(
                new QueryWrapper<User>()
                        .select(
                                "id",
                                "username",
                                "email",
                                "phone",
                                "sex",
                                "avatar_url",
                                "birthday",
                                "address",
                                "signature",
                                "university",
                                "secondary_school",
                                "elementary_school",
                                "company",
                                "introduce",
                                "major",
                                "role"
                        )
                        .eq("id", id)
        );
    }




    public void addTaskSetting(Long userId) {
        taskSettingsMapper.addTaskSettings(userId);
    }
}
