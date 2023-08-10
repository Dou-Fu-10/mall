package org.example.modules.order.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Created by Dou-Fu-10 2023-08-9 14:34:29
 *
 * @author Dou-Fu-10
 * @date 2023-08-9 14:34:29
 * @Description 发货
 */
@Data
public class OrderDeliveryDto {
    @Schema(name = "orderId", description = "订单id")
    private Long orderId;
    @Schema(name = "deliveryCompany", description = "物流公司")
    private String deliveryCompany;
    @Schema(name = "deliverySn", description = "物流单号")
    private String deliverySn;
}