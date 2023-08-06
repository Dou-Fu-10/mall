package org.example.modules.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.exception.BaseRequestException;
import org.example.config.AuthAdmin;
import org.example.modules.system.entity.dto.AdminDto;
import org.example.modules.system.service.AdminService;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /**
     * 退出功能
     *
     * @return /
     */
    @Operation(summary = "退出功能")
    @GetMapping(value = "/logout")
    public ResponseEntity<String> logout() {
        adminService.logout();
        return ResponseEntity.ok("退出登录成功");
    }


    /**
     * 用户注册
     *
     * @param resources 注册用户
     * @return 是否成功
     */
//    @Operation(summary = "注册")
//    @AnonymousPostMapping(value = "/register")
//    public ResponseEntity<String> register(@Validated @RequestBody AdminDto resources) {
//        resources.setPassword("123456");
//        if (adminService.register(resources)) {
//            return ResponseEntity.ok("注册成功");
//
//        }
//        throw new BaseRequestException("注册失败");
//    }

    /**
     * 登录以后返回token
     *
     * @param authAdmin 登录用户
     * @param request   Http Servlet请求
     * @return token
     */
    @Operation(summary = "登录")
    @AnonymousPostMapping(value = "/login")
    public ResponseEntity<Map<String, Object>> login(@Validated @RequestBody AuthAdmin authAdmin, HttpServletRequest request) {
        return ResponseEntity.ok(adminService.login(authAdmin, request));
    }

    /**
     * token续约
     *
     * @param request token
     * @return token
     */
    @Operation(summary = "token续约")
    @GetMapping(value = "/refresh")
    public ResponseEntity<String> refresh(HttpServletRequest request) {
        return ResponseEntity.ok(adminService.refreshHeadToken(request));
    }

}
