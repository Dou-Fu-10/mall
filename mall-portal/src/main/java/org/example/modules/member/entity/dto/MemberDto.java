package org.example.modules.member.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String password;
    /**
     * 钱包
     */
    @JsonIgnore
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
    @JsonIgnore
    private Boolean isVip;
    /**
     * 帐号启用状态；true=1->正常：false=0->禁用
     */
    @JsonIgnore
    private Boolean enabled;
    /**
     * 头像
     */
    private String icon;
    /**
     * 性别：0->未知；1->男；2->女
     */
    private Integer gender;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 所做城市
     */
    private String city;
    /**
     * 邀请码
     */
    private String invitationCode;
    /**
     * 职业
     */
    private String job;
    /**
     * 个性签名
     */
    private String personalizedSignature;
    /**
     * 用户来源
     */
    private Integer sourceType;
    /**
     * 注册时间
     */
    @JsonIgnore
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonIgnore
    private Date updateTime;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @JsonIgnore
    private Integer deleteFlag;


}

