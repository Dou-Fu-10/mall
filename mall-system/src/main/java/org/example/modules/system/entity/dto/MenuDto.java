package org.example.modules.system.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-09 18:52:14
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:52:14
 * @Description 后台菜单表(Menu)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private Long id;

    /**
     * 上级菜单ID
     */
    private Long parentId;
    /**
     * 子菜单数目
     */
    private Integer subCount;
    /**
     * 菜单类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     */
    private Integer type;
    /**
     * 菜单标题
     */
    private String title;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 链接地址
     */
    private String path;
    /**
     * 菜单级数
     */
    private Integer level;
    /**
     * vue组件名称
     */
    private String name;
    /**
     * vue组件
     */
    private String component;
    /**
     * 菜单排序
     */
    private Integer sort;
    /**
     * 前端图标
     */
    private String icon;
    /**
     * 前端隐藏
     */
    private Boolean hidden;
    /**
     * 访问权限
     */
    private String permission;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    private Integer deleteFlag;


}

