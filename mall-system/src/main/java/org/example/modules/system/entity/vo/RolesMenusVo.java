package org.example.modules.system.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class RolesMenusVo {
    /**
     * 菜单ID
     */
    private Long menuId;
    /**
     * 角色ID
     */
    private Long roleId;


}

