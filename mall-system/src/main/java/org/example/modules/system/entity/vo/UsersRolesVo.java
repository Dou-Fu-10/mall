package org.example.modules.system.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by PanShiFu 2023-07-09 19:57:26
 *
 * @author PanShiFu
 * @date 2023-07-09 19:57:26
 * @Description 用户角色关联(UserRole)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersRolesVo {
    /**
     * ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;


}
