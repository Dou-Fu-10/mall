package org.example.modules.order.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-08-03 14:28:08
 *
 * @author Dou-Fu-10
 * @date 2023-08-03 14:28:08
 * @Description 订单表(Order)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateOrderDto {
    @Schema(name = "memberReceiveAddressId", description = "收货地址ID")
    private Long memberReceiveAddressId;
    @Schema(name = "payType", description = "支付方式 : 0->未支付；1->支付宝；2->微信")
    private Integer payType;
    @Schema(name = "cartIds", description = "被选中的购物车商品ID")
    private Set<Long> cartIds;
}