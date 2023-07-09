package org.example.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.system.entity.RolesMenusEntity;
import org.example.modules.system.mapper.RolesMenusMapper;
import org.example.modules.system.service.RolesMenusService;
import org.springframework.stereotype.Service;

/**
 * Created by PanShiFu 2023-07-09 18:52:14
 *
 * @author PanShiFu
 * @date 2023-07-09 18:52:14
 * @Description 角色菜单关联(RolesMenus)表服务实现类
 */
@Service("rolesMenusService")
public class RolesMenusServiceImpl extends ServiceImpl<RolesMenusMapper, RolesMenusEntity> implements RolesMenusService {

}

