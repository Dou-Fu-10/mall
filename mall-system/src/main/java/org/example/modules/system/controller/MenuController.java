package org.example.modules.system.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.base.ValidationDto;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.system.entity.MenuEntity;
import org.example.modules.system.entity.dto.MenuDto;
import org.example.modules.system.entity.vo.MenuVo;
import org.example.modules.system.service.MenuService;
import org.example.security.utils.SecurityUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 后台菜单 MenuController
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
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有数据", description = "menu::select")
    @GetMapping
    @PreAuthorize("@hasPermission.check('menu::select')")
    public ResponseEntity<Object> select(Page<MenuEntity> page) {
        return new ResponseEntity<>(this.menuService.page(page, new MenuDto()), HttpStatus.OK);
    }

    /**
     * 查询所有的一级菜单
     *
     * @return 一级菜单列表
     */
    @Operation(summary = "查询所有的一级菜单", description = "menu::oneLevelMenu")
    @GetMapping("/oneLevelMenu")
    @PreAuthorize("@hasPermission.check('menu::oneLevelMenu')")
    public ResponseEntity<Object> getOneLevelMenu() {
        return new ResponseEntity<>(this.menuService.getOneLevelMenu(), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "通过主键查询单条数据", description = "menu::selectOne")
    @GetMapping("{id}")
    @PreAuthorize("@hasPermission.check('menu::selectOne')")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.menuService.getByMenuId(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param menuDto 实体对象
     * @return 新增结果
     */
    @Operation(summary = "新增数据", description = "menu::insert")
    @PostMapping
    @PreAuthorize("@hasPermission.check('menu::insert')")
    public ResponseEntity<Object> insert(@RequestBody @Validated(ValidationDto.Insert.class) MenuDto menuDto) {
        return new ResponseEntity<>(this.menuService.save(menuDto), HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param menuDto 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改数据", description = "menu::update")
    @PutMapping
    @PreAuthorize("@hasPermission.check('menu::update')")
    public ResponseEntity<Object> update(@RequestBody @Validated(ValidationDto.Update.class) MenuDto menuDto) {
        return new ResponseEntity<>(this.menuService.updateById(menuDto), HttpStatus.OK);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @Operation(summary = "删除数据", description = "menu::remove")
    @DeleteMapping
    @PreAuthorize("@hasPermission.check('menu::remove')")
    public ResponseEntity<Object> remove(@RequestBody @NotNull Set<Long> idList) {

        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(ids)) {
            throw new BaseRequestException("请正确的填写id");
        }
        return new ResponseEntity<>(this.menuService.removeByIds(ids) ? "删除成功" : "删除失败", HttpStatus.OK);
    }

    /**
     * 修改菜单显示状态
     *
     * @param id     菜单id
     * @param hidden 修改的状态
     * @return String
     */
    @Operation(summary = "修改菜单显示状态", description = "menu::updateHidden")
    @PostMapping(value = "/updateHidden/{id}")
    @PreAuthorize("@hasPermission.check('menu::updateHidden')")
    public ResponseEntity<String> updateHidden(@PathVariable Long id, @RequestParam("hidden") Boolean hidden) {
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
    @Operation(summary = "获取前端所需菜单", description = "menu::buildMenus")
    @GetMapping(value = "/build")
    @PreAuthorize("@hasPermission.check('menu::buildMenus')")
    public ResponseEntity<List<MenuVo>> buildMenus() {
        return ResponseEntity.ok(menuService.findByUser(SecurityUtils.getCurrentUserId()));
    }
}

