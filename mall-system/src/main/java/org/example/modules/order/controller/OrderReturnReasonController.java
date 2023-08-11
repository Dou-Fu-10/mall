package org.example.modules.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.base.ValidationDto;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.order.entity.OrderReturnReasonEntity;
import org.example.modules.order.entity.dto.OrderReturnReasonDto;
import org.example.modules.order.entity.vo.OrderReturnReasonVo;
import org.example.modules.order.service.OrderReturnReasonService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 退货原因表 OrderReturnReasonController
 * Created by Dou-Fu-10 2023-07-14 14:34:30
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:30
 * @Description 退货原因表(OrderReturnReason)表控制层
 */
@RestController
@RequestMapping("/api/orderReturnReason")
@Tag(name = "OrderReturnReasonController", description = "退货原因表(OrderReturnReason)表控制层")
public class OrderReturnReasonController {
    /**
     * 服务对象
     */
    @Resource
    private OrderReturnReasonService orderReturnReasonService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有数据", description = "orderReturnReason::select")
    @GetMapping
    @PreAuthorize("@hasPermission.check('orderReturnReason::update')")
    public ResponseEntity<Object> select(Page<OrderReturnReasonEntity> page) {
        Page<OrderReturnReasonEntity> orderReturnReasonEntityPage = this.orderReturnReasonService.page(page, new QueryWrapper<>());
        IPage<OrderReturnReasonVo> orderReturnReasonVoIPage = orderReturnReasonEntityPage.convert(orderReturnReasonEntity -> BeanCopy.convert(orderReturnReasonEntity, OrderReturnReasonVo.class));
        return ResponseEntity.ok(orderReturnReasonVoIPage);
    }

    /**
     * 新增数据
     *
     * @param orderReturnReason 实体对象
     * @return 新增结果
     */
    @Operation(summary = "新增数据", description = "orderReturnReason::update")
    @PostMapping
    @PreAuthorize("@hasPermission.check('orderReturnReason::insert')")
    public ResponseEntity<Object> insert(@RequestBody @Validated(ValidationDto.Insert.class) OrderReturnReasonDto orderReturnReason) {
        if (this.orderReturnReasonService.save(orderReturnReason)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param orderReturnReason 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改数据", description = "orderReturnReason::update")
    @PutMapping
    @PreAuthorize("@hasPermission.check('orderReturnReason::update')")
    public ResponseEntity<Object> update(@RequestBody @Validated(ValidationDto.Update.class) OrderReturnReasonDto orderReturnReason) {
        if (this.orderReturnReasonService.updateById(orderReturnReason)) {
            return ResponseEntity.ok("修改成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("修改失败");
    }

    /**
     * 修改帐号状态
     *
     * @param id     用户id
     * @param status 状态
     * @return String
     */
    @Operation(summary = "修改帐号状态", description = "orderReturnReason::update")
    @PutMapping(value = "/updateStatus/{id}")
    @PreAuthorize("@hasPermission.check('orderReturnReason::updateStatus')")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestParam(value = "status") Boolean status) {
        if (this.orderReturnReasonService.updateStatus(id, status)) {
            return ResponseEntity.ok("修改成功");
        }
        throw new BaseRequestException("修改失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @Operation(summary = "删除数据", description = "orderReturnReason::update")
    @DeleteMapping
    @PreAuthorize("@hasPermission.check('orderReturnReason::remove')")
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        if (this.orderReturnReasonService.removeByIds(ids)) {
            return ResponseEntity.ok("删除成功");
        }
        throw new BaseRequestException("删除失败");
    }
}

