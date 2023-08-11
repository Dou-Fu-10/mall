package org.example.modules.finance.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.modules.finance.entity.dto.PrizePoolDto;
import org.example.modules.finance.service.PrizePoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 奖金池 PrizePoolController
 * Created by Dou-Fu-10 2023-07-29 15:47:03
 *
 * @author Dou-Fu-10
 * @date 2023-07-29 15:47:03
 * @Description 奖金池(PrizePool)表控制层
 */
@RestController
@RequestMapping("/api/prizePool")
@Tag(name = "PrizePoolController", description = "")
public class PrizePoolController {
    /**
     * 服务对象
     */
    @Resource
    private PrizePoolService prizePoolService;

    /**
     * 查找奖金池信息
     *
     * @return 查找奖金池信息
     */
    @Operation(summary = "查找奖金池信息", description = "prizePool::select")
    @GetMapping
    @PreAuthorize("@hasPermission.check('prizePool::select')")
    public ResponseEntity<Object> select() {
        return ResponseEntity.ok(this.prizePoolService.select());
    }

    /**
     * 平台收益明细
     *
     * @return 平台收益明细
     */
    @Operation(summary = "平台收益明细", description = "prizePool::details")
    @GetMapping("/details")
    @PreAuthorize("@hasPermission.check('prizePool::details')")
    public ResponseEntity<Object> details() {
        return ResponseEntity.ok(this.prizePoolService.details());
    }

    /**
     * 修改数据
     *
     * @param prizePool 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改数据", description = "prizePool::update")
    @PutMapping
    @PreAuthorize("@hasPermission.check('prizePool::update')")
    public ResponseEntity<Object> update(@RequestBody @Validated PrizePoolDto prizePool) {
        if (this.prizePoolService.updateById(prizePool)) {
            return ResponseEntity.ok("修改成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("修改失败");
    }

    /**
     * 获得会员费用
     *
     * @return /
     */
    @Operation(summary = "获得会员费用", description = "prizePool::memberFees")
    @GetMapping("/memberFees")
    @PreAuthorize("@hasPermission.check('prizePool::memberFees')")
    public ResponseEntity<Object> getMemberFees() {
        BigDecimal memberFees = this.prizePoolService.getMemberFees();
        if (Objects.nonNull(memberFees)) {
            return ResponseEntity.ok(memberFees);
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("查询失败");
    }
}

