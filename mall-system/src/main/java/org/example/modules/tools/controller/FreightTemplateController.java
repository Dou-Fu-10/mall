package org.example.modules.tools.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.tools.entity.FreightTemplateEntity;
import org.example.modules.tools.entity.dto.FreightTemplateDto;
import org.example.modules.tools.service.FreightTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 运费模版 FreightTemplateController
 * Created by Dou-Fu-10 2023-07-14 14:36:26
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:36:26
 * @Description 运费模版(FreightTemplate)表控制层
 */
@RestController
@RequestMapping("/api/freightTemplate")
@Tag(name = "FreightTemplateController", description = "运费模版(FreightTemplate)表控制层")
public class FreightTemplateController {
    /**
     * 服务对象
     */
    @Resource
    private FreightTemplateService freightTemplateService;

    /**
     * 分页查询所有数据
     *
     * @param page            分页对象
     * @param FreightTemplate 查询实体
     * @return 所有数据
     */
    @GetMapping
    @Operation(summary = "分页查询所有数据", description = "freightTemplate::select")
    @PreAuthorize("@hasPermission.check('freightTemplate::select')")
    public ResponseEntity<Object> select(Page<FreightTemplateEntity> page, FreightTemplateEntity FreightTemplate) {
        return ResponseEntity.ok(this.freightTemplateService.page(page, new QueryWrapper<>(FreightTemplate)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "通过主键查询单条数据", description = "freightTemplate::selectOne")
    @PreAuthorize("@hasPermission.check('freightTemplate::selectOne')")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.freightTemplateService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param FreightTemplate 实体对象
     * @return 新增结果
     */
    @PostMapping
    @Operation(summary = "新增数据", description = "freightTemplate::insert")
    @PreAuthorize("@hasPermission.check('freightTemplate::insert')")
    public ResponseEntity<Object> insert(@RequestBody FreightTemplateDto FreightTemplate) {
        if (this.freightTemplateService.save(FreightTemplate)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param FreightTemplate 实体对象
     * @return 修改结果
     */
    @PutMapping
    @Operation(summary = "修改数据", description = "freightTemplate::update")
    @PreAuthorize("@hasPermission.check('freightTemplate::update')")
    public ResponseEntity<Object> update(@RequestBody FreightTemplateDto FreightTemplate) {
        if (this.freightTemplateService.updateById(FreightTemplate)) {
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
    @DeleteMapping
    @Operation(summary = "删除数据", description = "freightTemplate::remove")
    @PreAuthorize("@hasPermission.check('freightTemplate::remove')")
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        if (this.freightTemplateService.removeByIds(ids)) {
            return ResponseEntity.ok("删除成功");
        }
        throw new BaseRequestException("删除失败");
    }
}

