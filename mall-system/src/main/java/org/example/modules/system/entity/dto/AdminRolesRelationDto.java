package org.example.modules.system.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Dou-Fu-10 2023-07-09 19:57:25
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 19:57:25
 * @Description 用户角色关联(AdminRolesRelation)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRolesRelationDto {
    /**
     * ID
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long adminId;
    /**
     * 角色ID
     */
    private Long roleId;


}

