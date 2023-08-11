package org.example.modules.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.system.entity.RolesMenusRelationEntity;
import org.example.modules.system.entity.vo.MenuVo;
import org.example.modules.system.mapper.RolesMenusRelationMapper;
import org.example.modules.system.service.MenuService;
import org.example.modules.system.service.RolesMenusRelationService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-09 18:52:14
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:52:14
 * @Description 角色菜单关联(RolesMenus)表服务实现类
 */
@Service("RolesMenusRelationService")
public class RolesMenusRelationServiceImpl extends ServiceImpl<RolesMenusRelationMapper, RolesMenusRelationEntity> implements RolesMenusRelationService {

    @Resource
    @Lazy
    private MenuService menuService;

    @Override
    public List<RolesMenusRelationEntity> findByRoleIdsAndTypeNot(Set<Long> roleIds) {
        // 校验
        if (CollectionUtils.isEmpty(roleIds)) {
            return new ArrayList<>();
        }
        return lambdaQuery().in(RolesMenusRelationEntity::getRoleId, roleIds).list();
    }

    @Override
    public List<MenuVo> findMenusByRoleId(Long roleId) {
        if (Objects.isNull(roleId)) {
            return new ArrayList<>();
        }
        Set<Long> menusId = lambdaQuery().eq(RolesMenusRelationEntity::getRoleId, roleId).list().stream().map(RolesMenusRelationEntity::getMenuId).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(menusId)) {
            return new ArrayList<>();
        }
        return menuService.findByMenusId(menusId);
    }

    @Override
    public Boolean removeByRoleId(Long roleId) {
        // 删除 角色所绑定的 信息
        return remove(lambdaQuery().eq(RolesMenusRelationEntity::getRoleId, roleId).getWrapper());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean saveBatch(Long roleId, List<Long> menuIds) {
        if (Objects.isNull(roleId) || CollectionUtils.isEmpty(menuIds)) {
            throw new BaseRequestException("参数错误");
        }
        Set<RolesMenusRelationEntity> rolesMenusRelationEntitySet = menuIds.stream().map(id -> new RolesMenusRelationEntity(roleId, id)).collect(Collectors.toSet());
        return saveBatch(rolesMenusRelationEntitySet);
    }

    @Override
    public List<MenuVo> findMenusByRoleIds(Set<Long> roleId) {
        // 校验
        if (CollectionUtils.isEmpty(roleId)) {
            return new ArrayList<>();
        }
        // 获取 角色与 菜单的绑定
        List<RolesMenusRelationEntity> rolesMenusRelationEntityList = lambdaQuery().in(RolesMenusRelationEntity::getRoleId, roleId).list();
        if (CollectionUtils.isEmpty(rolesMenusRelationEntityList)) {
            return new ArrayList<>();
        }
        Set<Long> menuIdList = rolesMenusRelationEntityList.stream().map(RolesMenusRelationEntity::getMenuId).collect(Collectors.toSet());
        return menuService.findByMenusId(menuIdList);
    }

    @Override
    public Map<Long, List<MenuVo>> findMenusByRoleIdList(Set<Long> roleIds) {
        // 校验
        if (CollectionUtils.isEmpty(roleIds)) {
            return new HashMap<>();
        }
        List<MenuVo> menusByRoleIdList = getBaseMapper().findMenusByRoleIdList(roleIds);
        if (CollectionUtils.isEmpty(menusByRoleIdList)) {
            return new HashMap<>();
        }
        Map<Long, List<MenuVo>> longListMap = new HashMap<>();
        menusByRoleIdList.forEach(menuVo -> {
            Long roleId = menuVo.getRoleId();
            longListMap.computeIfAbsent(roleId, k -> new ArrayList<>()).add(menuVo);
        });
        return longListMap;
    }
}

