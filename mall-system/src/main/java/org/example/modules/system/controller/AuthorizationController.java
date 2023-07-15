package org.example.modules.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.exception.BaseRequestException;
import org.example.config.AuthUser;
import org.example.modules.system.entity.dto.AdminDto;
import org.example.modules.system.service.AdminService;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
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
    private AdminService adminService;


    @Operation(summary = "退出功能")
    @AnonymousPostMapping(value = "/logout")
    @ResponseBody
    public ResponseEntity<String> logout() {
        // TODO 退出登录
        return ResponseEntity.ok("退出登录成功");
    }

    /**
     * 用户注册
     *
     * @param resources 注册用户
     * @return 是否成功
     */
    @Operation(
            summary = "注册",
            description = "用户注册"
    )
    @AnonymousPostMapping(value = "/register")
    public ResponseEntity<String> register(@Validated @RequestBody AdminDto resources) {
        Boolean register = adminService.register(resources);
        if (register) {
            return ResponseEntity.ok("注册成功");

        }
        throw new BaseRequestException("注册失败");
    }

    /**
     * 登录以后返回token
     *
     * @param authUser 登录用户
     * @param request  Http Servlet请求
     * @return token
     */
    @Operation(
            summary = "登录",
            description = "用户登录返回token"
    )
    @AnonymousPostMapping(value = "/login")
    public ResponseEntity<Map<String, Object>> login(@Validated @RequestBody AuthUser authUser, HttpServletRequest request) {
        Map<String, Object> tokenMap = adminService.login(authUser, request);
        return ResponseEntity.ok(tokenMap);
    }
}
