package org.example.modules.admin.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.admin.system.entity.AdminRolesRelationEntity;
import org.example.modules.admin.system.mapper.AdminRolesRelationMapper;
import org.example.modules.admin.system.service.AdminRolesRelationService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-09 19:57:25
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 19:57:25
 * @Description 用户角色关联(AdminRolesRelation)表服务实现类
 */
@Service("AdminRolesRelationService")
public class AdminRolesRelationServiceImpl extends ServiceImpl<AdminRolesRelationMapper, AdminRolesRelationEntity> implements AdminRolesRelationService {

}

