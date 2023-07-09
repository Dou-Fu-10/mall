package org.example.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.system.entity.UserRoleEntity;
import org.example.modules.system.mapper.UserRoleMapper;
import org.example.modules.system.service.UserRoleService;

/**
 * Created by PanShiFu 2023-07-09 19:57:25
 *
 * @author PanShiFu
 * @date 2023-07-09 19:57:25
 * @Description 用户角色关联(UserRole)表服务实现类
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleService {

}

