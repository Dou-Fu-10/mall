package org.example.modules.order.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.modules.order.entity.dto.OrderReturnApplyDto;
import org.example.modules.order.service.OrderReturnApplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
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
     * 新增数据
     *
     * @param orderReturnApplyDto 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody OrderReturnApplyDto orderReturnApplyDto) {
        if (this.orderReturnApplyService.save(orderReturnApplyDto)) {
            return ResponseEntity.ok("退货申请提交成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("退货申请提交失败");
    }
}

