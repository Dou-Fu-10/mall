package org.example.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.modules.system.entity.RolesMenusRelationEntity;
import org.example.modules.system.entity.vo.MenuVo;

import java.util.List;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-07-09 18:52:14
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:52:14
 * @Description 角色菜单关联(RolesMenusRelation)表数据库访问层
 */
@Mapper
public interface RolesMenusRelationMapper extends BaseMapper<RolesMenusRelationEntity> {
    /**
     * 请确保 roleId 有效性
     *
     * @param roleId 角色id
     * @return /
     */
    @Select({
            "<script>",
            "SELECT ",
            "  ums_roles_menus_relation.role_id, ",
            "  ums_menu.* ",
            " FROM",
            "  ums_roles_menus_relation ",
            "  JOIN ums_menu ",
            "    ON ums_roles_menus_relation.menu_id = ums_menu.id ",
            "    AND ums_menu.delete_flag = 0 ",
            " WHERE ums_roles_menus_relation.role_id IN ",
            "  <foreach item='item' collection='roleId' open='(' separator=',' close=')'>",
            "    #{item}",
            "  </foreach>",
            "</script>"
    })
    List<MenuVo> findMenusByRoleIdList(@Param("roleId") Set<Long> roleId);

}

