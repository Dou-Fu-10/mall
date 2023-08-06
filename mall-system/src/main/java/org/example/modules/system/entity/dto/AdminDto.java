package org.example.modules.system.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.ValidationDto;
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
    @Null(groups = ValidationDto.SelectPage.class, message = "不支持查找")
    @Null(groups = ValidationDto.Insert.class, message = "参数传入错误")
    private Long id;

    /**
     * 用户
     */
    @UserNameValid(isNotBlank = false, groups = ValidationDto.SelectPage.class)
    private String username;
    /**
     * 密码
     */
    @JsonIgnore
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    @Null(groups = ValidationDto.SelectPage.class, message = "不支持查找")
    private String icon;
    /**
     * 手机号码
     */
    private String phone;
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
    @JsonIgnore
    private Date loginTime;
    /**
     * 帐号启用状态；0->正常：1->禁用
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

