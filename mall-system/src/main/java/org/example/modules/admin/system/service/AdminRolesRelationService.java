package org.example.modules.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.admin.system.entity.AdminRolesRelationEntity;
import org.example.modules.admin.system.entity.vo.RoleVo;

import java.util.List;
import java.util.Set;


/**
 * Created by Dou-Fu-10 2023-07-09 19:57:25
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 19:57:25
 * @Description 用户角色关联(AdminRolesRelation)表服务接口
 */
public interface AdminRolesRelationService extends IService<AdminRolesRelationEntity> {


    /**
     * 给用户分配角色
     *
     * @param adminId 用户id
     * @param roleIds 角色id列表
     * @return 是否成功
     */
    Boolean updateRole(Long adminId, Set<Long> roleIds);

    /**
     * 获取用户的角色信息
     *
     * @param adminId 用户id
     * @return 角色信息
     */
    List<RoleVo> getRoleListByAdminId(Long adminId);
}
