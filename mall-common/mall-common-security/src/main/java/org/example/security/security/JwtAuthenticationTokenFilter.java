package org.example.security.security;


import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.common.redis.service.RedisService;
import org.example.security.entity.JwtUser;
import org.example.security.utils.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * @author ty
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private RedisService redisService;
//    @Resource
//    private SecurityProperties properties;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的token
        String token = request.getHeader("token");
        // 解析获取userid
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        Claims claims = null;
        try {
            claims = jwtTokenUtil.getClaimsFromToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            // token 超时 token 非法
            // 响应告诉前端需要重新登录
//            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
//            WebUtils.renderString(response, JSONObject.toJSONString(result));
            return;
        }
        String userName = claims.getSubject();
        // 从redis 中获取用户信息
        JwtUser loginUser = (JwtUser) redisService.get("login:" + userName);
        // 如果获取不到
        if (Objects.isNull(loginUser)) {
            // 说明登录过去 提示重新登录
//            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
//            WebUtils.renderString(response, JSONObject.toJSONString(result));
            return;
        }

        // 存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);

    }

    /**
     * 初步检测Token
     *
     * @param request /
     * @return /
     */
//    private String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader(properties.getHeader());
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(properties.getTokenStartWith())) {
//            // 去掉令牌前缀
//            return bearerToken.replace(properties.getTokenStartWith(), "");
//        } else {
//            log.debug("非法Token：{}", bearerToken);
//        }
//        return null;
//    }
}
