package org.example.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by PanShiFu 2023-07-07 09:58:02
 *
 * @author PanShiFu
 * @date 2023-07-07 09:58:02
 * @Description 后台用户表(User)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_user")
public class UserEntity extends CommonEntity<UserEntity> implements Serializable {
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
    private Integer isAdmin;
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
     * 修改密码的时间
     */
    private Date pwdResetTime;

}

