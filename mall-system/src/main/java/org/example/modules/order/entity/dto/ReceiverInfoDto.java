package org.example.modules.order.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReceiverInfoDto {
    @Schema(name = "orderId", description = "订单ID")
    private Long orderId;
    @Schema(name = "receiverName", description = "收货人姓名")
    private String receiverName;
    @Schema(name = "receiverPhone", description = "收货人电话")
    private String receiverPhone;
    @Schema(name = "receiverPostCode", description = "收货人邮编")
    private String receiverPostCode;
    @Schema(name = "receiverDetailAddress", description = "详细地址")
    private String receiverDetailAddress;
    @Schema(name = "receiverProvince", description = "省份/直辖市")
    private String receiverProvince;
    @Schema(name = "receiverCity", description = "城市")
    private String receiverCity;
    @Schema(name = "receiverRegion", description = "区")
    private String receiverRegion;
}
