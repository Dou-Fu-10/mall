package org.example.modules.portal.member.entity.vo;

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
public class MemberVo {
    /**
     * ID
     */
    private Long id;
    /**
     * 上级用户ID
     */
    private Long parentId;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 钱包
     */
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
     * 帐号启用状态；0->正常：1->禁用
     */
    private Integer enabled;
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
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    private Integer deleteFlag;


}

