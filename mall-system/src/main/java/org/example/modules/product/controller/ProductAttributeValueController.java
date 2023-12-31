package org.example.modules.product.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.base.ValidationDto;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.product.entity.ProductAttributeValueEntity;
import org.example.modules.product.entity.dto.ProductAttributeValueDto;
import org.example.modules.product.service.ProductAttributeValueService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 存储产品参数信息 ProductAttributeValueController
 * Created by Dou-Fu-10 2023-07-14 13:54:16
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:54:16
 * @Description 存储产品参数信息的表(ProductAttributeValue)表控制层
 */
@RestController
@RequestMapping("/api/productAttributeValue")
@Tag(name = "ProductAttributeValueController", description = "存储产品参数信息的表(ProductAttributeValue)表控制层")
public class ProductAttributeValueController {
    /**
     * 服务对象
     */
    @Resource
    private ProductAttributeValueService productAttributeValueService;

    /**
     * 分页查询所有数据
     *
     * @param page                     分页对象
     * @param productAttributeValueDto 查询实体
     * @return 所有数据
     */
    @GetMapping
    @Operation(summary = "分页查询所有数据", description = "productAttributeValue::select")
    @PreAuthorize("@hasPermission.check('productAttributeValue::select')")
    public ResponseEntity<Object> select(Page<ProductAttributeValueEntity> page, @Validated(ValidationDto.SelectPage.class) ProductAttributeValueDto productAttributeValueDto) {
        return ResponseEntity.ok(this.productAttributeValueService.page(page, productAttributeValueDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "通过主键查询单条数据", description = "productAttributeValue::selectOne")
    @PreAuthorize("@hasPermission.check('productAttributeValue::selectOne')")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.productAttributeValueService.getByProductAttributeId(id));
    }

    /**
     * 新增数据
     *
     * @param productAttributeValue 实体对象
     * @return 新增结果
     */
    @PostMapping
    @Operation(summary = "新增数据", description = "productAttributeValue::insert")
    @PreAuthorize("@hasPermission.check('productAttributeValue::insert')")
    public ResponseEntity<Object> insert(@RequestBody @Validated(ValidationDto.Insert.class) ProductAttributeValueDto productAttributeValue) {
        if (this.productAttributeValueService.save(productAttributeValue)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param productAttributeValue 实体对象
     * @return 修改结果
     */
    @PutMapping
    @Operation(summary = "修改数据", description = "productAttributeValue::update")
    @PreAuthorize("@hasPermission.check('productAttributeValue::update')")
    public ResponseEntity<Object> update(@RequestBody @Validated(ValidationDto.Update.class) ProductAttributeValueDto productAttributeValue) {
        if (this.productAttributeValueService.updateById(productAttributeValue)) {
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
    @Operation(summary = "删除数据", description = "productAttributeValue::remove")
    @PreAuthorize("@hasPermission.check('productAttributeValue::remove')")
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        if (this.productAttributeValueService.removeByIds(ids)) {
            return ResponseEntity.ok("删除成功");
        }
        throw new BaseRequestException("删除失败");
    }
}

