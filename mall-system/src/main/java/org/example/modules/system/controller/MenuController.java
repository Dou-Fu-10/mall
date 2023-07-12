package org.example.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.system.entity.MenuEntity;
import org.example.modules.system.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by PanShiFu 2023-07-09 18:52:13
 *
 * @author PanShiFu
 * @date 2023-07-09 18:52:13
 * @Description 后台菜单表(Menu)表控制层
 */
@RestController
@RequestMapping("/api/menu")
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
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<MenuEntity> page, MenuEntity menu) {
        return new ResponseEntity<>(this.menuService.page(page, new QueryWrapper<>(menu)), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "通过主键查询单条数据")
    @GetMapping("{id}")
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
    @PostMapping
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
    @PutMapping
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
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return new ResponseEntity<>(this.menuService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
    }

    @Operation(summary = "修改菜单显示状态")
    @RequestMapping(value = "/updateHidden/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> updateHidden(@PathVariable Long id, @RequestParam("hidden") Integer hidden) {
        if (menuService.updateHidden(id, hidden)) {
            return ResponseEntity.ok("修改成功");
        }
        throw new BaseRequestException("修改成功");

    }
}

