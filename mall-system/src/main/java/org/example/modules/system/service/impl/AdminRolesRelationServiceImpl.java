package org.example.modules.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.system.entity.AdminRolesRelationEntity;
import org.example.modules.system.entity.vo.RoleVo;
import org.example.modules.system.mapper.AdminRolesRelationMapper;
import org.example.modules.system.service.AdminRolesRelationService;
import org.example.modules.system.service.RoleService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-09 19:57:25
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 19:57:25
 * @Description 用户角色关联(AdminRolesRelation)表服务实现类
 */
@Service("AdminRolesRelationService")
public class AdminRolesRelationServiceImpl extends ServiceImpl<AdminRolesRelationMapper, AdminRolesRelationEntity> implements AdminRolesRelationService {

    @Resource
    @Lazy
    private RoleService roleService;

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean updateRole(Long adminId, @NotNull Set<Long> roleIds) {
        remove(lambdaQuery().eq(AdminRolesRelationEntity::getAdminId, adminId).getWrapper());
        Set<AdminRolesRelationEntity> adminRolesRelationEntitySet = roleIds.stream().map(id -> new AdminRolesRelationEntity(adminId, id)).collect(Collectors.toSet());
        return saveBatch(adminRolesRelationEntitySet);
    }

    @Override
    public List<RoleVo> getRoleListByAdminId(Long adminId) {
        Set<Long> roleIdList = lambdaQuery().eq(AdminRolesRelationEntity::getAdminId, adminId).list().stream().map(AdminRolesRelationEntity::getRoleId).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(roleIdList)) {
            return new ArrayList<>();
        }
        return BeanCopy.copytList(roleService.listByIds(roleIdList), RoleVo.class);
    }
}

