package org.example.modules.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.modules.product.entity.dto.SkuStockDto;
import org.example.modules.product.entity.SkuStockEntity;
import org.example.modules.product.service.SkuStockService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
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
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param skuStock 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<SkuStockEntity> page, SkuStockEntity skuStock) {
        return ResponseEntity.ok(this.skuStockService.page(page, new QueryWrapper<>(skuStock)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.skuStockService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param skuStock 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody SkuStockDto skuStock) {
        if (this.skuStockService.save(skuStock)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param skuStock 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody SkuStockDto skuStock) {
        if (this.skuStockService.updateById(skuStock)) {
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
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return ResponseEntity.ok(this.skuStockService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败");
    }
}

