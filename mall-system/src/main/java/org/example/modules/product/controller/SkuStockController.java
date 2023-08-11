package org.example.modules.product.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.base.ValidationDto;
import org.example.modules.product.entity.dto.SkuStockDto;
import org.example.modules.product.service.SkuStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * sku的库存 SkuStockController
 * Created by Dou-Fu-10 2023-07-15 11:35:49
 *
 * @author Dou-Fu-10
 * @date 2023-07-15 11:35:49
 * @Description sku的库存(SkuStock)表控制层
 */
@RestController
@RequestMapping("/api/skuStock")
@Tag(name = "SkuStockController", description = "")
public class SkuStockController {
    /**
     * 服务对象
     */
    @Resource
    private SkuStockService skuStockService;

    /**
     * 修改数据
     *
     * @param skuStock 实体对象
     * @return 修改结果
     */
    @PutMapping("/updateBatch")
    @Operation(summary = "修改数据", description = "skuStock::update")
    @PreAuthorize("@hasPermission.check('skuStock::update')")
    public ResponseEntity<Object> update(@RequestBody @Validated(ValidationDto.Update.class) Set<SkuStockDto> skuStock) {
        if (this.skuStockService.updateBatchById(skuStock)) {
            return ResponseEntity.ok("修改成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("修改失败");
    }
}

