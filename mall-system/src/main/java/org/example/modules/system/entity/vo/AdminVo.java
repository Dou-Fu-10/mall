package org.example.modules.system.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by PanShiFu 2023-07-09 18:34:52
 *
 * @author PanShiFu
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
    private Integer enabled;
    /**
     * 最后修改密码的时间
     */
    private Date pwdResetTime;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 创建日期
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
