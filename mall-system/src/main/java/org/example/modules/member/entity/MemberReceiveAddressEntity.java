package org.example.modules.member.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-31 15:49:05
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:49:05
 * @Description 会员收货地址表(MemberReceiveAddress)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_member_receive_address")
@Schema(name = "ums_member_receive_address", description = "会员收货地址表(MemberReceiveAddress)表实体类")
public class MemberReceiveAddressEntity extends CommonEntity<MemberReceiveAddressEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 收货人名称
     */
    @Schema(name = "name", description = "收货人名称")
    private String name;
    /**
     * 手机号码
     */
    @Schema(name = "phone", description = "手机号码")
    private String phone;
    /**
     * 是否为默认
     */
    @Schema(name = "defaultStatus", description = "是否为默认")
    private Integer defaultStatus;
    /**
     * 邮政编码
     */
    @Schema(name = "postCode", description = "邮政编码")
    private String postCode;
    /**
     * 省份/直辖市
     */
    @Schema(name = "province", description = "省份/直辖市")
    private String province;
    /**
     * 城市
     */
    @Schema(name = "city", description = "城市")
    private String city;
    /**
     * 区
     */
    @Schema(name = "region", description = "区")
    private String region;
    /**
     * 详细地址(街道)
     */
    @Schema(name = "detailAddress", description = "详细地址(街道)")
    private String detailAddress;

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
     * 创建时间
     * 创建
     */
    @TableField(exist = false)
    private Date createTime;
    /**
     * 更新时间
     * 创建、更新
     */
    @TableField(exist = false)
    private Date updateTime;
}

