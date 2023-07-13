package org.example.modules.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.example.modules.product.entity.ProductLadderEntity;
import org.example.modules.product.service.ProductLadderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:56
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:56
 * @Description 产品阶梯价格表(只针对同商品)(ProductLadder)表控制层
 */
@RestController
@RequestMapping("/api/productLadder")
public class ProductLadderController {
    /**
     * 服务对象
     */
    @Resource
    private ProductLadderService productLadderService;

    /**
     * 分页查询所有数据
     *
     * @param page          分页对象
     * @param productLadder 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<ProductLadderEntity> page, ProductLadderEntity productLadder) {
        return new ResponseEntity<>(this.productLadderService.page(page, new QueryWrapper<>(productLadder)), HttpStatus.OK);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return new ResponseEntity<>(this.productLadderService.getById(id), HttpStatus.OK);
    }

    /**
     * 新增数据
     *
     * @param productLadder 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody ProductLadderEntity productLadder) {
        return new ResponseEntity<>(this.productLadderService.save(productLadder), HttpStatus.OK);
    }

    /**
     * 修改数据
     *
     * @param productLadder 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody ProductLadderEntity productLadder) {
        return new ResponseEntity<>(this.productLadderService.updateById(productLadder), HttpStatus.OK);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return new ResponseEntity<>(this.productLadderService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败", HttpStatus.OK);
    }
}
