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
     * 首先，从ums_role表中选择所有列，并将其别名为role。
     * 再使用左连接将ums_role表与ums_roles_menus表连接。连接条件是ums_role.id = ums_roles_menus.role_id。
     * 然后，再次使用左连接将连接结果与ums_menu表连接。连接条件是ums_menu.id = ums_roles_menus.menu_id。
     * 最后，使用左连接将连接结果与ums_users_roles表连接。连接条件是ums_role.id = ums_users_roles.role_id。
     * 在连接过后，使用WHERE子句来过滤结果，条件是ums_role.id = ums_users_roles.role_id AND ums_users_roles.user_id = 1。这意味着只返回角色ID与用户ID为1的关联记录。
     *
     * @param id 用户id
     * @return 用户权限信息
     */
    @Select("SELECT" +
            "  *  " +
            "FROM" +
            "  `ums_role` role" +
            "  LEFT JOIN `ums_roles_menus` urm" +
            "    ON role.`id` = urm.`role_id`" +
            "  LEFT JOIN `ums_menu` menu" +
            "    ON menu.`id` = urm.`menu_id`" +
            "  LEFT JOIN `ums_users_roles` uur" +
            "    ON role.`id` = uur.`role_id`" +
            "WHERE role.`id` = uur.`role_id`" +
            "  AND uur.`user_id` = #{userId}")
    List<RoleEntity> findByUserId(@Param("userId") Long id);
}

