package org.example.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.modules.system.entity.RoleEntity;

import java.util.List;

/**
 * Created by PanShiFu 2023-07-09 18:34:52
 *
 * @author PanShiFu
 * @date 2023-07-09 18:34:52
 * @Description 后台用户角色表(Role)表数据库访问层
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {


    /**
     * 获取用户权限信息
     *
     * @param id 用户id
     * @return 用户权限信息
     */
    @Select("SELECT * " +
            "FROM `ums_user_role` " +
            "JOIN `ums_role` ON `ums_user_role`.`role_id` = `ums_role`.`id` " +
            "JOIN `ums_roles_menus` ON `ums_role`.`id` = `ums_roles_menus`.`role_id` " +
            "JOIN `ums_menu` ON `ums_roles_menus`.`menu_id` = `ums_menu`.`id` " +
            "WHERE `ums_user_role`.`user_id` = #{userId}")
    List<RoleEntity> findByUserId(@Param("userId") Long id);
}

