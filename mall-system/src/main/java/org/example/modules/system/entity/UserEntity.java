package org.example.modules.system.entity;

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
 * Created by PanShiFu 2023-07-09 18:50:38
 *
 * @author PanShiFu
 * @date 2023-07-09 18:50:38
 * @Description 后台用户表(User)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_user")
@Schema(name = "ums_user", description = "后台用户表(User)表实体类")
public class UserEntity extends CommonEntity<UserEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 用户
     */
    @Schema(name = "username", description = "用户")
    private String username;
    /**
     * 密码
     */
    @Schema(name = "password", description = "密码")
    private String password;
    /**
     * 昵称
     */
    @Schema(name = "nickName", description = "昵称")
    private String nickName;
    /**
     * 头像
     */
    @Schema(name = "icon", description = "头像")
    private String icon;
    /**
     * 手机号码
     */
    @Schema(name = "phone", description = "手机号码")
    private String phone;
    /**
     * 是否为admin账号
     */
    @Schema(name = "isAdmin", description = "是否为admin账号")
    private Integer isAdmin;
    /**
     * 邮箱
     */
    @Schema(name = "email", description = "邮箱")
    private String email;
    /**
     * 性别
     */
    @Schema(name = "gender", description = "性别")
    private String gender;
    /**
     * 备注信息
     */
    @Schema(name = "note", description = "备注信息")
    private String note;
    /**
     * 最后登录时间
     */
    @Schema(name = "loginTime", description = "最后登录时间")
    private Date loginTime;
    /**
     * 帐号启用状态；0->正常：1->禁用
     */
    @Schema(name = "enabled", description = "帐号启用状态；0->正常：1->禁用")
    private Boolean enabled;
    /**
     * 最后修改密码的时间
     */
    @Schema(name = "pwdResetTime", description = "最后修改密码的时间")
    private Date pwdResetTime;

    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @Schema(name = "deleteFlag", description = "逻辑删除（1 代表已删除），（0 代表未删除）")
    private Integer deleteFlag;


}

