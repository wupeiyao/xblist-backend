package com.xiaowu.springboot.handler;

import com.alibaba.fastjson2.JSON;
import com.xiaowu.springboot.result.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/12/24 20:47
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result<Object> result = new Result<>(HttpStatus.SC_FORBIDDEN, "您的权限不足");
        String jsonString = JSON.toJSONString(result);

        WebUtils.renderString(response, jsonString);
    }
}
