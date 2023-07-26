package org.example.modules.admin.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;
import org.example.common.core.entity.AdminEntity;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-07-09 18:50:38
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:50:38
 * @Description 后台用户角色表(Role)表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_role")
@Schema(name = "ums_role", description = "后台用户角色表(Role)表实体类")
public class RoleEntity extends CommonEntity<RoleEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    @TableField(exist = false)
    @Schema(name = "users", description = "用户", hidden = true)
    private Set<AdminEntity> users;

    @TableField(exist = false)
    @Schema(name = "menus", description = "菜单", hidden = true)
    private Set<MenuEntity> menus;

    /**
     * 名称
     */
    @Schema(name = "name", description = "名称")
    private String name;
    /**
     * 描述
     */
    @Schema(name = "description", description = "描述")
    private String description;
    /**
     * 后台用户数量
     */
    @Schema(name = "adminCount", description = "后台用户数量")
    private Integer adminCount;
    /**
     * 排序
     */
    @Schema(name = "sort", description = "排序")
    private Integer sort;
    /**
     * 启用状态；0->正常：1->禁用
     */
    @Schema(name = "enabled", description = "启用状态；0->正常：1->禁用")
    private Boolean enabled;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @Schema(name = "deleteFlag", description = "逻辑删除（1 代表已删除），（0 代表未删除）")
    private Integer deleteFlag;


}

