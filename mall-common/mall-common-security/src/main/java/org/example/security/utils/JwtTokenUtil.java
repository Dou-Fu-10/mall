package org.example.security.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.security.config.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Dou-Fu-10
 * @date 2023-07-016
 * @Description redis配置
 */
@Component
@Slf4j
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "subject";
    private static final String CLAIM_KEY_CREATED = "created";

    @Resource
    private SecurityProperties securityProperties;

    /**
     * 根据荷载生成token
     * 主要是通过Jwts把荷载、失效时间、以及密钥加密生成token
     *
     * @param claims claims
     * @return token
     */
    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setId(IdUtil.simpleUUID())
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, securityProperties.getSecret())
                .compact();
    }


    /**
     * 从token中获取荷载
     * 获取荷载是通过jwts，然后船两参数，分别是secret、和token
     *
     * @param token token
     * @return Claims
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(securityProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 生成token失效时间
     *
     * @return 时间
     */
    private Date generateExpirationDate() {
        //失效时间是当前系统的时间+我们在配置文件里定义的时间
        return new Date(System.currentTimeMillis() + securityProperties.getExpiration());
    }

    /**
     * 根据token获取用户名
     *
     * @param token token
     * @return 用户名
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否有效
     * 主要通过token中的用户名和userDetail中的用户名是否一致，并且，token是否已经失效
     *
     * @param token       token
     * @param userDetails 用户信息
     * @return 是否有效
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     * @param token token
     * @return 是否失效
     */
    private boolean isTokenExpired(String token) {
        // 先获取之前设置的token的失效时间
        Date expiredDate = getExpiredDateFromToken(token);
        // 判断下，当前时间是都已经在expireDate之后
        return expiredDate.before(new Date());
    }

    /**
     * 根据token获取失效时间
     * 也是先从token中获取荷载
     * 然后从荷载中拿到到设置的失效时间
     * @param token token
     * @return 获取失效时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据用户信息生成token
     * 用户信息根据Spring security框架中的UserDetail中拿
     *
     * @param userDetails 用户信息
     * @return token
     */
    public String createToken(UserDetails userDetails) {
        // 准备一个空荷载claims，用于存储生成的key和value键值对（下面是存储生成token的时间和用户名）
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return createToken(claims);
    }


    /**
     * 当原来的token没过期时是可以刷新的
     *
     * @param oldToken 带tokenHead的token
     */
    public String refreshHeadToken(String oldToken) {
        if (StrUtil.isEmpty(oldToken)) {
            return null;
        }
        String token = oldToken.substring(securityProperties.getTokenHead().length());
        if (StrUtil.isEmpty(token)) {
            return null;
        }
        // token校验不通过
        Claims claims = getClaimsFromToken(token);
        if (claims == null) {
            return null;
        }
        // 如果token已经过期，不支持刷新
        if (isTokenExpired(token)) {
            return null;
        }
        // 如果token在30分钟之内刚刷新过，返回原token
        if (tokenRefreshJustBefore(token, 30 * 60)) {
            return token;
        } else {
            claims.put(CLAIM_KEY_CREATED, new Date());
            return createToken(claims);
        }
    }

    /**
     * 判断token在指定时间内是否刚刚刷新过
     *
     * @param token 原token
     * @param time  指定时间（秒）
     */
    private boolean tokenRefreshJustBefore(String token, int time) {
        Claims claims = getClaimsFromToken(token);
        Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
        Date refreshDate = new Date();
        //刷新时间在创建时间的指定时间内
        return refreshDate.after(created) && refreshDate.before(DateUtil.offsetSecond(created, time));
    }
}
