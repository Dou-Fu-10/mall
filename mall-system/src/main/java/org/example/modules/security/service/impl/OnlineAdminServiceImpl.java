package org.example.modules.security.service.impl;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.utils.BeanCopy;
import org.example.common.core.utils.FileUtil;
import org.example.common.core.utils.StringUtils;
import org.example.common.redis.service.RedisService;
import org.example.modules.security.service.OnlineAdminService;
import org.example.modules.system.entity.vo.OnlineUserVo;
import org.example.security.config.SecurityProperties;
import org.example.security.entity.JwtAdmin;
import org.example.security.entity.OnlineAdminDto;
import org.example.security.utils.JwtTokenUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Dou-Fu-10
 * @date 2023-07-16
 * @Description 权限访问配置
 */
@Service
@Slf4j
@AllArgsConstructor
public class OnlineAdminServiceImpl implements OnlineAdminService {

    @Resource
    private SecurityProperties securityProperties;
    @Resource
    private RedisService redisService;

    /**
     * 保存在线用户信息
     *
     * @param jwtAdmin 登录信息
     * @param token    token
     * @param request  请求信息
     */
    @Override
    public Boolean save(@NotNull JwtAdmin jwtAdmin, @NotEmpty(message = "token不能为空") String token, HttpServletRequest request) {
        // 获取登录者 ip
        String ip = StringUtils.getIp(request);
        // 浏览器
        String browser = StringUtils.getBrowser(request);
        // ip 对应的物理地址
        String address = StringUtils.getCityInfo(ip);
        OnlineAdminDto onlineAdminDto = null;
        try {
            onlineAdminDto = new OnlineAdminDto(jwtAdmin, browser, ip, address, token, new Date());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        // 以登录用户的username作为key保存在线用户信息  过期时间以毫秒来计算 14400000  即 4小时
        return redisService.set(securityProperties.getOnlineKey() + jwtAdmin.getUsername(), onlineAdminDto, securityProperties.getTokenValidityInSeconds(), TimeUnit.MILLISECONDS);
    }

    /**
     * 根据用户名强退用户
     *
     * @param username /
     */
    @Async
    @Override
    public void kickOutForUsername(String username) {
        String loginKey = securityProperties.getOnlineKey() + username;
        redisService.del(loginKey);
    }
}
