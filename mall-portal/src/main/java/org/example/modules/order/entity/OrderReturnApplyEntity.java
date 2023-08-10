package org.example.modules.order.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-08-05 17:04:10
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 17:04:10
 * @Description 订单退货申请(OrderReturnApply)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("oms_order_return_apply")
@Schema(name = "oms_order_return_apply", description = "订单退货申请(OrderReturnApply)表实体类")
public class OrderReturnApplyEntity extends CommonEntity<OrderReturnApplyEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 收货地址表id
     */
    private Long companyAddressId;
    /**
     * 退货商品id
     */
    private Long productId;

    /**
     * 订单编号
     */
    @Schema(name = "orderSn", description = "订单编号")
    private String orderSn;
    /**
     * 申请时间
     */
    @Schema(name = "createTime", description = "申请时间")
    private Date createTime;
    /**
     * 会员昵称
     */
    @Schema(name = "memberNickname", description = "会员昵称")
    private String memberNickname;
    /**
     * 退款金额
     */
    @Schema(name = "returnAmount", description = "退款金额")
    private BigDecimal returnAmount;
    /**
     * 退货人姓名
     */
    @Schema(name = "returnName", description = "退货人姓名")
    private String returnName;
    /**
     * 退货人电话
     */
    @Schema(name = "returnPhone", description = "退货人电话")
    private String returnPhone;
    /**
     * 申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝
     */
    @Schema(name = "status", description = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    private Integer status;
    /**
     * 处理时间
     */
    @Schema(name = "handleTime", description = "处理时间")
    private Date handleTime;
    /**
     * 商品图片
     */
    @Schema(name = "productPic", description = "商品图片")
    private String productPic;
    /**
     * 商品名称
     */
    @Schema(name = "productName", description = "商品名称")
    private String productName;
    /**
     * 商品销售属性：颜色：红色；尺码：xl;
     */
    @Schema(name = "productAttr", description = "商品销售属性：颜色：红色；尺码：xl;")
    private String productAttr;
    /**
     * 退货数量
     */
    @Schema(name = "productCount", description = "退货数量")
    private Integer productCount;
    /**
     * 商品单价
     */
    @Schema(name = "productPrice", description = "商品单价")
    private BigDecimal productPrice;
    /**
     * 商品实际支付单价
     */
    @Schema(name = "productRealPrice", description = "商品实际支付单价")
    private BigDecimal productRealPrice;
    /**
     * 原因
     */
    @Schema(name = "reason", description = "原因")
    private String reason;
    /**
     * 描述
     */
    @Schema(name = "description", description = "描述")
    private String description;
    /**
     * 凭证图片，以逗号隔开
     */
    @Schema(name = "proofPics", description = "凭证图片，以逗号隔开")
    private Set<String> proofPics;
    /**
     * 处理备注
     */
    @Schema(name = "handleNote", description = "处理备注")
    private String handleNote;
    /**
     * 处理人员
     */
    @Schema(name = "handleMan", description = "处理人员")
    private String handleMan;
    /**
     * 收货人
     */
    @Schema(name = "receiveMan", description = "收货人")
    private String receiveMan;
    /**
     * 收货时间
     */
    @Schema(name = "receiveTime", description = "收货时间")
    private Date receiveTime;
    /**
     * 收货备注
     */
    @Schema(name = "receiveNote", description = "收货备注")
    private String receiveNote;

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
    /**
     * 更新时间
     * 创建、更新
     */
    @TableField(exist = false)
    private Date updateTime;
}

