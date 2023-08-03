package org.example.security.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Dou-Fu-10
 * @date 2023-07-07 09:58:02
 * @Description 拒绝访问处理程序
 */
@Slf4j
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        log.error("拒绝访问处理程序:url=" + request.getRequestURI() + request.getHeader("Authorization"));
        // 当用户在没有授权的情况下访问受保护的REST资源时，将调用此方法发送403 Forbidden响应
        response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException == null ? "拒绝访问处理程序" : accessDeniedException.getMessage());
    }
}
