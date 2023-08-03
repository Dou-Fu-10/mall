package org.example.modules.product.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.product.entity.ProductAttributeEntity;
import org.example.modules.product.entity.dto.ProductAttributeDto;
import org.example.modules.product.service.ProductAttributeService;
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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-08-01 22:28:58
 *
 * @author Dou-Fu-10
 * @date 2023-08-01 22:28:58
 * @Description 商品属性参数表(ProductAttribute)表控制层
 */
@RestController
@RequestMapping("/productAttribute")
@Tag(name = "ProductAttributeController", description = "")
public class ProductAttributeController {
    /**
     * 服务对象
     */
    @Resource
    private ProductAttributeService productAttributeService;

    /**
     * 分页查询所有数据
     *
     * @param page                分页对象
     * @param productAttributeDto 查询实体
     * @return 所有数据
     */
    @AnonymousGetMapping
    public ResponseEntity<Object> selectAll(Page<ProductAttributeEntity> page, ProductAttributeDto productAttributeDto) {
        return ResponseEntity.ok(this.productAttributeService.page(page, productAttributeDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @AnonymousGetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.productAttributeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param productAttributeDto 实体对象
     * @return 新增结果
     */
    @AnonymousPostMapping
    public ResponseEntity<Object> insert(@RequestBody ProductAttributeDto productAttributeDto) {
        if (this.productAttributeService.save(productAttributeDto)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param productAttributeDto 实体对象
     * @return 修改结果
     */
    @AnonymousPutMapping
    public ResponseEntity<Object> update(@RequestBody ProductAttributeDto productAttributeDto) {
        if (this.productAttributeService.updateById(productAttributeDto)) {
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
    @AnonymousDeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<Long> collect = idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() >= 1).limit(10).collect(Collectors.toSet());
        return ResponseEntity.ok(this.productAttributeService.removeByIds(collect) ? "删除成功" : "删除失败");
    }
}

