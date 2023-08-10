package org.example.modules.order.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.example.modules.order.entity.OrderReturnApplyEntity;
import org.example.modules.order.entity.dto.OrderReturnApplyDto;
import org.example.modules.order.service.OrderReturnApplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 订单退货申请 OrderReturnApplyController
 * Created by Dou-Fu-10 2023-08-05 17:04:10
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 17:04:10
 * @Description 订单退货申请(OrderReturnApply)表控制层
 */
@RestController
@RequestMapping("/app/orderReturnApply")
@Tag(name = "OrderReturnApplyController", description = "")
public class OrderReturnApplyController {
    /**
     * 服务对象
     */
    @Resource
    private OrderReturnApplyService orderReturnApplyService;

    /**
     * 分页查询所有数据
     *
     * @param page                分页对象
     * @param orderReturnApplyDto 查询实体
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有数据")
    @GetMapping
    public ResponseEntity<Object> select(Page<OrderReturnApplyEntity> page, OrderReturnApplyDto orderReturnApplyDto) {
        return ResponseEntity.ok(this.orderReturnApplyService.page(page, orderReturnApplyDto));
    }

    /**
     * 新增数据
     *
     * @param orderReturnApplyDto 实体对象
     * @return 新增结果
     */
    @Operation(summary = "新增数据")
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody OrderReturnApplyDto orderReturnApplyDto, HttpServletRequest request) {
        if (this.orderReturnApplyService.save(orderReturnApplyDto)) {
            return ResponseEntity.ok("退货申请提交成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("退货申请提交失败");
    }
}

