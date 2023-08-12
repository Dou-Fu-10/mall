package org.example.modules.order.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Null;
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
    @JsonIgnore
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
    @JsonIgnore
    private String nickname;
    /**
     * 订单总金额
     */
    @JsonIgnore
    private BigDecimal totalAmount;
    /**
     * 应付金额（实际支付金额）
     */
    @JsonIgnore
    private BigDecimal payAmount;
    /**
     * 运费金额
     */
    @JsonIgnore
    private BigDecimal freightAmount;
    /**
     * 0->未支付；1->支付宝；2->微信；3->本地钱包支付
     */
    @JsonIgnore
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
    @JsonIgnore
    private String deliveryCompany;
    /**
     * 物流单号
     */
    @JsonIgnore
    private String deliverySn;
    /**
     * 自动确认时间（天）
     */
    @JsonIgnore
    private Integer autoConfirmDay;
    /**
     * 发票类型：0->不开发票；1->电子发票；2->纸质发票
     */
    @JsonIgnore
    private Integer billType;
    /**
     * 发票抬头
     */
    @JsonIgnore
    private String billHeader;
    /**
     * 发票内容
     */
    @JsonIgnore
    private String billContent;
    /**
     * 收票人电话
     */
    @JsonIgnore
    private String billReceiverPhone;
    /**
     * 收票人邮箱
     */
    @JsonIgnore
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
    @JsonIgnore
    private String receiverPostCode;
    /**
     * 省份/直辖市
     */
    @JsonIgnore
    private String receiverProvince;
    /**
     * 城市
     */
    @JsonIgnore
    private String receiverCity;
    /**
     * 区
     */
    @JsonIgnore
    private String receiverRegion;
    /**
     * 详细地址
     */
    @JsonIgnore
    private String receiverDetailAddress;
    /**
     * 订单备注
     */
    @JsonIgnore
    private String note;
    /**
     * 确认收货状态：1=true->已确认：0=false->未确认
     */
    @JsonIgnore
    private Boolean confirmStatus;
    /**
     * 支付时间
     */
    @JsonIgnore
    private Date paymentTime;
    /**
     * 发货时间
     */
    @JsonIgnore
    private Date deliveryTime;
    /**
     * 确认收货时间
     */
    @JsonIgnore
    private Date receiveTime;
    /**
     * 评价时间
     */
    @JsonIgnore
    private Date commentTime;
    /**
     * 修改时间
     */
    @JsonIgnore
    private Date updateTime;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @JsonIgnore
    private Integer deleteFlag;


}

