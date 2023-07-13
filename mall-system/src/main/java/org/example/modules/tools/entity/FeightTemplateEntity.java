package org.example.modules.tools.entity;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.example.common.core.base.CommonEntity;

/**
 * Created by Dou-Fu-10 2023-07-13 15:33:38
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:33:38
 * @Description 运费模版(FeightTemplate)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_feight_template")
@Schema(name = "pms_feight_template", description = "运费模版(FeightTemplate)表实体类")
public class FeightTemplateEntity extends CommonEntity<FeightTemplateEntity> implements Serializable {
    @TableId
    private Long id;

    @Schema(name = "name", description = "${column.comment}")
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
    private Double firstWeight;
    /**
     * 首费（元）
     */
    @Schema(name = "firstFee", description = "首费（元）")
    private Double firstFee;
    @Schema(name = "continueWeight", description = "${column.comment}")
    private Double continueWeight;
    @Schema(name = "continmeFee", description = "${column.comment}")
    private Double continmeFee;
    /**
     * 目的地（省、市）
     */
    @Schema(name = "dest", description = "目的地（省、市）")
    private String dest;


}

