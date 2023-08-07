package org.example.modules.product.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.base.ValidationDto;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.product.entity.ProductCategoryEntity;
import org.example.modules.product.entity.dto.ProductCategoryDto;
import org.example.modules.product.service.ProductCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-13 22:08:15
 *
 * @author Dou-Fu-10
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
     * @param page 分页对象
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有数据", description = "productCategory::select")
    @GetMapping
    @PreAuthorize("@hasPermission.check('productCategory::select')")
    public ResponseEntity<Object> select(Page<ProductCategoryEntity> page) {
        return ResponseEntity.ok(this.productCategoryService.page(page, new ProductCategoryEntity()));
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "通过主键查询单条数据", description = "productCategory::selectOne")
    @GetMapping("{id}")
    @PreAuthorize("@hasPermission.check('productCategory::selectOne')")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.productCategoryService.getByProductCategoryId(id));
    }

    /**
     * 新增数据
     *
     * @param productCategory 实体对象
     * @return 新增结果
     */
    @Operation(summary = "新增数据", description = "productCategory::insert")
    @PostMapping
    @PreAuthorize("@hasPermission.check('productCategory::insert')")
    public ResponseEntity<Object> insert(@RequestBody @Validated(ValidationDto.Insert.class)  ProductCategoryDto productCategory) {
        if (this.productCategoryService.save(productCategory)) {
            return ResponseEntity.ok("添加成功");
        }
        throw new BaseRequestException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param productCategory 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改数据", description = "productCategory::update")
    @PutMapping
    @PreAuthorize("@hasPermission.check('productCategory::update')")
    public ResponseEntity<Object> update(@RequestBody @Validated(ValidationDto.Update.class)  ProductCategoryDto productCategory) {
        if (this.productCategoryService.updateById(productCategory)) {
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
    @Operation(summary = "删除数据", description = "productCategory::remove")
    @DeleteMapping
    @PreAuthorize("@hasPermission.check('productCategory::remove')")
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("填写正确的id");
        }
        // 校验 id 格式是否正确
        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(ids)) {
            throw new BaseRequestException("填写正确的id");
        }

        // 获取上级是 将要被删除的 id列表
        List<ProductCategoryEntity> productCategoryEntityList = productCategoryService.lambdaQuery().in(ProductCategoryEntity::getParentId, ids).list();
        if (CollectionUtils.isEmpty(productCategoryEntityList)) {
            this.productCategoryService.removeByIds(ids);
        }
        // 判断 是否有下级分类
        Set<Long> subordinateClassification = productCategoryEntityList.stream().map(ProductCategoryEntity::getParentId).collect(Collectors.toSet());
        if (CollectionUtils.isNotEmpty(subordinateClassification)) {
            if (ids.size() == subordinateClassification.size()) {
                throw new BaseRequestException("拥有下级分类不可删除");
            }
        }
        ids.removeAll(subordinateClassification);
        if (this.productCategoryService.removeByIds(ids)) {
            return ResponseEntity.ok("删除成功");
        }
        throw new BaseRequestException("删除失败");
    }

    /**
     * 修改导航栏显示状态
     *
     * @param idList    修改状态的id
     * @param navStatus 显示状态
     * @return String
     */
    @Operation(summary = "修改导航栏显示状态", description = "productCategory::updateNavStatus")
    @PostMapping(value = "/update/navStatus")
    @PreAuthorize("@hasPermission.check('productCategory::updateNavStatus')")
    public ResponseEntity<String> updateNavStatus(@RequestBody Set<Long> idList, @RequestParam("navStatus") Boolean navStatus) {
        if (productCategoryService.updateNavStatus(idList, navStatus)) {
            return ResponseEntity.ok("修改成功");
        }
        throw new BaseRequestException("修改失败");

    }

    /**
     * 修改显示状态
     *
     * @param idList     修改状态的id
     * @param showStatus 显示状态
     * @return String
     */
    @Operation(summary = "修改显示状态", description = "productCategory::updateShowStatus")
    @PostMapping(value = "/update/showStatus")
    @PreAuthorize("@hasPermission.check('productCategory::updateShowStatus')")
    public ResponseEntity<String> updateShowStatus(@RequestBody Set<Long> idList, @RequestParam("showStatus") Boolean showStatus) {
        if (productCategoryService.updateShowStatus(idList, showStatus)) {
            return ResponseEntity.ok("修改成功");
        }
        throw new BaseRequestException("修改失败");

    }

}

