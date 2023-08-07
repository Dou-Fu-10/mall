package org.example.modules.system.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.base.ValidationDto;
import org.example.common.core.entity.AdminEntity;
import org.example.common.core.exception.BaseRequestException;
import org.example.config.UpdatePassword;
import org.example.modules.system.entity.dto.AdminDto;
import org.example.modules.system.entity.vo.RoleVo;
import org.example.modules.system.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.security.Principal;
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
     * @param page     分页对象
     * @param adminDto 查询实体
     * @return 所有数据
     */
    @Operation(summary = "获取分类用户信息列表", description = "admin::select")
    @GetMapping
    @PreAuthorize("@hasPermission.check('admin::select')")
    public ResponseEntity<Object> select(Page<AdminEntity> page, @Validated(ValidationDto.SelectPage.class) AdminDto adminDto) {
        return new ResponseEntity<>(this.adminService.page(page, adminDto), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "获取指定用户信息", description = "admin::selectOne")
    @GetMapping("{id}")
    @PreAuthorize("@hasPermission.check('admin::selectOne')")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.adminService.getAdminById(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param adminDto 实体对象
     * @return 新增结果
     */
    @Operation(summary = "添加用户", description = "admin::insert")
    @PostMapping
    @PreAuthorize("@hasPermission.check('admin::insert')")
    public ResponseEntity<Object> insert(@RequestBody @Validated(ValidationDto.Insert.class) AdminDto adminDto) {
        if (this.adminService.save(adminDto)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param adminDto 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改指定用户信息", description = "admin::update")
    @PutMapping
    @PreAuthorize("@hasPermission.check('admin::update')")
    public ResponseEntity<Object> update(@RequestBody @Validated(ValidationDto.Update.class) AdminDto adminDto) {
        if (this.adminService.updateById(adminDto)) {
            return ResponseEntity.ok("修改成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("修改失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @Operation(summary = "删除指定用户信息", description = "admin::remove")
    @DeleteMapping
    @PreAuthorize("@hasPermission.check('admin::remove')")
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        // 校验 id 能为空 不得大于20  单次删除不能超过10个
        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        if (this.adminService.removeByIds(ids)) {
            return ResponseEntity.ok("删除成功");
        }
        throw new BaseRequestException("删除失败");
    }

    @Operation(summary = "修改指定用户密码", description = "admin::updatePassword")
    @PostMapping(value = "/updatePassword")
    @PreAuthorize("@hasPermission.check('admin::updatePassword')")
    public ResponseEntity<Object> updatePassword(@Validated @RequestBody UpdatePassword updatePassword) {
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
    @Operation(summary = "修改帐号状态", description = "admin::updateStatus")
    @PutMapping(value = "/updateStatus/{id}")
    @PreAuthorize("@hasPermission.check('admin::updateStatus')")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam(value = "status") Boolean status) {
        if (adminService.updateStatus(id, status)) {
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
    @Operation(summary = "给用户分配角色", description = "admin::updateRole")
    @PostMapping(value = "/role/update")
    @PreAuthorize("@hasPermission.check('admin::updateRole')")
    public ResponseEntity<String> updateRole(@RequestParam("adminId") Long adminId, @RequestBody Set<Long> roleIds) {
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
    @Operation(summary = "获取指定用户的角色", description = "admin::roleList")
    @GetMapping(value = "/role/{adminId}")
    @PreAuthorize("@hasPermission.check('admin::roleList')")
    public ResponseEntity<List<RoleVo>> getRoleList(@PathVariable Long adminId) {
        return ResponseEntity.ok(adminService.getRoleListByAdminId(adminId));
    }


    /**
     * 获取当前登录用户信息
     *
     * @param principal 主要
     * @return 用户登录信息
     */
    @Operation(summary = "获取当前登录用户信息", description = "admin::info")
    @GetMapping(value = "/info")
    @PreAuthorize("@hasPermission.check('admin::info')")
    public ResponseEntity<Map<String, Object>> getAdminInfo(Principal principal) {
        return ResponseEntity.ok(adminService.info(principal));
    }
}

