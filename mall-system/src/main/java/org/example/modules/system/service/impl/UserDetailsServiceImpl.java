package org.example.modules.system.service.impl;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.entity.AdminEntity;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.redis.service.RedisService;
import org.example.modules.system.service.AdminService;
import org.example.modules.system.service.RoleService;
import org.example.security.entity.JwtUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by Dou-Fu-10 2023-07-07 09:58:02
 *
 * @author Dou-Fu-10
 * @date 2023-07-07 09:58:02
 * @Description 后台用户表(User)表服务实现类
 */
@Slf4j
@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AdminService adminService;
    private final RoleService roleService;
    @Resource
    private RedisService redisService;

    @Override
    public JwtUser loadUserByUsername(String username) {
        // 根据用户名获取用户信息
        AdminEntity user = adminService.getByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("");
        }
        // 判断账号是否激活
        if (!user.getEnabled()) {
            throw new BaseRequestException("账号未激活！");
        }
        // 将权限信息放入 jwtUserDto 中
        JwtUser jwtUser = new JwtUser(user, roleService.mapToGrantedAuthorities(user));
        redisService.set("login:" + jwtUser.getUsername(), jwtUser);
        return jwtUser;
    }
}
