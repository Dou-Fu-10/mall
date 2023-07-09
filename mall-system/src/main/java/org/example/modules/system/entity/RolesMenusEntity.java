package org.example.modules.system.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;

/**
 * Created by PanShiFu 2023-07-09 18:52:14
 *
 * @author PanShiFu
 * @date 2023-07-09 18:52:14
 * @Description 角色菜单关联(RolesMenus)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_roles_menus")
@Schema(name = "ums_roles_menus", description = "角色菜单关联(RolesMenus)表实体类")
public class RolesMenusEntity extends CommonEntity<RolesMenusEntity> implements Serializable {
    /**
     * 菜单ID
     */
    @TableId
    private Long menuId;
    /**
     * 角色ID
     */
    @TableId
    private Long roleId;


}

