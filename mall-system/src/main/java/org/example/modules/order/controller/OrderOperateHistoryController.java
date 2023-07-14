package org.example.modules.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.order.entity.OrderOperateHistoryEntity;
import org.example.modules.order.entity.dto.OrderOperateHistoryDto;
import org.example.modules.order.service.OrderOperateHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by PanShiFu 2023-07-14 14:34:29
 *
 * @author PanShiFu
 * @date 2023-07-14 14:34:29
 * @Description 订单操作历史记录(OrderOperateHistory)表控制层
 */
@RestController
@RequestMapping("/api/orderOperateHistory")
@Tag(name = "OrderOperateHistoryController", description = "订单操作历史记录(OrderOperateHistory)表控制层")
public class OrderOperateHistoryController {
    /**
     * 服务对象
     */
    @Resource
    private OrderOperateHistoryService orderOperateHistoryService;

    /**
     * 分页查询所有数据
     *
     * @param page                分页对象
     * @param orderOperateHistory 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<OrderOperateHistoryEntity> page, OrderOperateHistoryEntity orderOperateHistory) {
        return ResponseEntity.ok(this.orderOperateHistoryService.page(page, new QueryWrapper<>(orderOperateHistory)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.orderOperateHistoryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param orderOperateHistory 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody OrderOperateHistoryDto orderOperateHistory) {
        if (this.orderOperateHistoryService.save(orderOperateHistory)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param orderOperateHistory 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody OrderOperateHistoryDto orderOperateHistory) {
        if (this.orderOperateHistoryService.updateById(orderOperateHistory)) {
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
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return ResponseEntity.ok(this.orderOperateHistoryService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败");
    }
}

