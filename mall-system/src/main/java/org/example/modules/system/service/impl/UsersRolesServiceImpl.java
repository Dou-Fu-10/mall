package org.example.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.system.entity.UsersRolesEntity;
import org.example.modules.system.mapper.UsersRolesMapper;
import org.example.modules.system.service.UsersRolesService;

/**
 * Created by PanShiFu 2023-07-09 19:57:25
 *
 * @author PanShiFu
 * @date 2023-07-09 19:57:25
 * @Description 用户角色关联(UserRole)表服务实现类
 */
@Service("UsersRolesService")
public class UsersRolesServiceImpl extends ServiceImpl<UsersRolesMapper, UsersRolesEntity> implements UsersRolesService {

}

