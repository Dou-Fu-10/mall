package org.example.modules.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.modules.product.entity.ProductVertifyRecordEntity;
import org.example.modules.product.service.ProductVertifyRecordService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by PanShiFu 2023-07-13 15:35:57
 *
 * @author PanShiFu
 * @date 2023-07-13 15:35:57
 * @Description 商品审核记录(ProductVertifyRecord)表控制层
 */
@RestController
@RequestMapping("/api/productVertifyRecord")
public class ProductVertifyRecordController {
    /**
     * 服务对象
     */
    @Resource
    private ProductVertifyRecordService productVertifyRecordService;

    /**
     * 分页查询所有数据
     *
     * @param page                 分页对象
     * @param productVertifyRecord 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<ProductVertifyRecordEntity> page, ProductVertifyRecordEntity productVertifyRecord) {
        return new ResponseEntity<>(this.productVertifyRecordService.page(page, new QueryWrapper<>(productVertifyRecord)), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.productVertifyRecordService.getById(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param productVertifyRecord 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody ProductVertifyRecordEntity productVertifyRecord) {
        return new ResponseEntity<>(this.productVertifyRecordService.save(productVertifyRecord), HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param productVertifyRecord 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody ProductVertifyRecordEntity productVertifyRecord) {
        return new ResponseEntity<>(this.productVertifyRecordService.updateById(productVertifyRecord), HttpStatus.OK);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return new ResponseEntity<>(this.productVertifyRecordService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
    }
}

