package org.example.modules.order.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-08-10 13:31:15
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 13:31:15
 * @Description 订单退货申请(OrderReturnApply)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReturnApplyVo {
    /**
     * ID
     */
    @Schema(name = "id", description = "ID")
    private Long id;
    /**
     * 订单id
     */
    @JsonIgnore
    @Schema(name = "orderId", description = "订单id")
    private Long orderId;
    /**
     * 会员id
     */
    @JsonIgnore
    @Schema(name = "memberId", description = "会员id")
    private Long memberId;
    /**
     * 公司收货地址id
     */
    @JsonIgnore
    @Schema(name = "companyAddressId", description = "公司收货地址id")
    private Long companyAddressId;
    /**
     * 订单编号
     */
    @Schema(name = "orderSn", description = "订单编号")
    private String orderSn;
    /**
     * 申请退货时间
     */
    @Schema(name = "createTime", description = "申请退货时间")
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
     * 退货人（会员）姓名
     */
    @Schema(name = "returnName", description = "退货人（会员）姓名")
    private String returnName;
    /**
     * 退货人（会员）电话
     */
    @Schema(name = "returnPhone", description = "退货人（会员）电话")
    private String returnPhone;
    /**
     * 申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝
     */
    @Schema(name = "status", description = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    private Integer status;
    /**
     * 公司处理时间
     */
    @JsonIgnore
    @Schema(name = "handleTime", description = "公司处理时间")
    private Date handleTime;
    /**
     * 用户退货原因
     */
    @JsonIgnore
    @Schema(name = "reason", description = "用户退货原因")
    private String reason;
    /**
     * 退货原因id
     */
    @Schema(name = "reasonId", description = "退货原因id")
    private Long reasonId;
    /**
     * 用户退货问题描述
     */
    @Schema(name = "description", description = "用户退货问题描述")
    private String description;
    /**
     * 凭证图片，以逗号隔开
     */
    @Schema(name = "proofPics", description = "凭证图片，以逗号隔开")
    private String proofPics;
    /**
     * 公司处理备注
     */
    @JsonIgnore
    @Schema(name = "handleNote", description = "公司处理备注")
    private String handleNote;
    /**
     * 公司处理人员
     */
    @JsonIgnore
    @Schema(name = "handleMan", description = "公司处理人员")
    private String handleMan;
    /**
     * 公司收货人
     */
    @JsonIgnore
    @Schema(name = "receiveMan", description = "公司收货人")
    private String receiveMan;
    /**
     * 公司收货时间
     */
    @JsonIgnore
    @Schema(name = "receiveTime", description = "公司收货时间")
    private Date receiveTime;
    /**
     * 公司收货备注
     */
    @JsonIgnore
    @Schema(name = "receiveNote", description = "公司收货备注")
    private String receiveNote;
    /**
     * 公司备注
     */
    @JsonIgnore
    @Schema(name = "remark", description = "公司备注")
    private String remark;

}

