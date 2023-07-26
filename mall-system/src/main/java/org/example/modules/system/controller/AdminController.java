package org.example.modules.system.controller;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.entity.AdminEntity;
import org.example.common.core.exception.BaseRequestException;
import org.example.config.UpdatePassword;
import org.example.modules.system.entity.RoleEntity;
import org.example.modules.system.service.AdminService;
import org.example.security.annotaion.rest.AnonymousDeleteMapping;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.example.security.annotaion.rest.AnonymousPutMapping;
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
 * Created by Dou-Fu-10 2023-07-07 09:58:02
 *
 * @author Dou-Fu-10
 * @date 2023-07-07 09:58:02
 * @Description 后台用户表(Admin)表控制层
 */
@RestController
@RequestMapping("/api/admin")
@Tag(name = "AdminController", description = "后台用户表(Admin)表控制层")
public class AdminController {
    /**
     * 服务对象
     */
    @Resource
    private AdminService adminService;

    /**
     * 分页查询所有数据
     *
     * @param page  分页对象
     * @param admin 查询实体
     * @return 所有数据
     */
    @Operation(
            summary = "获取分类用户信息列表",
            description = "分页获取用户列表"
    )
    @AnonymousGetMapping
    public ResponseEntity<Object> selectAll(Page<AdminEntity> page, AdminEntity admin) {
        return new ResponseEntity<>(this.adminService.page(page, new QueryWrapper<>(admin)), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "获取指定用户信息")
    @AnonymousGetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.adminService.getById(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param admin 实体对象
     * @return 新增结果
     */
    @Operation(summary = "添加用户")
    @AnonymousPostMapping
    public ResponseEntity<Object> insert(@RequestBody AdminEntity admin) {
        // TODO 对数据进行校验
        return new ResponseEntity<>(this.adminService.save(admin), HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param admin 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改指定用户信息")
    @AnonymousPutMapping
    public ResponseEntity<Object> update(@RequestBody AdminEntity admin) {
        // TODO 对数据进行校验
        return new ResponseEntity<>(this.adminService.updateById(admin), HttpStatus.OK);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @Operation(summary = "删除指定用户信息")
    @AnonymousDeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        // TODO 不允许删除上级的或者同级的
        return new ResponseEntity<>(this.adminService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
    }

    @Operation(summary = "修改指定用户密码")
    @AnonymousPostMapping(value = "/updatePassword")
    public ResponseEntity<Object> updatePassword(@Validated @RequestBody UpdatePassword updatePassword) {
        // TODO 只允许本人或者超级管理员修改 , 同时对数据进行校验
        if (adminService.updatePassword(updatePassword)) {
            return ResponseEntity.ok("修改成功");
        }
        throw new BaseRequestException("修改失败");
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
        AdminEntity admin = new AdminEntity();
        admin.setEnabled(status);
        admin.setId(id);
        if (adminService.updateStatus(admin)) {
            return ResponseEntity.ok("修改成功");
        }
        throw new BaseRequestException("修改失败");
    }

    /**
     * 给用户分配角色
     *
     * @param adminId 用户id
     * @param roleIds 角色id 列表
     * @return String
     */
    @Operation(summary = "给用户分配角色")
    @AnonymousPostMapping(value = "/role/update")
    @ResponseBody
    public ResponseEntity<String> updateRole(@RequestParam("adminId") Long adminId,
                                             @RequestParam("roleIds") List<Long> roleIds) {
        if (adminService.updateRole(adminId, roleIds)) {
            return ResponseEntity.ok("修改成功");
        }
        throw new BaseRequestException("修改失败");
    }

    /**
     * 获取指定用户的角色
     *
     * @param adminId 用户id
     * @return 角色信息
     */
    @Operation(summary = "获取指定用户的角色")
    @AnonymousGetMapping(value = "/role/{adminId}")
    @ResponseBody
    public ResponseEntity<List<RoleEntity>> getRoleList(@PathVariable Long adminId) {
        return ResponseEntity.ok(adminService.getRoleList(adminId));
    }


    /**
     * 获取当前登录用户信息
     *
     * @param principal 主要
     * @return 用户登录信息
     */
    @Operation(summary = "获取当前登录用户信息", description = "登录后获取登录信息")
    @AnonymousGetMapping(value = "/info")
    public ResponseEntity<Map<String, Object>> getAdminInfo(Principal principal) {
        // TODO 用户登录用户自己
        if (principal == null) {
            throw new BaseRequestException("");
        }
        String username = principal.getName();
        AdminEntity adminEntity = adminService.getByUsername(username);
        Map<String, Object> data = new HashMap<>(3);
        data.put("username", adminEntity.getUsername());
        // 路由信息
        data.put("menus", adminService.getMenuList(adminEntity.getId()));
        // 头像
        data.put("icon", adminEntity.getIcon());
        List<RoleEntity> roleList = adminService.getRoleList(adminEntity.getId());
        if (CollUtil.isNotEmpty(roleList)) {
            List<String> roles = roleList.stream().map(RoleEntity::getName).collect(Collectors.toList());
            data.put("roles", roles);
        }
        return ResponseEntity.ok(data);
    }
}

