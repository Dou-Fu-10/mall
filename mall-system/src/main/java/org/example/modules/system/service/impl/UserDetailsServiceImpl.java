package org.example.modules.system.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.exception.BaseRequestException;
import org.example.config.JwtUser;
import org.example.modules.system.entity.UserEntity;
import org.example.modules.system.service.RoleService;
import org.example.modules.system.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by PanShiFu 2023-07-07 09:58:02
 *
 * @author PanShiFu
 * @date 2023-07-07 09:58:02
 * @Description 后台用户表(User)表服务实现类
 */
@Slf4j
@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final RoleService roleService;
    private final DataService dataService;
    private final UserCacheManager userCacheManager;

    @Override
    public JwtUser loadUserByUsername(String username) {
        // 获取缓存
        JwtUser jwtUser = userCacheManager.getUserCache(username);
        if (Objects.isNull(jwtUser)) {
            UserEntity user;
            // 根据用户名获取用户信息
            user = userService.getByUsername(username);
            if (Objects.isNull(user)) {
                throw new UsernameNotFoundException("");
            } else {
                // 判断是否 激活
                if (!user.getEnabled()) {
                    throw new BaseRequestException("账号未激活！");
                }
                // 将权限信息放入 jwtUserDto 中
                jwtUser = new JwtUser(
                        user,
//                        dataService.getDeptIds(user),
                        roleService.mapToGrantedAuthorities(user)
                );
                // 添加缓存数据
                userCacheManager.addUserCache(username, jwtUser);
            }
        }
        return jwtUser;
    }
}
