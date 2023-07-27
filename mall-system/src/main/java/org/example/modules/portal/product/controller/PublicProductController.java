package org.example.modules.portal.product.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.modules.admin.product.entity.ProductEntity;
import org.example.modules.admin.product.entity.dto.ProductDto;
import org.example.modules.portal.product.serveice.PublicProductService;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * Created by Dou-Fu-10 2023-07-14 13:05:47
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:05:47
 * @Description 商品信息(Product)表控制层
 */
@RestController
@RequestMapping("/product")
@Tag(name = "PublicProductController", description = "商品信息(Product)表控制层")
public class PublicProductController {
    /**
     * 服务对象
     */
    @Resource
    private PublicProductService publicProductService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param product 查询实体
     * @return 所有数据
     */
    @AnonymousGetMapping
    public ResponseEntity<Object> selectAll(Page<ProductEntity> page, ProductDto product) {
        return ResponseEntity.ok("");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @AnonymousGetMapping("detail/{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(publicProductService.detail(id));
    }
}

