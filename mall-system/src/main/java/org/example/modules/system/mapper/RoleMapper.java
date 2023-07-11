package org.example.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.modules.system.entity.RoleEntity;

import java.util.Set;

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
     * 首先，在ums_users_roles和ums_role表之间进行内连接（INNER JOIN）。连接条件是ums_users_roles表的role_id与ums_role表的id匹配，并且ums_role表的delete_flag字段为0且enabled字段为1。
     * 然后，在ums_role表和ums_roles_menus表之间进行内连接。连接条件是ums_role表的id与ums_roles_menus表的role_id匹配。
     * 最后，在ums_roles_menus表和ums_menu表之间进行内连接。连接条件是ums_roles_menus表的menu_id与ums_menu表的id匹配，并且ums_menu表的delete_flag字段为0。
     * 最终，使用WHERE子句筛选出ums_users_roles表中user_id为1的记录。
     *
     * @param id 用户id
     * @return 根据用户Id查找权限
     */
    @Select("SELECT" +
            "  menu.permission" +
            " FROM " +
            "  ums_users_roles uur" +
            "  INNER JOIN ums_role ur" +
            "    ON uur.role_id = ur.id" +
            "    AND ur.delete_flag = 0" +
            "    AND ur.enabled = 1" +
            "  INNER JOIN ums_roles_menus urm" +
            "    ON ur.id = urm.role_id" +
            "  INNER JOIN ums_menu menu" +
            "    ON urm.menu_id = menu.id" +
            "    AND menu.delete_flag = 0" +
            " WHERE uur.user_id = #{userId}")
    Set<String> findPermissionByUserId(@Param("userId") Long id);
}

