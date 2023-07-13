package org.example.modules.system.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;

/**
 * Created by Dou-Fu-10 2023-07-09 18:52:14
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:52:14
 * @Description 角色菜单关联(RolesMenusRelation)表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_roles_menus_relation")
@Schema(name = "ums_roles_menus_relation", description = "角色菜单关联(RolesMenusRelation)表实体类")
public class RolesMenusRelationEntity extends CommonEntity<RolesMenusRelationEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 菜单ID
     */
    @Schema(name = "菜单ID", description = "与菜单id进行关联")
    private Long menuId;
    /**
     * 角色ID
     */
    @Schema(name = "角色ID", description = "与角色ID进行关联")
    private Long roleId;


}

