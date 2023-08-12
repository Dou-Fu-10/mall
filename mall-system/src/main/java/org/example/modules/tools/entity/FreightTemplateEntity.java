package org.example.modules.tools.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-14 14:36:26
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:36:26
 * @Description 运费模版(FreightTemplate)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_freight_template")
@Schema(name = "pms_freight_template", description = "运费模版(FreightTemplate)表实体类")
public class FreightTemplateEntity extends CommonEntity<FreightTemplateEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 名字
     */
    @Schema(name = "name", description = "名字")
    private String name;
    /**
     * 计费类型:0->按重量；1->按件数
     */
    @Schema(name = "chargeType", description = "计费类型:0->按重量；1->按件数")
    private Integer chargeType;
    /**
     * 首重kg
     */
    @Schema(name = "firstWeight", description = "首重kg")
    private BigDecimal firstWeight;
    /**
     * 首费（元）
     */
    @Schema(name = "firstFee", description = "首费（元）")
    private BigDecimal firstFee;
    @Schema(name = "continueWeight", description = "${column.comment}")
    private BigDecimal continueWeight;
    @Schema(name = "continmeFee", description = "${column.comment}")
    private BigDecimal continmeFee;
    /**
     * 目的地（省、市）
     */
    @Schema(name = "dest", description = "目的地（省、市）")
    private String dest;
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

