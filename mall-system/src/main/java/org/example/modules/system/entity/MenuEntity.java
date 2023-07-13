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
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-09 18:52:13
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:52:13
 * @Description 后台菜单表(Menu)表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_menu")
@Schema(name = "ums_menu", description = "后台菜单表(Menu)表实体类")
public class MenuEntity extends CommonEntity<MenuEntity> implements Serializable {
    @TableId
    private Long id;

    /**
     * 上级菜单ID
     */
    @Schema(name = "parentId", description = "上级菜单ID")
    private Long parentId;
    /**
     * 子菜单数目
     */
    @Schema(name = "subCount", description = "子菜单数目")
    private Integer subCount;
    /**
     * 菜单类型
     */
    @Schema(name = "type", description = "菜单类型")
    private Integer type;
    /**
     * 菜单标题
     */
    @Schema(name = "title", description = "菜单标题")
    private String title;
    /**
     * 更新时间
     */
    @Schema(name = "updateTime", description = "更新时间")
    private Date updateTime;
    /**
     * 创建者
     */
    @Schema(name = "createBy", description = "创建者")
    private String createBy;
    /**
     * 菜单级数
     */
    @Schema(name = "level", description = "菜单级数")
    private Integer level;
    /**
     * vue组件名称
     */
    @Schema(name = "name", description = "vue组件名称")
    private String name;
    /**
     * vue组件
     */
    @Schema(name = "component", description = "vue组件")
    private String component;
    /**
     * 菜单排序
     */
    @Schema(name = "sort", description = "菜单排序")
    private Integer sort;
    /**
     * 前端图标
     */
    @Schema(name = "icon", description = "前端图标")
    private String icon;
    /**
     * 前端隐藏
     */
    @Schema(name = "hidden", description = "前端隐藏(0隐藏，1显示)")
    private Boolean hidden;
    /**
     * 访问权限
     */
    @Schema(name = "permission", description = "访问权限标识符")
    private String permission;
    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;
    /**
     * 更新者
     */
    @Schema(name = "updateBy", description = "更新者")
    private String updateBy;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @Schema(name = "deleteFlag", description = "逻辑删除（1 代表已删除），（0 代表未删除）")
    private Integer deleteFlag;


}

