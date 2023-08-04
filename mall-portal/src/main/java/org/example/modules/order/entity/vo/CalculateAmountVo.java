package org.example.modules.order.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by Dou-Fu-10 2023-08-03 21:28:08
 *
 * @author Dou-Fu-10
 * @date 2023-08-03 21:28:08
 * @Description 生成订单信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateAmountVo {
    // 总金额
    private BigDecimal totalAmount;
    // 运费
    private BigDecimal freightAmount;
    // 实际付费
    private BigDecimal payAmount;
}
