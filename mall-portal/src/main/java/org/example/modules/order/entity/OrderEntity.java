package org.example.modules.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-08-03 14:28:08
 *
 * @author Dou-Fu-10
 * @date 2023-08-03 14:28:08
 * @Description 订单表(Order)表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("oms_order")
@Schema(name = "oms_order", description = "订单表(Order)表实体类")
public class OrderEntity extends CommonEntity<OrderEntity> implements Serializable {
    /**
     * 订单id
     */
    @TableId
    private Long id;
    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 订单编号
     */
    @Schema(name = "orderSn", description = "订单编号")
    private String orderSn;
    /**
     * 提交时间
     */
    @Schema(name = "createTime", description = "提交时间")
    private Date createTime;
    /**
     * 用户帐号
     */
    @Schema(name = "memberNickname", description = "用户帐号")
    @TableField(exist = false)
    private String memberNickname;
    /**
     * 订单总金额
     */
    @Schema(name = "totalAmount", description = "订单总金额")
    private BigDecimal totalAmount;
    /**
     * 应付金额（实际支付金额）
     */
    @Schema(name = "payAmount", description = "应付金额（实际支付金额）")
    private BigDecimal payAmount;
    /**
     * 运费金额
     */
    @Schema(name = "freightAmount", description = "运费金额")
    private BigDecimal freightAmount;
    /**
     * 0->未支付；1->支付宝；2->微信；3->本地钱包支付
     */
    @Schema(name = "payType", description = "0->未支付；1->支付宝；2->微信；3->本地钱包支付")
    private Integer payType;
    /**
     * 订单来源：0->PC订单；1->app订单
     */
    @Schema(name = "sourceType", description = "订单来源：0->PC订单；1->app订单")
    private Integer sourceType;
    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     */
    @Schema(name = "status", description = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    private Integer status;
    /**
     * 物流公司(配送方式)
     */
    @Schema(name = "deliveryCompany", description = "物流公司(配送方式)")
    private String deliveryCompany;
    /**
     * 物流单号
     */
    @Schema(name = "deliverySn", description = "物流单号")
    private String deliverySn;
    /**
     * 自动确认时间（天）
     */
    @Schema(name = "autoConfirmDay", description = "自动确认时间（天）")
    private Integer autoConfirmDay;
    /**
     * 发票类型：0->不开发票；1->电子发票；2->纸质发票
     */
    @Schema(name = "billType", description = "发票类型：0->不开发票；1->电子发票；2->纸质发票")
    private Integer billType;
    /**
     * 发票抬头
     */
    @Schema(name = "billHeader", description = "发票抬头")
    private String billHeader;
    /**
     * 发票内容
     */
    @Schema(name = "billContent", description = "发票内容")
    private String billContent;
    /**
     * 收票人电话
     */
    @Schema(name = "billReceiverPhone", description = "收票人电话")
    private String billReceiverPhone;
    /**
     * 收票人邮箱
     */
    @Schema(name = "billReceiverEmail", description = "收票人邮箱")
    private String billReceiverEmail;
    /**
     * 收货人姓名
     */
    @Schema(name = "receiverName", description = "收货人姓名")
    private String receiverName;
    /**
     * 收货人电话
     */
    @Schema(name = "receiverPhone", description = "收货人电话")
    private String receiverPhone;
    /**
     * 收货人邮编
     */
    @Schema(name = "receiverPostCode", description = "收货人邮编")
    private String receiverPostCode;
    /**
     * 省份/直辖市
     */
    @Schema(name = "receiverProvince", description = "省份/直辖市")
    private String receiverProvince;
    /**
     * 城市
     */
    @Schema(name = "receiverCity", description = "城市")
    private String receiverCity;
    /**
     * 区
     */
    @Schema(name = "receiverRegion", description = "区")
    private String receiverRegion;
    /**
     * 详细地址
     */
    @Schema(name = "receiverDetailAddress", description = "详细地址")
    private String receiverDetailAddress;
    /**
     * 订单备注
     */
    @Schema(name = "note", description = "订单备注")
    private String note;
    /**
     * 确认收货状态：1=true->已确认：0=false->未确认
     */
    @Schema(name = "confirmStatus", description = "确认收货状态：1=true->已确认：0=false->未确认")
    private Boolean confirmStatus;
    /**
     * 支付时间
     */
    @Schema(name = "paymentTime", description = "支付时间")
    private Date paymentTime;
    /**
     * 发货时间
     */
    @Schema(name = "deliveryTime", description = "发货时间")
    private Date deliveryTime;
    /**
     * 确认收货时间
     */
    @Schema(name = "receiveTime", description = "确认收货时间")
    private Date receiveTime;
    /**
     * 评价时间
     */
    @Schema(name = "commentTime", description = "评价时间")
    private Date commentTime;
    /**
     * 修改时间
     */
    @Schema(name = "updateTime", description = "修改时间")
    private Date updateTime;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @Schema(name = "deleteFlag", description = "逻辑删除（1 代表已删除），（0 代表未删除）")
    private Integer deleteFlag;

    /**
     * 创建人
     * 创建
     */
    @TableField(exist = false)
    private String createBy;
    /**
     * 更新人
     * 创建、更新
     */
    @TableField(exist = false)
    private String updateBy;

}

