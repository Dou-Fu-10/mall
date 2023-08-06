package org.example.modules.system.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.ValidationDto;
import org.example.common.core.validation.GenderValid;
import org.example.common.core.validation.PhoneValid;
import org.example.common.core.validation.UserNameValid;

import java.util.Date;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-07-09 18:34:51
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:34:51
 * @Description 后台用户表(Admin)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    /**
     * ID
     */
    @Null(groups = {ValidationDto.SelectPage.class, ValidationDto.Insert.class}, message = "参数传入错误")
    @NotNull(groups = ValidationDto.Update.class, message = "参数传入错误")
    private Long id;

    /**
     * 用户
     */
    @UserNameValid(allowNull = true, groups = {ValidationDto.SelectPage.class, ValidationDto.Update.class})
    @UserNameValid(groups = ValidationDto.Insert.class)
    private String username;
    /**
     * 密码
     */
    @JsonIgnore
    private String password;
    /**
     * 昵称
     */
    @UserNameValid(allowNull = true, groups = {ValidationDto.SelectPage.class, ValidationDto.Update.class})
    @UserNameValid(groups = ValidationDto.Insert.class)
    private String nickName;
    /**
     * 头像
     */
    @Null(groups = ValidationDto.SelectPage.class, message = "参数传入错误")
    private String icon;
    /**
     * 手机号码
     */
    @PhoneValid(allowNull = true)
    private String phone;
    /**
     * 邮箱
     */
    @Email
    private String email;
    /**
     * 性别
     */
    @GenderValid(allowNull = true)
    private String gender;
    /**
     * 备注信息
     */
    @Null(groups = {ValidationDto.SelectPage.class}, message = "参数传入错误")
    private String note;
    /**
     * 最后登录时间
     */
    @JsonIgnore
    private Date loginTime;
    /**
     * 帐号启用状态；1->正常：0->禁用
     */
    private Boolean enabled;
    /**
     * 最后修改密码的时间
     */
    @JsonIgnore
    private Date pwdResetTime;
    /**
     * 创建者
     */
    @JsonIgnore
    private String createBy;
    /**
     * 更新者
     */
    @JsonIgnore
    private String updateBy;
    /**
     * 创建日期
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

    private Set<Long> roleIds;

}

