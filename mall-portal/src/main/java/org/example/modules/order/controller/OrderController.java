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
import org.example.security.utils.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;

/**
 * 订单 OrderController
 * Created by Dou-Fu-10 2023-08-03 14:28:08
 *
 * @author Dou-Fu-10
 * @date 2023-08-03 14:28:08
 * @Description 订单表(Order)表控制层
 */
@RestController
@RequestMapping("/app/order")
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
    @Operation(summary = "分页查询所有数据")
    @GetMapping
    public ResponseEntity<Object> select(Page<OrderEntity> page, OrderDto orderDto) {
        return ResponseEntity.ok(this.orderService.page(page, orderDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "通过主键查询单条数据")
    @GetMapping("detail/{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.orderService.getByOrderIdAndMemberId(id, SecurityUtils.getCurrentUserId()));
    }


    /**
     * 根据购物车信息生成确认单
     *
     * @param cartIds 购物车id列表
     * @return 生成结果
     */
    @Operation(summary = "根据购物车信息生成确认单", description = "根据购物车信息生成确认单")
    @PostMapping(value = "/generateConfirmationOrder")
    public ResponseEntity<ConfirmOrderVo> generateConfirmOrder(@RequestBody Set<Long> cartIds) {
        return ResponseEntity.ok(orderService.generateConfirmOrder(cartIds));
    }

    /**
     * 生成订单
     *
     * @param generateOrderDto 订单信息
     * @return 生成确认单
     */
    @Operation(summary = "生成订单", description = "生成订单")
    @PostMapping(value = "/generateOrder")
    public ResponseEntity<Boolean> generateOrder(@RequestBody GenerateOrderDto generateOrderDto) {
        return ResponseEntity.ok(this.orderService.generateOrder(generateOrderDto));
    }


    /**
     * 确认收货
     *
     * @param orderId 订单id
     * @return /
     */
    @Operation(summary = "用户确认收货")
    @PutMapping(value = "/confirmReceiveOrder")
    public ResponseEntity<String> confirmReceiveOrder(@RequestParam("orderId") Long orderId) {
        if (this.orderService.confirmReceiveOrder(orderId)) {
            return ResponseEntity.ok("确认成功");
        }
        return ResponseEntity.ok("确认失败");
    }

    /**
     * 用户删除订单
     *
     * @param orderId 订单id
     * @return /
     */
    @Operation(summary = "用户删除订单")
    @DeleteMapping(value = "/deleteOrder")
    public ResponseEntity<String> deleteOrder(@RequestParam("orderId") Long orderId) {
        if (this.orderService.deleteOrder(orderId)) {
            return ResponseEntity.ok("确认成功");
        }
        return ResponseEntity.ok("确认失败");
    }

    /**
     * 用户取消订单
     *
     * @param orderId 订单id
     * @return /
     */
    @Operation(summary = "用户取消订单")
    @PutMapping(value = "/cancelUserOrder")
    public ResponseEntity<String> cancelUserOrder(@RequestParam("orderId") Long orderId) {
        if (this.orderService.cancelOrder(orderId)) {
            return ResponseEntity.ok("取消订单成功");
        }
        return ResponseEntity.ok("取消订单失败");
    }


    @Operation(summary = "用户支付成功的回调")
    @PostMapping(value = "/paySuccess")
    @ResponseBody
    public ResponseEntity<String> paySuccess(@RequestParam Long orderId, @RequestParam Integer payType) {
        if (this.orderService.paySuccess(orderId, payType)) {
            return ResponseEntity.ok("支付成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("支付失败");
    }
}

