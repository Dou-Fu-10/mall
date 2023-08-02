package org.example.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.entity.AdminEntity;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.system.entity.AdminRolesRelationEntity;
import org.example.modules.system.entity.RoleEntity;
import org.example.modules.system.entity.vo.MenuVo;
import org.example.modules.system.entity.vo.RoleVo;
import org.example.modules.system.mapper.RoleMapper;
import org.example.modules.system.service.AdminRolesRelationService;
import org.example.modules.system.service.RoleService;
import org.example.modules.system.service.RolesMenusRelationService;
import org.example.security.entity.Authority;
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
    private AdminRolesRelationService adminRolesRelationService;
    @Resource
    private RolesMenusRelationService rolesMenusRelationService;

    @Override
    public List<Authority> mapToGrantedAuthorities(@NotNull AdminEntity user) {
        // 获取权限信息
        Set<String> permissions = new HashSet<>();
        // TODO 管理员
        // 如果是超级管理员直接返回
        if (user.getIsAdmin()) {
            permissions.add("admin");
            return permissions.stream().map(Authority::new)
                    .collect(Collectors.toList());
        }
        // 根据用户Id查找权限
        permissions = getBaseMapper().findPermissionByUserId(user.getId());
        return permissions.stream().map(Authority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateStatus(Long id, Boolean status) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setEnabled(status);
        roleEntity.setId(id);
        return roleEntity.updateById();
    }

    @Override
    public List<MenuVo> listMenu(Long roleId) {
        return rolesMenusRelationService.findMenusByRoleId(roleId);
    }

    @Override
    public boolean allocMenu(Long roleId, List<Long> menuIds) {
        rolesMenusRelationService.removeByIds(roleId);
        return rolesMenusRelationService.saveBatch(roleId, menuIds);
    }

    @Override
    public List<RoleEntity> findByUsersId(Long adminId) {
        List<AdminRolesRelationEntity> list = adminRolesRelationService.lambdaQuery().eq(AdminRolesRelationEntity::getAdminId, adminId).list();
        Set<Long> collect = list.stream().map(AdminRolesRelationEntity::getRoleId).collect(Collectors.toSet());
        return lambdaQuery().in(RoleEntity::getId, collect).list();
    }

    @Override
    public Page<RoleVo> page(Page<RoleEntity> page, RoleEntity role) {
        Page<RoleEntity> roleEntityPage = page(page, new QueryWrapper<>(role));
        IPage<RoleVo> roleVoIPage = roleEntityPage.convert(roleEntity -> BeanCopy.convert(roleEntity, RoleVo.class));
        roleVoIPage.getRecords().stream().forEach(roleVo -> roleVo.setMenu(rolesMenusRelationService.findMenusByRoleId(roleVo.getId())));
        return (Page) roleVoIPage;
    }
}

