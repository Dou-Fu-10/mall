package org.example.modules.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.exception.BaseRequestException;
import org.example.config.AuthMember;
import org.example.modules.member.service.MemberBonusService;
import org.example.modules.member.service.MemberService;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 登录注册 AuthorizationController
 * Created by Dou-Fu-10 2023/7/14
 *
 * @author Dou-Fu-10
 * @date 2023/7/14
 * @Description 登录注册
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@Tag(name = "AuthorizationController", description = "登录注册")
public class AuthorizationController {

    /**
     * 服务对象
     */
    @Resource
    private MemberService memberService;


    /**
     * 用户注册
     *
     * @param authMember 注册用户
     * @return 是否成功
     */
    @Operation(summary = "注册", description = "用户注册")
    @AnonymousPostMapping(value = "/register")
    public ResponseEntity<String> register(@NotNull @Validated @RequestBody AuthMember authMember) {
        if (memberService.register(authMember)) {
            return ResponseEntity.ok("注册成功");

        }
        throw new BaseRequestException("注册失败");
    }

    /**
     * 获取验证码
     *
     * @return /
     */
    @Operation(summary = "获取验证码")
    @AnonymousGetMapping(value = "/captcha")
    public ResponseEntity<Object> getCode() {
        return ResponseEntity.ok(memberService.generateVerificationCode());
    }

    /**
     * 获取短信验证码
     *
     * @param phone 手机号码
     * @return /
     */
    @Operation(summary = "获取短信验证码")
    @AnonymousGetMapping(value = "/sms")
    public ResponseEntity<Object> sendSMS(@RequestParam("phone") String phone) {
        return ResponseEntity.ok(memberService.sendSMS());
    }

    /**
     * 登录以后返回token
     *
     * @param authMember 登录用户
     * @param request    Http Servlet请求
     * @return token
     */
    @Operation(summary = "登录", description = "用户登录返回token")
    @AnonymousPostMapping(value = "/login")
    public ResponseEntity<Map<String, Object>> login(@Validated @RequestBody AuthMember authMember, HttpServletRequest request) {
        return ResponseEntity.ok(memberService.login(authMember, request));
    }


}
