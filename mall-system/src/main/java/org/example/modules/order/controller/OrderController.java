package org.example.modules.order.controller;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.base.ValidationDto;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.order.entity.OrderEntity;
import org.example.modules.order.entity.dto.OrderDto;
import org.example.modules.order.service.OrderService;
import org.example.security.annotaion.rest.AnonymousDeleteMapping;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.example.security.annotaion.rest.AnonymousPutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
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
    public ResponseEntity<Object> select(Page<OrderEntity> page,@Validated(ValidationDto.SelectPage.class)  OrderDto orderDto) {
        // TODO 对orderDto 进行数据校验
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
     * 修改订单状态
     *
     * @param id     用户id
     * @param status 状态
     * @return String
     */
    @Operation(summary = "修改订单状态", description = "order::updateStatus")
    @PutMapping(value = "/updateStatus/{id}")
    @PreAuthorize("@hasPermission.check('order::updateStatus')")
    public ResponseEntity<String> updateStatus(@PathVariable Long id,
                                               @RequestParam(value = "status") Integer status,
                                               @RequestParam(value = "deliveryCompany") String deliveryCompany,
                                               @RequestParam(value = "deliverySn") String deliverySn) {
        if (this.orderService.updateStatus(id, status, deliveryCompany, deliverySn)) {
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
    public ResponseEntity<Object> findCompletedOrdersByMonth(Page<OrderEntity> page,@Validated(ValidationDto.SelectList.class)  OrderDto orderDto) {
        return ResponseEntity.ok(this.orderService.findCompletedOrdersByMonth(page, orderDto, new DateTime()));
    }

    /**
     * 新增数据
     *
     * @param order 实体对象
     * @return 新增结果
     */
//    @Operation(summary = "新增数据", description = "order::insert")
//    @AnonymousPostMapping
//    @PreAuthorize("@hasPermission.check('order::insert')")
//    public ResponseEntity<Object> insert(@RequestBody OrderDto order) {
//        if (this.orderService.save(order)) {
//            return ResponseEntity.ok("添加成功");
//        }
//        // 修改成自定义的 错误类型
//        throw new BaseRequestException("添加失败");
//    }

    /**
     * 修改数据
     *
     * @param order 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改数据", description = "order::update")
    @PutMapping
    @PreAuthorize("@hasPermission.check('order::update')")
    public ResponseEntity<Object> update(@RequestBody @Validated(ValidationDto.Update.class) OrderDto order) {
        if (this.orderService.updateById(order)) {
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
//    @Operation(summary = "删除数据", description = "order::remove")
//    @DeleteMapping
//    @PreAuthorize("@hasPermission.check('order::remove')")
//    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
//        if (CollectionUtils.isEmpty(idList)) {
//            throw new BaseRequestException("请正确的填写id");
//        }
//        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
//        return ResponseEntity.ok(this.orderService.removeByIds(ids) ? "删除成功" : "删除失败");
//    }
}

