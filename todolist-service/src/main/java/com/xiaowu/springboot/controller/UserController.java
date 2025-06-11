package com.xiaowu.springboot.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.UUID;
import com.xiaowu.springboot.constant.MessageConstant;
import com.xiaowu.springboot.domain.LoginUser;
import com.xiaowu.springboot.domain.User;
import com.xiaowu.springboot.dto.UserDTO;
import com.xiaowu.springboot.dto.UserLoginDTO;
import com.xiaowu.springboot.dto.UserRegisterDTO;
import com.xiaowu.springboot.exception.UserNotLoginException;
import com.xiaowu.springboot.result.Result;
import com.xiaowu.springboot.service.UserService;
import com.xiaowu.springboot.utils.JwtUtil;
import com.xiaowu.springboot.vo.UserLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/user")
@Tag(name = "用户管理", description = "用户相关操作")
public class UserController {



    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserService userService;

    @GetMapping("/test")
    public Result<String> test() {
        return Result.success("测试成功");
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录：{}", userLoginDTO);
        log.info("用户登录：{}", userLoginDTO);
        log.info("用户登录请求数据：{}", userLoginDTO); // 打印完整 DTO
        log.info("captchaCode: {}", userLoginDTO.getCaptchaCode()); // 单独打印 captchaCod

        //  1. 获取验证码（前端传了 captchaCode 和 captcha）
        System.out.println("前端传的验证码标识：" + userLoginDTO.getCaptchaCode());
        System.out.println("Redis中查找的key：" + "captcha:" + userLoginDTO.getCaptchaCode());

        String realCaptcha = redisTemplate.opsForValue().get("captcha:" + userLoginDTO.getCaptchaCode());
        System.out.println("后端存的·" + realCaptcha);
        if (realCaptcha == null || !realCaptcha.trim().equalsIgnoreCase(userLoginDTO.getCaptcha().trim())) {
            return Result.error("验证码错误或已过期");
        }

        System.out.println("前端验证码长度：" + userLoginDTO.getCaptcha().length());
        System.out.println("后端验证码长度：" + realCaptcha.length());

        //  2. 验证码校验通过后，删除缓存，防止重复使用
        redisTemplate.delete("captcha:" + userLoginDTO.getCaptchaCode());

        //  3. 身份认证
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword())
        );

        if (authentication == null) {
            throw new UserNotLoginException("登录失败");
        }

        //  4. 后续逻辑照常处理
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User user = loginUser.getUser();

        String token = JwtUtil.generateToken(user.getUsername());
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .avatarUrl(user.getAvatarUrl())
                .token(token)
                .build();

        return Result.success(userLoginVO);
    }


    @Operation(summary = "用户注册", description = "向前端返回状态码")
    @PostMapping("/register")
    public Result<Object> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        int insert = userService.insertUser(userRegisterDTO);

        if (insert != 0) {
            return Result.success(MessageConstant.REGISTER_SUCCESS);
        } else {
            return Result.error(MessageConstant.REGISTER_FAIL);
        }
    }

    @Operation(summary = "用户修改信息")
    @PostMapping("/update")
    public Result<Object> updateUser(@RequestBody UserDTO userDTO) {
        int update = userService.updateUser(userDTO);
        if (update == 0) {
            return Result.error(MessageConstant.UPDATE_FAIL);
        }
        return Result.success(MessageConstant.UPDATE_SUCCESS);
    }

    @Operation(summary = "用户信息回显")
    @GetMapping("/one")
    public Result<User> selectUserById(@RequestParam(name = "id") Long id) {
        User user = userService.selectUserById(id);
        return Result.success(user);
    }

    @Operation(summary = "用户退出登录")
    @PostMapping("/logout")
    public Result<String> logout(@RequestHeader("Authorization") String token) {
        log.info("用户退出登录，token：{}", token);
        // 无需具体处理，前端丢弃 token 即可
        return Result.success("退出成功");
    }


    @GetMapping("/captcha")
    public void generateCaptcha(HttpServletResponse response) throws Exception {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 40, 4, 40);
        String code = lineCaptcha.getCode();
        String captchaCode = UUID.randomUUID().toString();

        redisTemplate.opsForValue().set("captcha:" + captchaCode, code, 3, TimeUnit.MINUTES);

        // 明确设置响应头（两种方式都加上）
        response.setHeader("Captcha-Code", captchaCode);
        response.addHeader("Access-Control-Expose-Headers", "Captcha-Code"); // 关键！

        response.setContentType("image/png");
        lineCaptcha.write(response.getOutputStream());
    }


}
