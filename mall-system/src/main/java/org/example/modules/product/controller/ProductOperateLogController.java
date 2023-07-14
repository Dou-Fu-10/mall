package org.example.modules.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.core.exception.BaseRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.modules.product.entity.dto.ProductOperateLogDto;
import org.example.modules.product.entity.ProductOperateLogEntity;
import org.example.modules.product.service.ProductOperateLogService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by PanShiFu 2023-07-14 13:54:18
 *
 * @author PanShiFu
 * @date 2023-07-14 13:54:18
 * @Description 商品价格变动记录(ProductOperateLog)表控制层
 */
@RestController
@RequestMapping("/api/productOperateLog")
@Tag(name = "ProductOperateLogController", description = "商品价格变动记录(ProductOperateLog)表控制层")
public class ProductOperateLogController {
    /**
     * 服务对象
     */
    @Resource
    private ProductOperateLogService productOperateLogService;

    /**
     * 分页查询所有数据
     *
     * @param page              分页对象
     * @param productOperateLog 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<ProductOperateLogEntity> page, ProductOperateLogEntity productOperateLog) {
        return ResponseEntity.ok(this.productOperateLogService.page(page, new QueryWrapper<>(productOperateLog)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.productOperateLogService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param productOperateLog 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody ProductOperateLogDto productOperateLog) {
        if (this.productOperateLogService.save(productOperateLog)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param productOperateLog 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody ProductOperateLogDto productOperateLog) {
        if (this.productOperateLogService.updateById(productOperateLog)) {
            return ResponseEntity.ok("修改成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("修改失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return ResponseEntity.ok(this.productOperateLogService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败");
    }
}
