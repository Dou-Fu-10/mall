package org.example.modules.member.entity.dto;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.ValidationDto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-31 15:49:05
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:49:05
 * @Description 会员表(Member)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    /**
     * ID
     */
    private Long id;
    /**
     * 上级用户ID
     */
    private Long parentId;
    /**
     * 密码
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String password;
    /**
     * 钱包
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private BigDecimal money;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 是否是vip
     */
    private Boolean isVip;
    /**
     * 帐号启用状态；true=1->正常：false=0->禁用
     */
    private Boolean enabled;
    /**
     * 头像
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String icon;
    /**
     * 会员性别
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String gender;
    /**
     * 生日
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private Date birthday;
    /**
     * 所做城市
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String city;
    /**
     * 邀请码
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String invitationCode;
    /**
     * 职业
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String job;
    /**
     * 个性签名
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String personalizedSignature;

}

