package org.example.modules.order.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.modules.order.entity.OrderEntity;
import org.example.modules.order.entity.dto.GenerateOrderDto;
import org.example.modules.order.entity.dto.OrderDto;
import org.example.modules.order.entity.vo.ConfirmOrderVo;
import org.example.modules.order.service.OrderService;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Dou-Fu-10 2023-08-03 14:28:08
 *
 * @author Dou-Fu-10
 * @date 2023-08-03 14:28:08
 * @Description 订单表(Order)表控制层
 */
@RestController
@RequestMapping("/order")
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
    @GetMapping("detail/{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.orderService.getById(id));
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
     * 根据购物车信息生成确认单
     *
     * @param cartIds 购物车id列表
     * @return 生成结果
     */
    @Operation(summary = "根据购物车信息生成确认单", description = "根据购物车信息生成确认单")
    @AnonymousGetMapping(value = "/generateConfirmationOrder")
    public ResponseEntity<ConfirmOrderVo> generateConfirmOrder(@RequestBody List<Long> cartIds) {
        return ResponseEntity.ok(orderService.generateConfirmOrder(cartIds));
    }

    /**
     * 生成订单
     *
     * @param generateOrderDto 订单信息
     * @return 生成确认单
     */
    @Operation(summary = "根据购物车信息生成确认单", description = "根据购物车信息生成确认单")
    @AnonymousPostMapping(value = "/generateOrder")
    public ResponseEntity<Map<String, Object>> generateOrder(@RequestBody GenerateOrderDto generateOrderDto) {
        return ResponseEntity.ok(this.orderService.generateOrder(generateOrderDto));
    }
}

