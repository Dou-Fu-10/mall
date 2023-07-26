package org.example.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.system.entity.RolesMenusRelationEntity;

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

    List<RolesMenusRelationEntity> findByRoleIdsAndTypeNot(Set<Long> roleIds);
}
