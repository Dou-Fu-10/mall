package org.example.modules.order.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.common.core.exception.BaseRequestException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.modules.order.entity.dto.OrderDto;
import org.example.modules.order.entity.OrderEntity;
import org.example.modules.order.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-08-03 14:28:08
 *
 * @author Dou-Fu-10
 * @date 2023-08-03 14:28:08
 * @Description 订单表(Order)表控制层
 */
@RestController
@RequestMapping("/api/order")
@Tag(name = "OrderController", description = "")
public class OrderController {
    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param orderDto 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<OrderEntity> page, OrderDto orderDto) {
        return ResponseEntity.ok(this.orderService.page(page, orderDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.orderService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param orderDto 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody OrderDto orderDto) {
        if (this.orderService.save(orderDto)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param orderDto 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody OrderDto orderDto) {
        if (this.orderService.updateById(orderDto)) {
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
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<Long> collect = idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() >= 1).limit(10).collect(Collectors.toSet());
        return ResponseEntity.ok(this.orderService.removeByIds(collect) ? "删除成功" : "删除失败");
    }
}

