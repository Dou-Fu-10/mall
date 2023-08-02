package org.example.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.system.entity.RolesMenusRelationEntity;
import org.example.modules.system.entity.vo.MenuVo;

import java.util.List;
import java.util.Set;


/**
 * Created by Dou-Fu-10 2023-07-09 18:52:14
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:52:14
 * @Description 角色菜单关联(RolesMenusRelation)表服务接口
 */
public interface RolesMenusRelationService extends IService<RolesMenusRelationEntity> {

    /**
     * 按角色id和类型查找
     *
     * @param roleIds 角色ids
     * @return /
     */
    List<RolesMenusRelationEntity> findByRoleIdsAndTypeNot(Set<Long> roleIds);

    /**
     * 通过角色Id查找菜单
     *
     * @param roleId 角色id
     * @return /
     */
    List<MenuVo> findMenusByRoleId(Long roleId);

    /**
     * 删除 角色所绑定的菜单
     *
     * @param roleId 角色id
     * @return Boolean
     */
    Boolean removeByIds(Long roleId);

    /**
     * 角色绑定菜单
     *
     * @param roleId  角色id
     * @param menuIds 菜单ids
     * @return Boolean
     */
    Boolean saveBatch(Long roleId, List<Long> menuIds);

    /**
     * 通过角色Id列表查找菜单
     *
     * @param roleId 角色id 列表
     * @return /
     */
    List<MenuVo> findMenusByRoleIds(Set<Long> roleId);
}
