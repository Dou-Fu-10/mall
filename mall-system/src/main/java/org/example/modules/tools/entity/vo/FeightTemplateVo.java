package org.example.modules.tools.entity.vo;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
public class FeightTemplateVo {
    private Long id;

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

