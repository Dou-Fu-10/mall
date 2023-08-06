package org.example.modules.finance.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-29 15:47:03
 *
 * @author Dou-Fu-10
 * @date 2023-07-29 15:47:03
 * @Description 奖金池(PrizePool)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrizePoolDto {
    /**
     * Id
     */
    private Long id;

    /**
     * 商品奖金池占比
     */
    private Integer productBonusesPercentage;
    /**
     * 会员奖金池占比
     */
    private Integer memberBonusesPercentage;
    /**
     * 推荐会员提成比例
     */
    private Integer recommendedCommissionPercentage;
    /**
     * 会员费用
     */
    private BigDecimal memberFees;
    /**
     * 创建日期
     */
    @JsonIgnore
    private Date createTime;
}

