package org.example.modules.admin.system.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-09 18:34:52
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:34:52
 * @Description 后台用户表(Admin)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminVo {
    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date loginTime;
    /**
     * 帐号启用状态；0->正常：1->禁用
     */
    private Boolean enabled;
    /**
     * 最后修改密码的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonIgnore(value = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @JsonIgnore
    private Integer deleteFlag;

    private List<RoleVo> roleVoList;

}

