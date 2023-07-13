package org.example.modules.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.modules.product.entity.ProductCategoryEntity;
import org.example.modules.product.service.ProductCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by PanShiFu 2023-07-13 22:08:15
 *
 * @author PanShiFu
 * @date 2023-07-13 22:08:15
 * @Description 产品分类(ProductCategory)表控制层
 */
@RestController
@RequestMapping("/api/productCategory")
@Tag(name = "ProductCategoryController", description = "产品分类(ProductCategory)表控制层")
public class ProductCategoryController {
    /**
     * 服务对象
     */
    @Resource
    private ProductCategoryService productCategoryService;

    /**
     * 分页查询所有数据
     *
     * @param page            分页对象
     * @param productCategory 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<ProductCategoryEntity> page, ProductCategoryEntity productCategory) {
        return new ResponseEntity<>(this.productCategoryService.page(page, new QueryWrapper<>(productCategory)), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.productCategoryService.getById(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param productCategory 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody ProductCategoryEntity productCategory) {
        return new ResponseEntity<>(this.productCategoryService.save(productCategory), HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param productCategory 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody ProductCategoryEntity productCategory) {
        return new ResponseEntity<>(this.productCategoryService.updateById(productCategory), HttpStatus.OK);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return new ResponseEntity<>(this.productCategoryService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
    }
}

