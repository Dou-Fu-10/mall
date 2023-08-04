package org.example.modules.order.entity.dto;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Created by Dou-Fu-10 2023-08-03 23:22:56
 *
 * @author Dou-Fu-10
 * @date 2023-08-03 23:22:56
 * @Description 订单设置表(OrderSetting)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSettingDto {
    @Schema(name = "id", description = "${column.comment}")
    private Long id;

    /**
     * 秒杀订单超时关闭时间(分)
     */
    @Schema(name = "flashOrderOvertime", description = "秒杀订单超时关闭时间(分)")
    private Integer flashOrderOvertime;
    /**
     * 正常订单超时时间(分)
     */
    @Schema(name = "normalOrderOvertime", description = "正常订单超时时间(分)")
    private Integer normalOrderOvertime;
    /**
     * 发货后自动确认收货时间（天）
     */
    @Schema(name = "confirmOvertime", description = "发货后自动确认收货时间（天）")
    private Integer confirmOvertime;
    /**
     * 自动完成交易时间，不能申请售后（天）
     */
    @Schema(name = "finishOvertime", description = "自动完成交易时间，不能申请售后（天）")
    private Integer finishOvertime;
    /**
     * 订单完成后自动好评时间（天）
     */
    @Schema(name = "commentOvertime", description = "订单完成后自动好评时间（天）")
    private Integer commentOvertime;


}

