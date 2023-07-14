package org.example.modules.tools.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by PanShiFu 2023-07-14 14:36:26
 *
 * @author PanShiFu
 * @date 2023-07-14 14:36:26
 * @Description 运费模版(FeightTemplate)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeightTemplateVo {
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
    private Double firstWeight;
    /**
     * 首费（元）
     */
    private Double firstFee;
    private Double continueWeight;
    private Double continmeFee;
    /**
     * 目的地（省、市）
     */
    private String dest;


}

