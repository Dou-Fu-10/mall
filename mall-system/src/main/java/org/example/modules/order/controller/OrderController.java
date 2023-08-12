package org.example.modules.order.controller;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.base.ValidationDto;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.order.entity.OrderEntity;
import org.example.modules.order.entity.dto.OrderDeliveryDto;
import org.example.modules.order.entity.dto.OrderDto;
import org.example.modules.order.entity.dto.ReceiverInfoDto;
import org.example.modules.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * 订单 OrderController
 * Created by Dou-Fu-10 2023-07-14 14:34:28
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:28
 * @Description 订单表(Order)表控制层
 */
@RestController
@RequestMapping("/api/order")
@Tag(name = "OrderController", description = "订单表(Order)表控制层")
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
    @Operation(summary = "分页查询所有数据", description = "order::select")
    @GetMapping
    @PreAuthorize("@hasPermission.check('order::select')")
    public ResponseEntity<Object> select(Page<OrderEntity> page, @Validated(ValidationDto.SelectPage.class) OrderDto orderDto) {
        return ResponseEntity.ok(this.orderService.page(page, orderDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "通过主键查询单条数据", description = "order::selectOne")
    @GetMapping("{id}")
    @PreAuthorize("@hasPermission.check('order::selectOne')")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.orderService.getOrderById(id));
    }

    /**
     * 订单发货
     *
     * @param orderDeliveryDto 订单发货
     * @return String
     */
    @Operation(summary = "修改订单状态", description = "order::delivery")
    @PutMapping(value = "/delivery")
    @PreAuthorize("@hasPermission.check('order::delivery')")
    public ResponseEntity<String> delivery(@RequestBody OrderDeliveryDto orderDeliveryDto) {
        if (this.orderService.delivery(orderDeliveryDto)) {
            return ResponseEntity.ok("修改成功");
        }
        throw new BaseRequestException("修改失败");
    }

    /**
     * 按传入的月份查找月份已完成的订单
     *
     * @param page     分页
     * @param orderDto 实体类
     * @return
     */
    @Operation(summary = "按传入的月份查找月份已完成的订单", description = "order::findCompletedOrdersByMonth")
    @GetMapping("/completedOrdersMonth")
    @PreAuthorize("@hasPermission.check('order::findCompletedOrdersByMonth')")
    public ResponseEntity<Object> findCompletedOrdersByMonth(Page<OrderEntity> page, @Validated(ValidationDto.SelectList.class) OrderDto orderDto) {
        return ResponseEntity.ok(this.orderService.findCompletedOrdersByMonth(page, orderDto, new DateTime()));
    }

    /**
     * 取消订单
     *
     * @param id   订单id
     * @param note 备注
     * @return /
     */
    @Operation(summary = "取消订单")
    @PostMapping(value = "/update/close/{id}")
    public ResponseEntity<Object> close(@PathVariable("id") Long id, @RequestParam String note) {
        if (orderService.close(id, note)) {
            return ResponseEntity.ok("取消成功");
        }
        throw new BaseRequestException("取消失败");
    }

    /**
     * 修改收货人信息
     *
     * @param receiverInfoDto 收货人
     * @return /
     */
    @Operation(summary = "修改收货人信息")
    @PostMapping(value = "/update/receiverInfo")
    public ResponseEntity<Object> updateReceiverInfo(@RequestBody ReceiverInfoDto receiverInfoDto) {
        if (orderService.updateReceiverInfo(receiverInfoDto)) {
            return ResponseEntity.ok("修改成功");
        }
        throw new BaseRequestException("修改失败");
    }

    /**
     * 订单备注
     * @param id 订单id
     * @param note 订单备注
     * @return /
     */
    @Operation(summary = "备注订单")
    @PostMapping(value = "/update/note")
    public ResponseEntity<Object> updateNote(@RequestParam("id") Long id, @RequestParam("note") String note) {
        if (orderService.updateNote(id, note)) {
            return ResponseEntity.ok("备注订单成功");
        }
        throw new BaseRequestException("备注订单失败");
    }
}

