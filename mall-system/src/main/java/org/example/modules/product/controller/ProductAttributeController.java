package org.example.modules.product.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.base.ValidationDto;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.product.entity.ProductAttributeEntity;
import org.example.modules.product.entity.dto.ProductAttributeDto;
import org.example.modules.product.service.ProductAttributeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-14 12:49:51
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 12:49:51
 * @Description 商品属性参数表(ProductAttribute)表控制层
 */
@RestController
@RequestMapping("/api/productAttribute")
@Tag(name = "ProductAttributeController", description = "商品属性参数表(ProductAttribute)表控制层")
public class ProductAttributeController {
    /**
     * 服务对象
     */
    @Resource
    private ProductAttributeService productAttributeService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @GetMapping
    @Operation(summary = "分页查询所有数据", description = "productAttribute::select")
    @PreAuthorize("@hasPermission.check('productAttribute::select')")
    public ResponseEntity<Object> select(Page<ProductAttributeEntity> page) {
        return ResponseEntity.ok(this.productAttributeService.page(page, new ProductAttributeDto()));
    }

    /**
     * 通过商品属性分类id 查询商品属性 分页后所有数据
     *
     * @param page             分页对象
     * @param productAttribute 查询实体
     * @return 所有数据
     */
    @GetMapping("/getProductAttributeByProductAttributeCategoryId")
    @Operation(summary = "通过商品属性分类id 查询商品属性 分页后所有数据", description = "productAttribute::update")
    @PreAuthorize("@hasPermission.check('productAttribute::update')")
    public ResponseEntity<Object> getProductAttributeByProductAttributeCategoryId(Page<ProductAttributeEntity> page,  @Validated(ValidationDto.SelectList.class) ProductAttributeDto productAttribute) {
        return ResponseEntity.ok(this.productAttributeService.getProductAttributeByProductAttributeCategoryId(page, productAttribute));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "通过主键查询单条数据", description = "productAttribute::selectOne")
    @PreAuthorize("@hasPermission.check('productAttribute::selectOne')")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.productAttributeService.getByProductAttributeId(id));
    }

    /**
     * 新增数据
     *
     * @param productAttribute 实体对象
     * @return 新增结果
     */
    @PostMapping
    @Operation(summary = "新增数据", description = "productAttribute::insert")
    @PreAuthorize("@hasPermission.check('productAttribute::insert')")
    public ResponseEntity<Object> insert(@RequestBody @Validated(ValidationDto.Insert.class)  ProductAttributeDto productAttribute) {
        if (this.productAttributeService.save(productAttribute)) {
            return ResponseEntity.ok("添加成功");
        }
        throw new BaseRequestException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param productAttribute 实体对象
     * @return 修改结果
     */
    @PutMapping
    @Operation(summary = "修改数据", description = "productAttribute::update")
    @PreAuthorize("@hasPermission.check('productAttribute::update')")
    public ResponseEntity<Object> update(@RequestBody  @Validated(ValidationDto.Update.class) ProductAttributeDto productAttribute) {
        if (this.productAttributeService.updateById(productAttribute)) {
            return ResponseEntity.ok("修改成功");
        }
        throw new BaseRequestException("修改失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @Operation(summary = "删除数据", description = "productAttribute::remove")
    @PreAuthorize("@hasPermission.check('productAttribute::remove')")
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        if (this.productAttributeService.removeByIds(ids)) {
            return ResponseEntity.ok("删除成功");
        }
        throw new BaseRequestException("删除失败");
    }
}

