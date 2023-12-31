package org.example.modules.system.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.entity.AdminEntity;
import org.example.common.core.exception.AuthenticationException;
import org.example.modules.system.service.AdminService;
import org.example.modules.system.service.RoleService;
import org.example.security.entity.Authority;
import org.example.security.entity.JwtAdmin;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by Dou-Fu-10 2023-07-07 09:58:02
 *
 * @author Dou-Fu-10
 * @date 2023-07-07 09:58:02
 * @Description 后台用户表(User)表服务实现类
 */
@Slf4j
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private AdminService adminService;
    @Resource
    private RoleService roleService;

    @Override
    public JwtAdmin loadUserByUsername(String username) {
        // 根据用户名获取用户信息
        AdminEntity user = adminService.getByUsername(username);
        if (Objects.isNull(user)) {
            throw new AuthenticationException("账号密码错误");
        }
        // 判断账号是否激活
        if (!user.getEnabled()) {
            throw new AuthenticationException("账号未激活！");
        }
        // 获取用户权限信息
        List<Authority> authoritieList = roleService.GrantedAuthoritieList(user);
        // 将权限信息放入 jwtUserDto 中
        return new JwtAdmin(user, authoritieList);
    }
}
