package org.example.modules.admin.finance.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-31 15:40:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:40:17
 * @Description 会员详细说明(MemberDescription)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_member_description")
@Schema(name = "ums_member_description", description = "会员详细说明(MemberDescription)表实体类")
public class MemberDescriptionEntity extends CommonEntity<MemberDescriptionEntity> implements Serializable {
    /**
     * Id
     */
    @TableId
    private Long id;

    /**
     * 排序
     */
    @Schema(name = "sort", description = "排序")
    private Integer sort;
    /**
     * 标题
     */
    @Schema(name = "title", description = "标题")
    private String title;
    /**
     * 详情
     */
    @Schema(name = "description", description = "详情")
    private String description;

    /**
     * 创建人
     * 创建
     */
    @TableField(exist = false)
    private String createBy;
    /**
     * 更新人
     * 创建、更新
     */
    @TableField(exist = false)
    private String updateBy;
    /**
     * 创建时间
     * 创建
     */
    @TableField(exist = false)
    private Date createTime;
    /**
     * 更新时间
     * 创建、更新
     */
    @TableField(exist = false)
    private Date updateTime;
}

