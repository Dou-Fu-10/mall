package org.example.modules.admin.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.admin.order.entity.OrderReturnReasonEntity;
import org.example.modules.admin.order.entity.dto.OrderReturnReasonDto;
import org.example.modules.admin.order.service.OrderReturnReasonService;
import org.example.security.annotaion.rest.AnonymousDeleteMapping;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.example.security.annotaion.rest.AnonymousPutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
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
     * @param page              分页对象
     * @param orderReturnReason 查询实体
     * @return 所有数据
     */
    @AnonymousGetMapping
    public ResponseEntity<Object> selectAll(Page<OrderReturnReasonEntity> page, OrderReturnReasonEntity orderReturnReason) {
        return ResponseEntity.ok(this.orderReturnReasonService.page(page, new QueryWrapper<>(orderReturnReason)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @AnonymousGetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.orderReturnReasonService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param orderReturnReason 实体对象
     * @return 新增结果
     */
    @AnonymousPostMapping
    public ResponseEntity<Object> insert(@RequestBody OrderReturnReasonDto orderReturnReason) {
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
    @AnonymousPutMapping
    public ResponseEntity<Object> update(@RequestBody OrderReturnReasonDto orderReturnReason) {
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
    @Operation(summary = "修改帐号状态")
    @AnonymousPutMapping(value = "/updateStatus/{id}")
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
    @AnonymousDeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        return ResponseEntity.ok(this.orderReturnReasonService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() >= 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败");
    }
}

