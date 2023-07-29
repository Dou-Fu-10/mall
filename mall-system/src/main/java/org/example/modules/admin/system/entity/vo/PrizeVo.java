package org.example.modules.admin.system.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.modules.admin.order.entity.vo.OrderVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-29 15:47:03
 *
 * @author Dou-Fu-10
 * @date 2023-07-29 15:47:03
 * @Description 奖金
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrizeVo {
    /**
     * 订单
     */
    List<OrderVo> orderList;
    /**
     * 会员奖金池
     */
    BigDecimal memberBonus;
    /**
     * 商品奖金池
     */
    BigDecimal commodityBonus;
}
