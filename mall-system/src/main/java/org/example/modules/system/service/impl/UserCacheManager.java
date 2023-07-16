package org.example.modules.system.service.impl;

import cn.hutool.core.util.RandomUtil;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.common.redis.service.RedisService;
import org.example.security.entity.JwtUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Zheng Jie
 * @description 用户缓存管理
 * @date 2022-05-26
 **/
@Component
public class UserCacheManager {
    public static final String CACHE_KEY = "user-login-cache:";
    @Resource
    private RedisService redisService;
    @Value("${login.user-cache.idle-time}")
    private long idleTime;

    /**
     * 返回用户缓存
     *
     * @param userName 用户名
     * @return JwtUserDto
     */
    public JwtUser getUserCache(String userName) {
        if (StringUtils.isNotEmpty(userName)) {
            // 获取数据
            Object obj = redisService.get(CACHE_KEY + userName);
            if (obj != null) {
                return (JwtUser) obj;
            }
        }
        return null;
    }

    /**
     * 添加缓存到Redis
     *
     * @param userName 用户名
     */
    @Async
    public void addUserCache(String userName, JwtUser user) {
        if (StringUtils.isNotEmpty(userName)) {
            // 添加数据, 避免数据同时过期
            long time = idleTime + RandomUtil.randomInt(900, 1800);
            redisService.set(CACHE_KEY + userName, user, time);
        }
    }

    /**
     * 清理用户缓存信息
     * 用户信息变更时
     *
     * @param userName 用户名
     */
    @Async
    public void cleanUserCache(String userName) {
        if (StringUtils.isNotEmpty(userName)) {
            // 清除数据
            redisService.del(CACHE_KEY + userName);
        }
    }
}