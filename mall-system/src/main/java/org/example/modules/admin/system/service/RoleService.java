package org.example.modules.admin.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.core.entity.AdminEntity;
import org.example.modules.admin.system.entity.MenuEntity;
import org.example.modules.admin.system.entity.RoleEntity;
import org.example.modules.admin.system.entity.vo.MenuVo;
import org.example.modules.admin.system.entity.vo.RoleVo;
import org.example.security.entity.Authority;

import java.util.List;


/**
 * Created by Dou-Fu-10 2023-07-09 18:15:18
 *
 * @author Dou-Fu-10
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
    List<Authority> mapToGrantedAuthorities(AdminEntity user);

    /**
     * 修改角色状态
     *
     * @param id     角色id
     * @param status 角色状态
     * @return String
     */
    boolean updateStatus(Long id, Boolean status);

    /**
     * 获取角色相关菜单
     *
     * @param roleId 角色id
     * @return 角色关联菜单
     */
    List<MenuVo> listMenu(Long roleId);

    /**
     * 给角色分配菜单
     *
     * @param roleId  角色id
     * @param menuIds 菜单id列表
     * @return String
     */
    boolean allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 根据用户ID查询 用户角色
     *
     * @param adminId 用户ID
     * @return 用户角色
     */
    List<RoleEntity> findByUsersId(Long adminId);

    Page<RoleVo> page(Page<RoleEntity> page, RoleEntity role);
}
