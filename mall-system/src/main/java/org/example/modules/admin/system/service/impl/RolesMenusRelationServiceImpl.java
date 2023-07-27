package org.example.modules.admin.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.modules.admin.system.entity.RolesMenusRelationEntity;
import org.example.modules.admin.system.entity.vo.MenuVo;
import org.example.modules.admin.system.mapper.RolesMenusRelationMapper;
import org.example.modules.admin.system.service.MenuService;
import org.example.modules.admin.system.service.RolesMenusRelationService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
        return lambdaQuery().in(RolesMenusRelationEntity::getRoleId, roleIds).list();
    }

    @Override
    public List<MenuVo> findMenusByRoleId(Long roleId) {
        Set<Long> menusId = lambdaQuery().eq(RolesMenusRelationEntity::getRoleId, roleId).list().stream().map(RolesMenusRelationEntity::getMenuId).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(menusId)) {
            return new ArrayList<>();
        }
        return menuService.findByMenusId(menusId);
    }
}
