package org.example.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.system.entity.RolesMenusRelationEntity;
import org.example.modules.system.mapper.RolesMenusRelationMapper;
import org.example.modules.system.service.RolesMenusRelationService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-09 18:52:14
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:52:14
 * @Description 角色菜单关联(RolesMenus)表服务实现类
 */
@Service("RolesMenusRelationService")
public class RolesMenusRelationServiceImpl extends ServiceImpl<RolesMenusRelationMapper, RolesMenusRelationEntity> implements RolesMenusRelationService {

}

