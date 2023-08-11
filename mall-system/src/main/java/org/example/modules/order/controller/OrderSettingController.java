package org.example.modules.order.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.base.ValidationDto;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.order.entity.dto.OrderSettingDto;
import org.example.modules.order.service.OrderSettingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * 订单设置表 OrderSettingController
 * Created by Dou-Fu-10 2023-07-14 14:34:30
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:30
 * @Description 订单设置表(OrderSetting)表控制层
 */
@RestController
@RequestMapping("/api/orderSetting")
@Tag(name = "OrderSettingController", description = "订单设置表(OrderSetting)表控制层")
public class OrderSettingController {
    /**
     * 服务对象
     */
    @Resource
    private OrderSettingService orderSettingService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "通过主键查询单条数据", description = "orderSetting::selectOne")
    @GetMapping("{id}")
    @PreAuthorize("@hasPermission.check('orderSetting::remove')")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.orderSettingService.getById(id));
    }

    /**
     * 修改数据
     *
     * @param orderSetting 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改数据", description = "orderSetting::update")
    @PutMapping
    @PreAuthorize("@hasPermission.check('orderSetting::update')")
    public ResponseEntity<Object> update(@RequestBody @Validated(ValidationDto.Update.class) OrderSettingDto orderSetting) {
        if (this.orderSettingService.updateById(orderSetting)) {
            return ResponseEntity.ok("修改成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("修改失败");
    }
}

