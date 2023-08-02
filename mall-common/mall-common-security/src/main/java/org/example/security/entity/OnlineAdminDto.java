package org.example.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-28
 *
 * @author Dou-Fu-10
 * @date 2023-07-28
 * @Description 在线用户
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineAdminDto {
    /**
     * 用户名
     */
    private JwtAdmin jwtAdminDto;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * IP
     */
    private String ip;

    /**
     * 地址
     */
    private String address;

    /**
     * token
     */
    private String key;

    /**
     * 登录时间
     */
    private Date loginTime;


}