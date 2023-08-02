package org.example.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.redis.service.RedisService;
import org.example.security.config.SecurityProperties;
import org.example.security.utils.JwtTokenUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Dou-Fu-10
 * @date 2023-07-16
 * @Description 权限访问配置
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final RedisService redisService;
    private final SecurityProperties properties;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的token
        // TODO 优化缓存设计
        String token = jwtTokenUtil.resolveToken(request);
        log.info(token);
        filterChain.doFilter(request, response);
    }
}
