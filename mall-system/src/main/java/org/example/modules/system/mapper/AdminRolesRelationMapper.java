package org.example.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.system.entity.AdminRolesRelationEntity;

/**
 * Created by Dou-Fu-10 2023-07-09 19:57:25
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 19:57:25
 * @Description 用户角色关联(AdminRolesRelation)表数据库访问层
 */
@Mapper
public interface AdminRolesRelationMapper extends BaseMapper<AdminRolesRelationEntity> {

}

