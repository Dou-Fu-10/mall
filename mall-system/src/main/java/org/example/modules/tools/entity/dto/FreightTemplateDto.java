package org.example.modules.tools.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
public class FreightTemplateDto {
    /**
     * ID
     */
    private Long id;

    /**
     * 名字
     */
    private String name;
    /**
     * 计费类型:0->按重量；1->按件数
     */
    private Integer chargeType;
    /**
     * 首重kg
     */
    private BigDecimal firstWeight;
    /**
     * 首费（元）
     */
    private BigDecimal firstFee;
    private BigDecimal continueWeight;
    private BigDecimal continmeFee;
    /**
     * 目的地（省、市）
     */
    private String dest;


}

