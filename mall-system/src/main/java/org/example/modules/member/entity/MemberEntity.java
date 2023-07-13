package org.example.modules.member.entity;

import java.util.Date;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.example.common.core.base.CommonEntity;

/**
 * Created by PanShiFu 2023-07-13 14:08:44
 *
 * @author PanShiFu
 * @date 2023-07-13 14:08:44
 * @Description 会员表(Member)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_member")
@Schema(name = "ums_member", description = "会员表(Member)表实体类")
public class MemberEntity extends CommonEntity<MemberEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 会员等级
     */
    @Schema(name = "memberLevelId", description = "会员等级")
    private Long memberLevelId;
    /**
     * 用户名
     */
    @Schema(name = "username", description = "用户名")
    private String username;
    /**
     * 密码
     */
    @Schema(name = "password", description = "密码")
    private String password;
    /**
     * 昵称
     */
    @Schema(name = "nickname", description = "昵称")
    private String nickname;
    /**
     * 手机号码
     */
    @Schema(name = "phone", description = "手机号码")
    private String phone;
    /**
     * 帐号启用状态；0->正常：1->禁用
     */
    @Schema(name = "enabled", description = "帐号启用状态；0->正常：1->禁用")
    private Integer enabled;
    /**
     * 头像
     */
    @Schema(name = "icon", description = "头像")
    private String icon;
    /**
     * 性别：0->未知；1->男；2->女
     */
    @Schema(name = "gender", description = "性别：0->未知；1->男；2->女")
    private Integer gender;
    /**
     * 生日
     */
    @Schema(name = "birthday", description = "生日")
    private Date birthday;
    /**
     * 所做城市
     */
    @Schema(name = "city", description = "所做城市")
    private String city;
    /**
     * 职业
     */
    @Schema(name = "job", description = "职业")
    private String job;
    /**
     * 个性签名
     */
    @Schema(name = "personalizedSignature", description = "个性签名")
    private String personalizedSignature;
    /**
     * 用户来源
     */
    @Schema(name = "sourceType", description = "用户来源")
    private Integer sourceType;
    /**
     * 积分
     */
    @Schema(name = "integration", description = "积分")
    private Integer integration;
    /**
     * 成长值
     */
    @Schema(name = "growth", description = "成长值")
    private Integer growth;
    /**
     * 剩余抽奖次数
     */
    @Schema(name = "luckeyCount", description = "剩余抽奖次数")
    private Integer luckeyCount;
    /**
     * 历史积分数量
     */
    @Schema(name = "historyIntegration", description = "历史积分数量")
    private Integer historyIntegration;
    /**
     * 注册时间
     */
    @Schema(name = "createTime", description = "注册时间")
    private Date createTime;


}

