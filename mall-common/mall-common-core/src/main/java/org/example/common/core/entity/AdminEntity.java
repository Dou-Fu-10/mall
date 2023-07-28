package org.example.common.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-09 18:50:38
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:50:38
 * @Description 后台用户表(Admin)表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_admin")
public class AdminEntity extends CommonEntity<AdminEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 用户
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String icon;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 是否为admin账号
     */
    private Boolean isAdmin;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别
     */
    private String gender;
    /**
     * 备注信息
     */
    private String note;
    /**
     * 最后登录时间
     */
    private Date loginTime;
    /**
     * 帐号启用状态；0->正常：1->禁用
     */
    private Boolean enabled;
    /**
     * 最后修改密码的时间
     */
    private Date pwdResetTime;

    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    private Integer deleteFlag;
}

