package org.example.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.config.Authority;
import org.example.modules.system.entity.MenuEntity;
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
     * @param user 用户信息
     * @return 权限信息
     */
    List<Authority> mapToGrantedAuthorities(UserEntity user);

    /**
     * 修改角色状态
     *
     * @param roleEntity 角色信息
     * @return String
     */
    boolean update(RoleEntity roleEntity);

    /**
     * 获取角色相关菜单
     *
     * @param roleId 角色id
     * @return 角色关联菜单
     */
    List<MenuEntity> listMenu(Long roleId);

    /**
     * 给角色分配菜单
     *
     * @param roleId  角色id
     * @param menuIds 菜单id列表
     * @return String
     */
    boolean allocMenu(Long roleId, List<Long> menuIds);
}
