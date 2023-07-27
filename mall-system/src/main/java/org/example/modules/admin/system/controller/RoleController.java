package org.example.modules.admin.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.admin.system.entity.MenuEntity;
import org.example.modules.admin.system.entity.RoleEntity;
import org.example.modules.admin.system.entity.vo.MenuVo;
import org.example.modules.admin.system.service.RoleService;
import org.example.security.annotaion.rest.AnonymousDeleteMapping;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.example.security.annotaion.rest.AnonymousPutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-09 18:15:18
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:15:18
 * @Description 后台用户角色表(Role)表控制层
 */
@RestController
@RequestMapping("/api/role")
@Tag(name = "RoleController", description = "后台用户角色表(Role)表控制层")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param role 查询实体
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有数据")
    @AnonymousGetMapping
    public ResponseEntity<Object> selectAll(Page<RoleEntity> page, RoleEntity role) {
        return new ResponseEntity<>(this.roleService.page(page,role), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "通过主键查询单条数据")
    @AnonymousGetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.roleService.getById(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param role 实体对象
     * @return 新增结果
     */
    @Operation(summary = "新增数据")
    @AnonymousPostMapping
    public ResponseEntity<Object> insert(@RequestBody RoleEntity role) {
        return new ResponseEntity<>(this.roleService.save(role), HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param role 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改数据")
    @AnonymousPutMapping
    public ResponseEntity<Object> update(@RequestBody RoleEntity role) {
        return new ResponseEntity<>(this.roleService.updateById(role), HttpStatus.OK);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @Operation(summary = "删除数据")
    @AnonymousDeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return new ResponseEntity<>(this.roleService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
    }

    /**
     * 修改角色状态
     *
     * @param id     角色id
     * @param status 角色状态
     * @return String
     */
    @Operation(summary = "修改角色状态")
    @AnonymousPostMapping(value = "/updateStatus/{id}")
    @ResponseBody
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam(value = "status") Boolean status) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setEnabled(status);
        roleEntity.setId(id);
        if (roleService.update(roleEntity)) {
            return ResponseEntity.ok("修改成功");
        }
        throw new BaseRequestException("修改失败");
    }

    /**
     * 获取角色相关菜单
     *
     * @param roleId 角色id
     * @return 角色关联菜单
     */
    @Operation(summary = "获取角色相关菜单")
    @AnonymousGetMapping(value = "/listMenu/{roleId}")
    public ResponseEntity<List<MenuVo>> listMenu(@PathVariable Long roleId) {
        List<MenuEntity> roleList = roleService.listMenu(roleId);
        return ResponseEntity.ok(BeanCopy.copytList(roleList, MenuVo.class));
    }


    /**
     * 给角色分配菜单
     *
     * @param roleId  角色id
     * @param menuIds 菜单id列表
     * @return String
     */
    @Operation(summary = "给角色分配菜单")
    @AnonymousPostMapping(value = "/allocMenu")
    @ResponseBody
    public ResponseEntity<String> allocMenu(@RequestParam Long roleId, @RequestParam List<Long> menuIds) {
        if (roleService.allocMenu(roleId, menuIds)) {
            return ResponseEntity.ok("分配成功");
        }
        throw new BaseRequestException("分配失败");
    }

}
