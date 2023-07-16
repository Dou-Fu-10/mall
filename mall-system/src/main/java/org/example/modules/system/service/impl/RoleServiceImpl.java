package org.example.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.security.entity.Authority;
import org.example.common.core.entity.AdminEntity;
import org.example.modules.system.entity.MenuEntity;
import org.example.modules.system.entity.RoleEntity;
import org.example.modules.system.mapper.RoleMapper;
import org.example.modules.system.service.RoleService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-09 18:15:18
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:15:18
 * @Description 后台用户角色表(Role)表服务实现类
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Authority> mapToGrantedAuthorities(@NotNull AdminEntity user) {
        // 获取权限信息
        Set<String> permissions = new HashSet<>();
        // 如果是超级管理员直接返回
        if (user.getIsAdmin()) {
            permissions.add("admin");
            return permissions.stream().map(Authority::new)
                    .collect(Collectors.toList());
        }
        // 根据用户Id查找权限
        permissions = roleMapper.findPermissionByUserId(user.getId());
        return permissions.stream().map(Authority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean update(RoleEntity roleEntity) {
        return false;
    }

    @Override
    public List<MenuEntity> listMenu(Long roleId) {
        return null;
    }

    @Override
    public boolean allocMenu(Long roleId, List<Long> menuIds) {
        return false;
    }
}

