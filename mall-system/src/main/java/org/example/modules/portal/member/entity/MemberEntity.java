package org.example.modules.portal.member.entity;

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
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:16
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:16
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
     * 注册时间
     */
    @Schema(name = "createTime", description = "注册时间")
    private Date createTime;

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
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @Schema(name = "deleteFlag", description = "逻辑删除（1 代表已删除），（0 代表未删除）")
    private Integer deleteFlag;
}

