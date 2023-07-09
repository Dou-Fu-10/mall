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
 * @Description 后台用户登录日志表(AdminLoginLog)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginLogVo {
    /**
     * ID
     */
    private Long id;

    /**
     * 登录者用户名
     */
    private String username;
    /**
     * 登录时间
     */
    private Date createTime;
    /**
     * 登录IP地址
     */
    private String ip;
    /**
     * 登录位置
     */
    private String address;
    /**
     * 浏览器登录类型
     */
    private String userAgent;


}

