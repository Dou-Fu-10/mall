package org.example.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.entity.AdminEntity;
import org.example.common.core.utils.BeanCopy;
import org.example.common.core.utils.StringUtils;
import org.example.modules.system.entity.AdminRolesRelationEntity;
import org.example.modules.system.entity.RoleEntity;
import org.example.modules.system.entity.dto.RoleDto;
import org.example.modules.system.entity.vo.MenuVo;
import org.example.modules.system.entity.vo.RoleVo;
import org.example.modules.system.mapper.RoleMapper;
import org.example.modules.system.service.AdminRolesRelationService;
import org.example.modules.system.service.RoleService;
import org.example.modules.system.service.RolesMenusRelationService;
import org.example.security.entity.Authority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
    public List<Authority> GrantedAuthoritieList(AdminEntity user) {
        if (Objects.isNull(user)) {
            return new ArrayList<>();
        }
        // 获取权限信息
        // 根据用户Id查找权限
        Set<String> permissions = getBaseMapper().findPermissionByUserId(user.getId());
        // 校验 权限信息不得等于空
        permissions = permissions.stream().filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(permissions)) {
            return new ArrayList<>();
        }
        return permissions.stream().map(Authority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean updateStatus(Long id, Boolean status) {
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
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean allocMenu(Long roleId, List<Long> menuIds) {
        if (Objects.isNull(roleId) || CollectionUtils.isEmpty(menuIds)) {
            return false;
        }
        // 校验角色是否存在
        RoleEntity roleEntity = getById(roleId);
        if (Objects.isNull(roleEntity)) {
            return false;
        }
        // 删除角色对应的 菜单 删除成功才能绑定
        rolesMenusRelationService.removeByRoleId(roleId);
        return rolesMenusRelationService.saveBatch(roleId, menuIds);
    }

    @Override
    public List<RoleEntity> findByUsersId(Long adminId) {
        // 获取管理员所绑定的角色信息
        List<AdminRolesRelationEntity> list = adminRolesRelationService.lambdaQuery().eq(AdminRolesRelationEntity::getAdminId, adminId).list();
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        Set<Long> collect = list.stream().map(AdminRolesRelationEntity::getRoleId).collect(Collectors.toSet());
        return lambdaQuery().in(RoleEntity::getId, collect).list();
    }

    @Override
    public Page<RoleVo> page(Page<RoleEntity> page, RoleDto roleDto) {
        RoleEntity roleEntity = BeanCopy.convert(roleDto, RoleEntity.class);
        Page<RoleEntity> roleEntityPage = page(page, new QueryWrapper<>(roleEntity));
        // 获取角色信息
        IPage<RoleVo> roleVoIPage = roleEntityPage.convert(role -> BeanCopy.convert(role, RoleVo.class));
        // 校验角色不等于空
        List<RoleVo> roleVoList = roleVoIPage.getRecords();
        if (CollectionUtils.isEmpty(roleVoList)) {
            return (Page<RoleVo>) roleVoIPage;
        }
        // 获取角色 id
        Set<Long> roleIdList = roleVoList.stream().map(RoleVo::getId).collect(Collectors.toSet());

        // 获取角色id 对应的 菜单
        Map<Long, List<MenuVo>> menusByRoleIdList = rolesMenusRelationService.findMenusByRoleIdList(roleIdList);
        // 校验不等于空
        if (CollectionUtils.isEmpty(menusByRoleIdList)) {
            return (Page<RoleVo>) roleVoIPage;
        }
        roleVoList.forEach(roleVo -> {
            Long roleId = roleVo.getId();
            if (menusByRoleIdList.containsKey(roleId)) {
                List<MenuVo> menuVos = menusByRoleIdList.get(roleId);
                roleVo.setMenu(menuVos);
            }
        });
        return (Page<RoleVo>) roleVoIPage;
    }

    @Override
    public List<RoleVo> getExistingRoles(Set<Long> roleIds) {
        List<RoleEntity> roleEntityList = lambdaQuery().in(RoleEntity::getId, roleIds).list();
        return BeanCopy.copytList(roleEntityList, RoleVo.class);
    }

    @Override
    public Boolean save(RoleDto role) {
        RoleEntity roleEntity = BeanCopy.convert(role, RoleEntity.class);
        return roleEntity.insert();
    }

    @Override
    public Boolean updateById(RoleDto role) {
        RoleEntity roleEntity = BeanCopy.convert(role, RoleEntity.class);
        return roleEntity.updateById();
    }
}

