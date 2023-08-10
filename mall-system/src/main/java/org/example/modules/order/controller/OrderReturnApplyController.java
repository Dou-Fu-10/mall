package org.example.modules.order.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.base.ValidationDto;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.order.entity.OrderReturnApplyEntity;
import org.example.modules.order.entity.dto.OrderReturnApplyDto;
import org.example.modules.order.service.OrderReturnApplyService;
import org.example.modules.tools.service.CompanyAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:30
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:30
 * @Description 订单退货申请(OrderReturnApply)表控制层
 */
@RestController
@RequestMapping("/api/orderReturnApply")
@Tag(name = "OrderReturnApplyController", description = "订单退货申请(OrderReturnApply)表控制层")
public class OrderReturnApplyController {
    /**
     * 服务对象
     */
    @Resource
    private OrderReturnApplyService orderReturnApplyService;


    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param orderReturnApply 查询实体
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有数据", description = "orderReturnApply::select")
    @GetMapping
    @PreAuthorize("@hasPermission.check('orderReturnApply::select')")
    public ResponseEntity<Object> select(Page<OrderReturnApplyEntity> page, @Validated(ValidationDto.SelectPage.class) OrderReturnApplyDto orderReturnApply) {
        return ResponseEntity.ok(this.orderReturnApplyService.page(page, orderReturnApply));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "通过主键查询单条数据", description = "orderReturnApply::selectOne")
    @GetMapping("{id}")
    @PreAuthorize("@hasPermission.check('orderReturnApply::selectOne')")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(orderReturnApplyService.getByOrderReturnApplyId(id));
    }

    /**
     * 修改数据
     *
     * @param orderReturnApply 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改数据", description = "orderReturnApply::update")
    @PutMapping
    @PreAuthorize("@hasPermission.check('orderReturnApply::update')")
    public ResponseEntity<Object> update(@RequestBody @Validated(ValidationDto.Update.class) OrderReturnApplyDto orderReturnApply) {
        if (this.orderReturnApplyService.updateById(orderReturnApply)) {
            return ResponseEntity.ok("修改成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("修改失败");
    }
}

