package org.example.modules.system.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-09 18:34:52
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:34:52
 * @Description 后台用户角色表(Role)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
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
    @JsonIgnore
    private Integer adminCount;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 启用状态；true=1->正常：false=0->禁用
     */
    private Boolean enabled;
    /**
     * 更新时间
     */
    @JsonIgnore
    private Date updateTime;
    /**
     * 创建者
     */
    @JsonIgnore
    private String createBy;
    /**
     * 更新者
     */
    @JsonIgnore
    private String updateBy;
    /**
     * 创建时间
     */
    @JsonIgnore
    private Date createTime;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @JsonIgnore
    private Integer deleteFlag;


}

