package org.example.modules.order.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.modules.cartItem.entity.vo.CartItemVo;
import org.example.modules.member.entity.vo.MemberReceiveAddressVo;

import java.util.List;

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
public class ConfirmOrderVo {
    // 购物车订单信息
    private List<MemberReceiveAddressVo> memberReceiveAddressList;
    private List<CartItemVo> cartItemVoList;
    private CalculateAmountVo calculateAmount;
}
