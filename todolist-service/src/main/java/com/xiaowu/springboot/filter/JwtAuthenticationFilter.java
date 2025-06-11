package com.xiaowu.springboot.filter;

import com.xiaowu.springboot.utils.JwtUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * 在每个请求中进行 JWT 验证。
     * 如果请求中包含有效的 JWT token，将从中提取用户名并设置用户认证信息。
     * 如果 JWT 无效，则返回 401 错误。
     *
     * @param request 当前 HTTP 请求
     * @param response 当前 HTTP 响应
     * @param filterChain 过滤器链，用于继续处理请求
     * @throws ServletException 如果处理请求时发生错误
     * @throws IOException 如果发生输入/输出异常
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 从请求头中获取 Authorization 字段
        String token = request.getHeader("Authorization");

        // 检查 token 是否以 "Bearer " 开头
        if (token != null && token.startsWith("Bearer ")) {
            try {
                // 移除 "Bearer " 前缀，提取有效的 token 部分
                String username = JwtUtil.validateToken(token.replace("Bearer ", ""));

                // 如果用户名不为空，则说明 token 验证通过
                log.info("认证通过");
                if (username != null) {
                    // 创建认证信息（这里只是设置了用户名，实际应用中可以提取更多的权限信息）
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            username, null, null // 可以在此处通过 Jwt 提取权限信息并设置
                    );

                    // 将认证信息存入 SecurityContext 中，表示当前用户已经被认证
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (JwtException e) {
                // 如果 JWT 验证失败，返回 401 状态码和错误信息
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid JWT token");
                return;
            }
        }

        // 继续执行过滤器链，进行后续的请求处理
        filterChain.doFilter(request, response);
    }
}
