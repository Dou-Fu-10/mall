package org.example.modules.order.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:29
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:29
 * @Description 订单表(Order)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    /**
     * 订单id
     */
    private Long id;
    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 提交时间
     */
    private Date createTime;
    /**
     * 会员昵称
     */
    private String nickname;
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    /**
     * 应付金额（实际支付金额）
     */
    private BigDecimal payAmount;
    /**
     * 运费金额
     */
    private BigDecimal freightAmount;
    /**
     * 支付方式：0->未支付；1->支付宝；2->微信
     */
    private Integer payType;
    /**
     * 订单来源：0->PC订单；1->app订单
     */
    private Integer sourceType;
    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     */
    private Integer status;
    /**
     * 物流公司(配送方式)
     */
    private String deliveryCompany;
    /**
     * 物流单号
     */
    private String deliverySn;
    /**
     * 自动确认时间（天）
     */
    private Integer autoConfirmDay;
    /**
     * 活动信息
     */
    private String promotionInfo;
    /**
     * 发票类型：0->不开发票；1->电子发票；2->纸质发票
     */
    private Integer billType;
    /**
     * 发票抬头
     */
    private String billHeader;
    /**
     * 发票内容
     */
    private String billContent;
    /**
     * 收票人电话
     */
    private String billReceiverPhone;
    /**
     * 收票人邮箱
     */
    private String billReceiverEmail;
    /**
     * 收货人姓名
     */
    private String receiverName;
    /**
     * 收货人电话
     */
    private String receiverPhone;
    /**
     * 收货人邮编
     */
    private String receiverPostCode;
    /**
     * 省份/直辖市
     */
    private String receiverProvince;
    /**
     * 城市
     */
    private String receiverCity;
    /**
     * 区
     */
    private String receiverRegion;
    /**
     * 详细地址
     */
    private String receiverDetailAddress;
    /**
     * 订单备注
     */
    private String note;
    /**
     * 确认收货状态：0->已确认：1->未确认
     */
    private Boolean confirmStatus;
    /**
     * 支付时间
     */
    private Date paymentTime;
    /**
     * 发货时间
     */
    private Date deliveryTime;
    /**
     * 确认收货时间
     */
    private Date receiveTime;
    /**
     * 评价时间
     */
    private Date commentTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    private Integer deleteFlag;


}

