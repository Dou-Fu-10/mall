package org.example.modules.tools.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.base.ValidationDto;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.tools.entity.HomeAdvertiseEntity;
import org.example.modules.tools.entity.dto.HomeAdvertiseDto;
import org.example.modules.tools.service.HomeAdvertiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 首页轮播广告表 HomeAdvertiseController
 * Created by Dou-Fu-10 2023-08-10 22:21:00
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 22:21:00
 * @Description 首页轮播广告表(HomeAdvertise)表控制层
 */
@RestController
@RequestMapping("/api/homeAdvertise")
@Tag(name = "HomeAdvertiseController", description = "")
public class HomeAdvertiseController {
    /**
     * 服务对象
     */
    @Resource
    private HomeAdvertiseService homeAdvertiseService;

    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param homeAdvertiseDto 查询实体
     * @return 所有数据
     */
    @GetMapping
    @Operation(summary = "分页查询所有数据", description = "homeAdvertise::select")
    @PreAuthorize("@hasPermission.check('homeAdvertise::select')")
    public ResponseEntity<Object> select(Page<HomeAdvertiseEntity> page, @Validated(ValidationDto.SelectPage.class) HomeAdvertiseDto homeAdvertiseDto) {
        return ResponseEntity.ok(this.homeAdvertiseService.page(page, homeAdvertiseDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "通过主键查询单条数据", description = "homeAdvertise::selectOne")
    @PreAuthorize("@hasPermission.check('homeAdvertise::selectOne')")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.homeAdvertiseService.getByHomeAdvertiseId(id));
    }

    /**
     * 新增数据
     *
     * @param homeAdvertiseDto 实体对象
     * @return 新增结果
     */
    @PostMapping
    @Operation(summary = "新增数据", description = "homeAdvertise::insert")
    @PreAuthorize("@hasPermission.check('homeAdvertise::insert')")
    public ResponseEntity<String> insert(@RequestBody @Validated(ValidationDto.Insert.class) HomeAdvertiseDto homeAdvertiseDto) {
        if (this.homeAdvertiseService.save(homeAdvertiseDto)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param homeAdvertiseDto 实体对象
     * @return 修改结果
     */
    @PutMapping
    @Operation(summary = "修改数据", description = "homeAdvertise::update")
    @PreAuthorize("@hasPermission.check('homeAdvertise::update')")
    public ResponseEntity<String> update(@RequestBody @Validated(ValidationDto.Update.class) HomeAdvertiseDto homeAdvertiseDto) {
        if (this.homeAdvertiseService.updateById(homeAdvertiseDto)) {
            return ResponseEntity.ok("修改成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("修改失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @Operation(summary = "删除数据", description = "homeAdvertise::remove")
    @PreAuthorize("@hasPermission.check('homeAdvertise::remove')")
    public ResponseEntity<String> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        if (this.homeAdvertiseService.removeByIds(ids)) {
            return ResponseEntity.ok("删除成功");
        }
        throw new BaseRequestException("删除失败");
    }
}

