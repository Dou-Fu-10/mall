package org.example.modules.order.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;
import java.util.Date;

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
@TableName("oms_order_setting")
@Schema(name = "oms_order_setting", description = "订单设置表(OrderSetting)表实体类")
public class OrderSettingEntity extends CommonEntity<OrderSettingEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;
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
    /**
     * 创建人
     * 创建
     */
    @JsonIgnore
    @TableField(exist = false)
    private String createBy;
    /**
     * 更新人
     * 创建、更新
     */
    @JsonIgnore
    @TableField(exist = false)
    private String updateBy;
    /**
     * 创建时间
     * 创建
     */
    @JsonIgnore
    @TableField(exist = false)
    private Date createTime;
    /**
     * 更新时间
     * 创建、更新
     */
    @JsonIgnore
    @TableField(exist = false)
    private Date updateTime;

}

