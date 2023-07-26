package org.example.modules.admin.order.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:31
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:31
 * @Description 订单设置表(OrderSetting)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSettingDto {
    /**
     * ID
     */
    private Long id;

    /**
     * 秒杀订单超时关闭时间(分)
     */
    private Integer flashOrderOvertime;
    /**
     * 正常订单超时时间(分)
     */
    private Integer normalOrderOvertime;
    /**
     * 发货后自动确认收货时间（天）
     */
    private Integer confirmOvertime;
    /**
     * 自动完成交易时间，不能申请售后（天）
     */
    private Integer finishOvertime;
    /**
     * 订单完成后自动好评时间（天）
     */
    private Integer commentOvertime;


}

