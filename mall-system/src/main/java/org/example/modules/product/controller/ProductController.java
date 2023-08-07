package org.example.modules.product.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.base.ValidationDto;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.product.entity.ProductEntity;
import org.example.modules.product.entity.dto.ProductDto;
import org.example.modules.product.entity.dto.ProductDtoParam;
import org.example.modules.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-14 13:05:47
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:05:47
 * @Description 商品信息(Product)表控制层
 */
@RestController
@RequestMapping("/api/product")
@Tag(name = "ProductController", description = "商品信息(Product)表控制层")
public class ProductController {
    /**
     * 服务对象
     */
    @Resource
    private ProductService productService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param productDto 查询实体
     * @return 所有数据
     */
    @GetMapping
    @Operation(summary = "分页查询所有数据", description = "product::select")
    @PreAuthorize("@hasPermission.check('product::select')")
    public ResponseEntity<Object> select(Page<ProductEntity> page, @Validated(ValidationDto.SelectPage.class)  ProductDto productDto) {
        return ResponseEntity.ok(this.productService.page(page, productDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "通过主键查询单条数据", description = "product::selectOne")
    @PreAuthorize("@hasPermission.check('product::selectOne')")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        ProductDto productDto = new ProductDto();
        productDto.setId((Long) id);
        return ResponseEntity.ok(this.productService.page(new Page<>(), productDto));
    }

    /**
     * 新增数据
     *
     * @param product 实体对象
     * @return 新增结果
     */
    @PostMapping
    @Operation(summary = "新增数据", description = "product::insert")
    @PreAuthorize("@hasPermission.check('product::insert')")
    public ResponseEntity<Object> insert(@RequestBody @Validated(ValidationDto.Insert.class)  ProductDtoParam product) {
        if (this.productService.save(product)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param product 实体对象
     * @return 修改结果
     */
    @PutMapping
    @Operation(summary = "修改数据", description = "product::update")
    @PreAuthorize("@hasPermission.check('product::update')")
    public ResponseEntity<Object> update(@RequestBody @Validated(ValidationDto.Update.class)  ProductDtoParam product) {
        if (this.productService.updateById(product)) {
            return ResponseEntity.ok("修改成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("修改失败");
    }

    /**
     * 修改商品状态
     *
     * @param id     用户id
     * @param status 状态
     * @return String
     */
    @PutMapping(value = "/updateStatus/{id}")
    @Operation(summary = "修改数据", description = "product::updateStatus")
    @PreAuthorize("@hasPermission.check('product::updateStatus')")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam(value = "status") Boolean status) {
        if (this.productService.updateStatus(id, status)) {
            return ResponseEntity.ok("修改成功");
        }
        throw new BaseRequestException("修改失败");
    }

    /**
     * 修改商品状态
     *
     * @param id    用户id
     * @param audit 状态
     * @return String
     */
    @PutMapping(value = "/updateAudit/{id}")
    @Operation(summary = "修改商品状态", description = "product::updateAudit")
    @PreAuthorize("@hasPermission.check('product::updateAudit')")
    public ResponseEntity<String> updateAudit(@PathVariable Long id, @RequestParam(value = "audit") Boolean audit) {
        if (this.productService.updateAudit(id, audit)) {
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
    @Operation(summary = "删除数据", description = "product::remove")
    @PreAuthorize("@hasPermission.check('product::remove')")
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        if (this.productService.removeByIds(ids)) {
            return ResponseEntity.ok("删除成功");
        }
        throw new BaseRequestException("删除失败");
    }
}

