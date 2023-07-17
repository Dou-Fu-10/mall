package org.example.security.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Dou-Fu-10
 * @date 2023-07-07 09:58:02
 * @Description 身份验证入口点
 */
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.error("认证或者授权失败");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException == null ? "认证或者授权失败" : authException.getMessage());
    }
}
