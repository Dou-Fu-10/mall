package org.example.modules.system.controller;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.example.common.core.exception.BaseRequestException;
import org.example.config.AuthUser;
import org.example.modules.system.entity.RoleEntity;
import org.example.modules.system.entity.UserEntity;
import org.example.modules.system.entity.dto.UpdatePasswordDto;
import org.example.modules.system.entity.dto.UserDto;
import org.example.modules.system.service.UserService;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by PanShiFu 2023-07-07 09:58:02
 *
 * @author PanShiFu
 * @date 2023-07-07 09:58:02
 * @Description 后台用户表(User)表控制层
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param user 查询实体
     * @return 所有数据
     */
    @Operation(
            summary = "获取分类用户信息列表",
            description = "分页获取用户列表"
    )
    @AnonymousGetMapping
    public ResponseEntity<Object> selectAll(Page<UserEntity> page, UserEntity user) {
        return new ResponseEntity<>(this.userService.page(page, new QueryWrapper<>(user)), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "获取指定用户信息")
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.userService.getById(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @Operation(summary = "添加用户")
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody UserEntity user) {
        // TODO 对数据进行校验
        return new ResponseEntity<>(this.userService.save(user), HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改指定用户信息")
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody UserEntity user) {
        // TODO 对数据进行校验
        return new ResponseEntity<>(this.userService.updateById(user), HttpStatus.OK);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @Operation(summary = "删除指定用户信息")
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        // TODO 不允许删除上级的或者同级的
        return new ResponseEntity<>(this.userService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
    }

    @Operation(summary = "修改指定用户密码")
    @PostMapping(value = "/updatePassword")
    public ResponseEntity<Object> updatePassword(@Validated @RequestBody UpdatePasswordDto updatePasswordDto) {
        // TODO 只允许本人或者超级管理员修改 , 同时对数据进行校验
        if (userService.updatePassword(updatePasswordDto)) {
            return ResponseEntity.ok("修改成功");
        }
        throw new BaseRequestException("修改失败");
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
    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@Validated @RequestBody UserDto resources) {
        Boolean register = userService.register(resources);
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
        Map<String, Object> tokenMap = userService.login(authUser, request);
        return ResponseEntity.ok(tokenMap);
    }

    /**
     * 修改帐号状态
     *
     * @param id     用户id
     * @param status 状态
     * @return String
     */
    @Operation(summary = "修改帐号状态")
    @AnonymousPostMapping(value = "/updateStatus/{id}")
    @ResponseBody
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam(value = "status") boolean status) {
        UserEntity user = new UserEntity();
        user.setEnabled(status);
        user.setId(id);
        if (userService.updateStatus(user)) {
            return ResponseEntity.ok("修改成功");
        }
        throw new BaseRequestException("修改失败");
    }

    /**
     * 给用户分配角色
     *
     * @param userId  用户id
     * @param roleIds 角色id 列表
     * @return String
     */
    @Operation(summary = "给用户分配角色")
    @AnonymousPostMapping(value = "/role/update")
    @ResponseBody
    public ResponseEntity<String> updateRole(@RequestParam("userId") Long userId,
                                             @RequestParam("roleIds") List<Long> roleIds) {
        if (userService.updateRole(userId, roleIds)) {
            return ResponseEntity.ok("修改成功");
        }
        throw new BaseRequestException("修改失败");
    }

    /**
     * 获取指定用户的角色
     *
     * @param userId 用户id
     * @return 角色信息
     */
    @Operation(summary = "获取指定用户的角色")
    @GetMapping(value = "/role/{userId}")
    @ResponseBody
    public ResponseEntity<List<RoleEntity>> getRoleList(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getRoleList(userId));
    }


    /**
     * 获取当前登录用户信息
     *
     * @param principal 主要
     * @return 用户登录信息
     */
    @Operation(summary = "获取当前登录用户信息", description = "登录后获取登录信息")
    @AnonymousGetMapping(value = "/info")
    public ResponseEntity<Map<String, Object>> getUserInfo(Principal principal) {
        // TODO 用户登录用户自己
        if (principal == null) {
            throw new BaseRequestException("");
        }
        String username = principal.getName();
        UserEntity userEntity = userService.getByUsername(username);
        Map<String, Object> data = new HashMap<>(3);
        data.put("username", userEntity.getUsername());
        // 路由信息
        data.put("menus", userService.getMenuList(userEntity.getId()));
        // 头像
        data.put("icon", userEntity.getIcon());
        List<RoleEntity> roleList = userService.getRoleList(userEntity.getId());
        if (CollUtil.isNotEmpty(roleList)) {
            List<String> roles = roleList.stream().map(RoleEntity::getName).collect(Collectors.toList());
            data.put("roles", roles);
        }
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "退出功能")
    @AnonymousPostMapping(value = "/logout")
    @ResponseBody
    public ResponseEntity<String> logout() {
        // TODO 退出登录
        return ResponseEntity.ok("退出登录成功");
    }
}

