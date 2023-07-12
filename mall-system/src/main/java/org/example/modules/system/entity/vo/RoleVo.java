package org.example.modules.system.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by PanShiFu 2023-07-09 18:34:52
 *
 * @author PanShiFu
 * @date 2023-07-09 18:34:52
 * @Description 后台用户角色表(Role)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo {
    private Long id;

    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 后台用户数量
     */
    private Integer adminCount;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 启用状态；0->正常：1->禁用
     */
    private Boolean enabled;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    private Integer deleteFlag;


}

