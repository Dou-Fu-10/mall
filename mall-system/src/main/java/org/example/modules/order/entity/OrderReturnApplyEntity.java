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
 * Created by Dou-Fu-10 2023-08-10 13:31:15
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 13:31:15
 * @Description 订单退货申请(OrderReturnApply)表实体类
 */
@EqualsAndHashCode(callSuper = true)
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
     * 公司收货地址id
     */
    @Schema(name = "companyAddressId", description = "公司收货地址id")
    private Long companyAddressId;
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
     * 公司给客户的退款金额
     */
    @Schema(name = "returnAmount", description = "公司给客户的退款金额")
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
    private String proofPics;
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