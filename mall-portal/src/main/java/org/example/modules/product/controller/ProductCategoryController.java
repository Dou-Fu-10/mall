package org.example.modules.product.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.modules.product.entity.ProductCategoryEntity;
import org.example.modules.product.serveice.ProductCategoryService;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dou-Fu-10 2023-07-13 22:08:15
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 22:08:15
 * @Description 产品分类(ProductCategory)表控制层
 */
@RestController
@RequestMapping("/productCategory")
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
     * @return 所有数据
     */
    @AnonymousGetMapping
    public ResponseEntity<Object> select() {
        return ResponseEntity.ok(this.productCategoryService.selectProductCategory());
    }
}

