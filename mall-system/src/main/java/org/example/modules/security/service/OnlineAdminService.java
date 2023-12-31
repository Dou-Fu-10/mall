package org.example.modules.security.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.security.entity.JwtAdmin;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by Dou-Fu-10 2023/7/16
 *
 * @author Dou-Fu-10
 * @date 2023/7/16
 * @Description 描述
 */
public interface OnlineAdminService {
    /**
     * 保存在线用户信息
     *
     * @param jwtAdmin /
     * @param token    /
     * @param request  /
     * @return Boolean
     */
    Boolean save(JwtAdmin jwtAdmin, String token, HttpServletRequest request);

    /**
     * 根据用户名强退用户
     *
     * @param username /
     */
    @Async
    void kickOutForUsername(String username);
}
