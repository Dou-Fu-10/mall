package org.example.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.config.Authority;
import org.example.modules.system.entity.RoleEntity;
import org.example.modules.system.entity.UserEntity;

import java.util.List;


/**
 * Created by PanShiFu 2023-07-09 18:15:18
 *
 * @author PanShiFu
 * @date 2023-07-09 18:15:18
 * @Description 后台用户角色表(Role)表服务接口
 */
public interface RoleService extends IService<RoleEntity> {

    /**
     * 获取用户权限信息
     *
     * @param user 用户
     * @return 权限信息
     */
    List<Authority> mapToGrantedAuthorities(UserEntity user);
}
