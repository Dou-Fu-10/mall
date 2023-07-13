package org.example.modules.order.entity.vo;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by Dou-Fu-10 2023-07-13 15:31:37
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:31:37
 * @Description 订单设置表(OrderSetting)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSettingVo {
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

