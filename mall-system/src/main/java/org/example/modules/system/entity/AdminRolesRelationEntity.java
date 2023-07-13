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
 * Created by Dou-Fu-10 2023-07-09 19:57:25
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 19:57:25
 * @Description 用户角色关联(AdminRolesRelation)表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_admins_roles_relation")
@Schema(name = "ums_admins_roles_relation", description = "用户角色关联(AdminRolesRelation)表实体类")
public class AdminRolesRelationEntity extends CommonEntity<AdminRolesRelationEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
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

