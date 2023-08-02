package org.example.config;


import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.example.common.redis.service.RedisService;
import org.example.security.config.SecurityProperties;
import org.example.security.entity.OnlineAdminDto;
import org.example.security.utils.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

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
        if (Strings.isNotBlank(token)) {
            Claims claims = jwtTokenUtil.getClaimsByToken(token);
            if (Objects.nonNull(claims)) {
                // 解析获取userName
                String userName = claims.getSubject();
                // 从redis 中获取用户信息
                OnlineAdminDto loginUser = (OnlineAdminDto) redisService.get(properties.getOnlineKey() + userName);
                // 如果获取不到
                if (Objects.nonNull(loginUser)) {
                    // 存入SecurityContextHolder
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getJwtAdminDto(), null, null);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    // TODO 清除token
                    log.error("无法获取redis数据");
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
