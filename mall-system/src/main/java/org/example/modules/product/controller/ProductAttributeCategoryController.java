package org.example.modules.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.modules.product.entity.ProductAttributeCategoryEntity;
import org.example.modules.product.service.ProductAttributeCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:53
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:53
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
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<ProductAttributeCategoryEntity> page, ProductAttributeCategoryEntity productAttributeCategory) {
        return new ResponseEntity<>(this.productAttributeCategoryService.page(page, new QueryWrapper<>(productAttributeCategory)), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.productAttributeCategoryService.getById(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param productAttributeCategory 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody ProductAttributeCategoryEntity productAttributeCategory) {
        return new ResponseEntity<>(this.productAttributeCategoryService.save(productAttributeCategory), HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param productAttributeCategory 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody ProductAttributeCategoryEntity productAttributeCategory) {
        return new ResponseEntity<>(this.productAttributeCategoryService.updateById(productAttributeCategory), HttpStatus.OK);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return new ResponseEntity<>(this.productAttributeCategoryService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
    }
}

