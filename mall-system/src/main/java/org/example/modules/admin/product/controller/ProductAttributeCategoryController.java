package org.example.modules.admin.product.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.admin.product.entity.ProductAttributeCategoryEntity;
import org.example.modules.admin.product.entity.dto.ProductAttributeCategoryDto;
import org.example.modules.admin.product.entity.vo.ProductAttributeCategoryVo;
import org.example.modules.admin.product.service.ProductAttributeCategoryService;
import org.example.security.annotaion.rest.AnonymousDeleteMapping;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.example.security.annotaion.rest.AnonymousPutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-14 11:03:41
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 11:03:41
 * @Description 产品属性分类表(ProductAttributeCategory)表控制层
 */
@RestController
@RequestMapping("/api/productAttributeCategory")
@Tag(name = "ProductAttributeCategoryController", description = "产品属性分类表(ProductAttributeCategory)表控制层")
public class ProductAttributeCategoryController {
    /**
     * 服务对象
     */
    @Resource
    private ProductAttributeCategoryService productAttributeCategoryService;

    /**
     * 分页查询所有数据
     *
     * @param page                     分页对象
     * @param productAttributeCategory 查询实体
     * @return 所有数据
     */
    @AnonymousGetMapping
    @Operation(summary = "分页查询所有数据")
    public ResponseEntity<Object> selectAll(Page<ProductAttributeCategoryEntity> page, ProductAttributeCategoryDto productAttributeCategory) {
        return ResponseEntity.ok(this.productAttributeCategoryService.page(page, productAttributeCategory));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @AnonymousGetMapping("{id}")
    @Operation(summary = "通过主键查询单条数据")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.productAttributeCategoryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param productAttributeCategory 实体对象
     * @return 新增结果
     */
    @AnonymousPostMapping
    @Operation(summary = "新增数据")
    public ResponseEntity<Object> insert(@RequestBody ProductAttributeCategoryDto productAttributeCategory) {
        if (this.productAttributeCategoryService.save(productAttributeCategory)) {
            return ResponseEntity.ok("添加成功");
        }
        throw new BaseRequestException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param productAttributeCategory 实体对象
     * @return 修改结果
     */
    @AnonymousPutMapping
    @Operation(summary = "修改数据")
    public ResponseEntity<Object> update(@RequestBody ProductAttributeCategoryDto productAttributeCategory) {
        if (this.productAttributeCategoryService.updateById(productAttributeCategory)) {
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
    @AnonymousDeleteMapping
    @Operation(summary = "删除数据")
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        return ResponseEntity.ok(this.productAttributeCategoryService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() >= 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败");
    }

    /***
     * 获取所有商品属性分类及其下属性
     * @return 属性
     */
    @AnonymousGetMapping(value = "/list/withAttr")
    @Operation(summary = "获取所有商品属性分类及其下属性")
    public ResponseEntity<List<ProductAttributeCategoryVo>> getListWithAttr() {
        return ResponseEntity.ok(productAttributeCategoryService.getListWithAttr());
    }
}

