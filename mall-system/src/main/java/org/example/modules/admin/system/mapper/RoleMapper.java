package org.example.modules.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.modules.admin.system.entity.RoleEntity;

import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-07-09 18:34:52
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:34:52
 * @Description 后台用户角色表(Role)表数据库访问层
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {


    /**
     * 首先，在ums_admins_roles_relation和ums_role表之间进行内连接（INNER JOIN）。连接条件是ums_admins_roles_relation表的role_id与ums_role表的id匹配，并且ums_role表的delete_flag字段为0且enabled字段为1。
     * 然后，在ums_role表和ums_roles_menus_relation表之间进行内连接。连接条件是ums_role表的id与ums_roles_menus_relation表的role_id匹配。
     * 最后，在ums_roles_menus_relation表和ums_menu表之间进行内连接。连接条件是ums_roles_menus_relation表的menu_id与ums_menu表的id匹配，并且ums_menu表的delete_flag字段为0。
     * 最终，使用WHERE子句筛选出ums_admins_roles_relation表中user_id为1的记录。
     *
     * @param id 用户id
     * @return 根据用户Id查找权限
     */
    @Select("SELECT" +
            "  menu.permission" +
            " FROM " +
            "  ums_admins_roles_relation uarr" +
            "  INNER JOIN ums_role ur" +
            "    ON uarr.role_id = ur.id" +
            "    AND ur.delete_flag = 0" +
            "    AND ur.enabled = 1" +
            "  INNER JOIN ums_roles_menus_relation urmr" +
            "    ON ur.id = urmr.role_id" +
            "  INNER JOIN ums_menu menu" +
            "    ON urmr.menu_id = menu.id" +
            "    AND menu.delete_flag = 0" +
            " WHERE uarr.admin_id = #{adminId}")
    Set<String> findPermissionByUserId(@Param("adminId") Long id);
}

