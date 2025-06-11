package com.xiaowu.springboot.handler;

import com.alibaba.fastjson2.JSON;
import com.xiaowu.springboot.result.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/12/24 20:27
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        Result<Object> result = new Result<>(HttpStatus.SC_UNAUTHORIZED, "用户认证失败请重新登录");
        String jsonString = JSON.toJSONString(result);

        WebUtils.renderString(response, jsonString);
    }
}
