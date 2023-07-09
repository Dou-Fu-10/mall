package org.example.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.system.entity.RoleEntity;
import org.example.modules.system.mapper.RoleMapper;
import org.example.modules.system.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * Created by PanShiFu 2023-07-09 18:15:18
 *
 * @author PanShiFu
 * @date 2023-07-09 18:15:18
 * @Description 后台用户角色表(Role)表服务实现类
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

}

