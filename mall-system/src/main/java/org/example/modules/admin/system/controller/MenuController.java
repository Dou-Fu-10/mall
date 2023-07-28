package org.example.modules.admin.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.admin.system.entity.MenuEntity;
import org.example.modules.admin.system.entity.vo.MenuVo;
import org.example.modules.admin.system.service.MenuService;
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
 * Created by Dou-Fu-10 2023-07-09 18:52:13
 *
 * @author Dou-Fu-10
 * @date 2023-07-09 18:52:13
 * @Description 后台菜单表(Menu)表控制层
 */
@RestController
@RequestMapping("/api/menu")
@Tag(name = "MenuController", description = "后台菜单表(Menu)表控制层")
public class MenuController {
    /**
     * 服务对象
     */
    @Resource
    private MenuService menuService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param menu 查询实体
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有数据")
    @AnonymousGetMapping
    public ResponseEntity<Object> selectAll(Page<MenuEntity> page, MenuEntity menu) {
        return new ResponseEntity<>(this.menuService.page(page, menu), HttpStatus.OK);
    }

    /**
     * 查询所有的一级菜单
     *
     * @return 一级菜单列表
     */
    @Operation(summary = "查询所有的一级菜单")
    @AnonymousGetMapping("/oneLevelMenu")
    public ResponseEntity<Object> getOneLevelMenu() {
        return new ResponseEntity<>(this.menuService.getOneLevelMenu(), HttpStatus.OK);
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
        return new ResponseEntity<>(this.menuService.getById(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param menu 实体对象
     * @return 新增结果
     */
    @Operation(summary = "新增数据")
    @AnonymousPostMapping
    public ResponseEntity<Object> insert(@RequestBody MenuEntity menu) {
        return new ResponseEntity<>(this.menuService.save(menu), HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param menu 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改数据")
    @AnonymousPutMapping
    public ResponseEntity<Object> update(@RequestBody MenuEntity menu) {
        return new ResponseEntity<>(this.menuService.updateById(menu), HttpStatus.OK);
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
        return new ResponseEntity<>(this.menuService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() >= 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
    }

    /**
     * 修改菜单显示状态
     *
     * @param id     菜单id
     * @param hidden 修改的状态
     * @return String
     */
    @Operation(summary = "修改菜单显示状态")
    @RequestMapping(value = "/updateHidden/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> updateHidden(@PathVariable Long id, @RequestParam("hidden") Integer hidden) {
        if (menuService.updateHidden(id, hidden)) {
            return ResponseEntity.ok("修改成功");
        }
        throw new BaseRequestException("修改成功");
    }

    /**
     * 登录用户获取前端所需菜单
     *
     * @return 菜单
     */
    @Operation(summary = "获取前端所需菜单")
    @GetMapping(value = "/build")
    public ResponseEntity<List<MenuVo>> buildMenus() {
        Long currentUserId = 1L;
        return ResponseEntity.ok(menuService.findByUser(currentUserId));
    }
}

