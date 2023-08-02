package org.example.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * @Description 后台用户登录日志表(AdminLoginLog)表实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_admin_login_log")
@Schema(name = "ums_admin_login_log", description = "后台用户登录日志表(AdminLoginLog)表实体类")
public class AdminLoginLogEntity extends CommonEntity<AdminLoginLogEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 登录者用户名
     */
    @Schema(name = "username", description = "登录者用户名")
    private String username;
    /**
     * 登录时间
     */
    @Schema(name = "createTime", description = "登录时间")
    private Date createTime;
    /**
     * 登录IP地址
     */
    @Schema(name = "ip", description = "登录IP地址")
    private String ip;
    /**
     * 登录位置
     */
    @Schema(name = "address", description = "登录位置")
    private String address;
    /**
     * 浏览器登录类型
     */
    @Schema(name = "userAgent", description = "浏览器登录类型")
    private String userAgent;
    @TableField(exist = false)
    private String createBy;
    /**
     * 更新人
     * 创建、更新
     */
    @TableField(exist = false)
    private String updateBy;
    /**
     * 更新时间
     * 创建、更新
     */
    @TableField(exist = false)
    private Date updateTime;

    public AdminLoginLogEntity(String username, Date createTime, String ip, String address, String userAgent) {
        this.username = username;
        this.createTime = createTime;
        this.ip = ip;
        this.address = address;
        this.userAgent = userAgent;
    }


}

