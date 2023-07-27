package org.example.modules.admin.product.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.admin.product.entity.ProductCategoryEntity;
import org.example.modules.admin.product.entity.dto.ProductCategoryDto;
import org.example.modules.admin.product.service.ProductCategoryService;
import org.example.security.annotaion.rest.AnonymousDeleteMapping;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.example.security.annotaion.rest.AnonymousPutMapping;
import org.springframework.http.ResponseEntity;
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
     * @param page            分页对象
     * @param productCategory 查询实体
     * @return 所有数据
     */
    @AnonymousGetMapping
    public ResponseEntity<Object> selectAll(Page<ProductCategoryEntity> page, ProductCategoryEntity productCategory) {
        return ResponseEntity.ok(this.productCategoryService.page(page, productCategory));
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @AnonymousGetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.productCategoryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param productCategory 实体对象
     * @return 新增结果
     */
    @AnonymousPostMapping
    public ResponseEntity<Object> insert(@RequestBody ProductCategoryDto productCategory) {
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
    @AnonymousPutMapping
    public ResponseEntity<Object> update(@RequestBody ProductCategoryDto productCategory) {
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
    @AnonymousDeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("填写正确的id");
        }
        // 校验 id 格式是否正确
        Set<Long> collect = idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() >= 1).limit(10).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(collect)) {
            throw new BaseRequestException("填写正确的id");
        }

        List<ProductCategoryEntity> list = productCategoryService.lambdaQuery().in(ProductCategoryEntity::getParentId, collect).list();
        // 判断 是否有下级分类
        Set<Long> subordinateClassification = list.stream().map(ProductCategoryEntity::getParentId).collect(Collectors.toSet());
        if (CollectionUtils.isNotEmpty(subordinateClassification)) {
            if (collect.size() == subordinateClassification.size()) {
                throw new BaseRequestException("拥有下级分类不可删除");
            }
        }
        collect.removeAll(subordinateClassification);
        if (this.productCategoryService.removeByIds(collect)) {
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
    @Operation(summary = "修改导航栏显示状态")
    @AnonymousPostMapping(value = "/update/navStatus")
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
    @Operation(summary = "修改显示状态")
    @AnonymousPostMapping(value = "/update/showStatus")
    public ResponseEntity<String> updateShowStatus(@RequestBody Set<Long> idList, @RequestParam("showStatus") Boolean showStatus) {
        if (productCategoryService.updateShowStatus(idList, showStatus)) {
            return ResponseEntity.ok("修改成功");
        }
        throw new BaseRequestException("修改失败");

    }

}
