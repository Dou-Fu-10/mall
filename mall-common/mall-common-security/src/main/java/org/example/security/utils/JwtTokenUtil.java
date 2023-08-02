package org.example.security.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import io.jsonwebtoken.*;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.exception.BaseRequestException;
import org.example.security.config.SecurityProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Dou-Fu-10
 * @date 2023-07-016
 * @Description redis配置
 */
@Component
@Slf4j
public class JwtTokenUtil implements InitializingBean {
    private static final String CLAIM_KEY_USERNAME = "subject";
    private static final String CLAIM_KEY_CREATED = "created";
    private JwtBuilder jwtBuilder;
    private JwtParser jwtParser;
    @Resource
    private SecurityProperties securityProperties;

    @Override
    public void afterPropertiesSet() {
        jwtParser = Jwts.parser()
                .setSigningKey(generalKey());
        jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, generalKey());
    }

    /**
     * 根据荷载生成token
     * 主要是通过Jwts把荷载、失效时间、以及密钥加密生成token
     *
     * @param claims         claims
     * @param expirationDate 过期时间
     * @return token
     */
    private String createAdminToken(Map<String, Object> claims, Date expirationDate) {
        return jwtBuilder
                // 唯一的ID
                .setId(IdUtil.simpleUUID())
                // 声明
                .setClaims(claims)
                // 主题  可以是JSON数据
                .setSubject((String) claims.get(CLAIM_KEY_USERNAME))
                // 签发者
                .setIssuer("mall")
                // 签发时间
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // 过期时间
                .setExpiration(expirationDate)
                .compact();
    }

    /**
     * 生成加密后的秘钥 secretKey
     *
     * @return
     */
    public SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(securityProperties.getBase64Secret());
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }


    /**
     * 从token中获取荷载
     * 获取荷载是通过jwts，然后船两参数，分别是secret、和token
     *
     * @param token token
     * @return Claims
     */
    public Claims getClaimsByToken(String token) {
        Claims claims;
        try {
            claims = jwtParser
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            // 当 JWT 的过期时间（expiration time）早于当前时间时，会抛出该异常。
            throw new BaseRequestException("登录信息已过期，请重新登录");
        } catch (MalformedJwtException e) {
            // 当 JWT 的格式不正确、无法正确解析时，会抛出该异常。
            throw new BaseRequestException("身份信息验证失败");
        } catch (UnsupportedJwtException e) {
            // 当 JWT 使用了不支持的特性或算法时，会抛出该异常。
            throw new BaseRequestException("身份信息验证失败");
        } catch (SignatureException e) {
            // 当 JWT 的签名验证失败时，会抛出该异常。
            throw new BaseRequestException("身份信息验证失败");
        } catch (Exception e) {
            log.error(e.getMessage());
            log.info("JWT格式验证失败:{}", token);
            throw new BaseRequestException("身份信息验证失败");
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
        return new Date(System.currentTimeMillis() + securityProperties.getTokenValidityInSeconds());
    }

    /**
     * 生成token 续约失效时间
     *
     * @return 时间
     */
    private Date generateRenewDate() {
        //失效时间是当前系统的时间+我们在配置文件里定义的时间
        return new Date(System.currentTimeMillis() + securityProperties.getRenew());
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
            Claims claims = getClaimsByToken(token);
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
     *
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
     *
     * @param token token
     * @return 获取失效时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsByToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据用户信息生成token
     * 用户信息根据Spring security框架中的UserDetail中拿
     *
     * @param userDetails 用户信息
     * @return token
     */
    public String createAdminToken(UserDetails userDetails) {
        // 准备一个空荷载claims，用于存储生成的key和value键值对（下面是存储生成token的时间和用户名）
        Map<String, Object> claims = new HashMap<>(2);
        // 用户名
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        // 生成时间
        claims.put(CLAIM_KEY_CREATED, new Date());
        return createAdminToken(claims, generateExpirationDate());
    }

    public String createMemberToken(String phone) {
        // 准备一个空荷载claims，用于存储生成的key和value键值对（下面是存储生成token的时间和用户名）
        Map<String, Object> claims = new HashMap<>(2);
        // 用户名
        claims.put(CLAIM_KEY_USERNAME, phone);
        // 生成时间
        claims.put(CLAIM_KEY_CREATED, new Date());
        return createAdminToken(claims, generateExpirationDate());
    }


    /**
     * 当原来的token没过期时是可以刷新的
     *
     * @param oldToken 带tokenHead的token
     */
    public String refreshHeadToken(String oldToken) {
        String token = resolveToken(oldToken);
        if (StringUtils.hasText(token)) {
            throw new BaseRequestException("登录信息校验失败");
        }
        // token校验不通过
        Claims claims = getClaimsByToken(token);
        if (claims == null) {
            throw new BaseRequestException("登录信息校验失败");
        }
        // 如果token已经过期，不支持刷新
        if (isTokenExpired(token)) {
            throw new BaseRequestException("登录信息已过期");
        }
        // 如果token在30分钟之内刚刷新过，返回原token
        if (tokenRefreshJustBefore(token, (int) TimeUnit.MILLISECONDS.toSeconds(securityProperties.getDetect()))) {
            return token;
        } else {
            // 刷新token，刷新后默认将在14400000毫秒后过期 即 四小时后过期
            claims.put(CLAIM_KEY_CREATED, new Date());
            return createAdminToken(claims, generateRenewDate());
        }
    }

    /**
     * 判断token在指定时间内是否刚刚刷新过
     *
     * @param token 原token
     * @param time  指定时间（秒）
     */
    private boolean tokenRefreshJustBefore(String token, int time) {
        // 获取token
        Claims claims = getClaimsByToken(token);
        // 获取token创建时间为 21:58:44
        Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
        // 获取当地时间为 22:19:26
        Date refreshDate = new Date();
        DateTime dateTime = DateUtil.offsetSecond(created, time);// 22:28:44
        // 刷新时间在创建时间的指定时间内
        // 当地时间 22:19:26 在token创建时间21:58:44 之后 并且 当地时间 22:19:26 在token创建时间 22:28:44 之前
        return refreshDate.after(created) && refreshDate.before(dateTime);
    }

    private String resolveToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken)) {
            if (bearerToken.startsWith(securityProperties.getTokenStartWith())) {
                // 去掉令牌前缀
                return bearerToken.replace(securityProperties.getTokenStartWith(), "");
            }
            return bearerToken;
        } else {
            log.debug("非法Token：{}", bearerToken);
        }
        return null;
    }

    /**
     * 初步检测Token
     *
     * @param request /
     * @return /
     */
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(securityProperties.getTokenHeader());
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(securityProperties.getTokenStartWith())) {
            // 去掉令牌前缀
            return bearerToken.replace(securityProperties.getTokenStartWith(), "");
        } else {
            log.debug("非法Token：{}", bearerToken);
        }
        return null;
    }
}
