package org.example.modules.order.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.common.core.exception.BaseRequestException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.modules.order.entity.dto.OrderReturnApplyDto;
import org.example.modules.order.entity.OrderReturnApplyEntity;
import org.example.modules.order.service.OrderReturnApplyService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

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
            return ResponseEntity.ok("退货成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("退货失败");
    }
}

