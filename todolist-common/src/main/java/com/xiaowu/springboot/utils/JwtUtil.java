package com.xiaowu.springboot.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

/**
 * @description:
 * @author: xiaowu
 * @time: 2024/12/23 21:50
 */
public class JwtUtil {

    // 使用自动生成的 256 位密钥
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);  // 自动生成一个 256 位的密钥
    private static final long EXPIRATION_TIME = 365L * 24 * 60 * 60 * 1000; // 1 年的毫秒数


    /**
     * 生成 JWT
     */
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date()) // 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 过期时间
                .signWith(key) // 使用 Key 签名（默认 HS256 算法）
                .compact();
    }

    /**
     * 验证 JWT 并返回用户信息
     */
    public static String validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key) // 设置签名密钥
                    .build()
                    .parseClaimsJws(token) // 解析 JWT
                    .getBody();
            return claims.getSubject(); // 返回用户名
        } catch (JwtException e) {
            throw new IllegalArgumentException("Invalid JWT token");
        }
    }
}

