package org.example.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.utils.StringUtils;
import org.example.config.Authority;
import org.example.modules.system.entity.MenuEntity;
import org.example.modules.system.entity.RoleEntity;
import org.example.modules.system.entity.UserEntity;
import org.example.modules.system.mapper.RoleMapper;
import org.example.modules.system.service.RoleService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by PanShiFu 2023-07-09 18:15:18
 *
 * @author PanShiFu
 * @date 2023-07-09 18:15:18
 * @Description 后台用户角色表(Role)表服务实现类
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    @Cacheable(key = "'auth:' + #p0.id")
    public List<Authority> mapToGrantedAuthorities(UserEntity user) {
        // 获取权限信息
        Set<String> permissions = new HashSet<>();
        // 如果是管理员直接返回
        if (user.getIsAdmin()) {
            permissions.add("admin");
            return permissions.stream().map(Authority::new)
                    .collect(Collectors.toList());
        }
        List<RoleEntity> roles = roleMapper.findByUserId(user.getId());
        permissions = roles.stream().flatMap(role -> role.getMenus().stream())
                .map(MenuEntity::getPermission)
                .filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        return permissions.stream().map(Authority::new)
                .collect(Collectors.toList());
    }
}

