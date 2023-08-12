package org.example.modules.order.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
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
    @DecimalMin(value = "0", message = "请正确的输入支付信息")
    @DecimalMax(value = "3", message = "请正确的输入支付信息")
    @Schema(name = "payType", description = "0->未支付；1->支付宝；2->微信；3->本地钱包支付")
    private Integer payType;
    @Schema(name = "cartIds", description = "被选中的购物车商品ID")
    private Set<Long> cartIds;
}