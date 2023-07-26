package org.example.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Jwt参数配置
 *
 * @author Zheng Jie
 * @date 2019年11月28日
 */
@Data
@ConfigurationProperties(prefix = "jwt")
public class SecurityProperties {

    /**
     * Request Headers ： Authorization
     */
    private String header;
    /**
     * JWT存储的请求头
     */
    private String tokenHead;
    /**
     * JWT加解密使用的密钥
     */
    private Long expiration;
    /**
     * JWT的超期限时间(60*60*24*7)
     */
    private String secret;
    /**
     * JWT负载中拿到开头
     */
    private String tokenHeader;

    /**
     * 令牌前缀，最后留个空格 Bearer
     */
    private String tokenStartWith;

    /**
     * 必须使用最少88位的Base64对该令牌进行编码
     */
    private String base64Secret;

    /**
     * 令牌过期时间 此处单位/毫秒
     */
    private Long tokenValidityInSeconds;

    /**
     * 在线用户 key，根据 key 查询 redis 中在线用户的数据
     */
    private String onlineKey;

    /**
     * 登录用户 key，根据 key 查询 redis 中登录用户的数据
     */
    private String loginKey;

    /**
     * 验证码 key
     */
    private String codeKey;

    /**
     * token 续期检查
     */
    private Long detect;

    /**
     * 续期时间
     */
    private Long renew;

    /**
     * 是否限制单用户登录
     */
    private Boolean singleLogin;

    public String getTokenStartWith() {
        return tokenStartWith + " ";
    }
}
