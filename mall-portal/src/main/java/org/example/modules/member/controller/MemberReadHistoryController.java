package org.example.modules.member.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.member.entity.MemberReadHistoryEntity;
import org.example.modules.member.entity.dto.MemberReadHistoryDto;
import org.example.modules.member.service.MemberReadHistoryService;
import org.example.security.utils.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-08-05 13:27:09
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 13:27:09
 * @Description 会员阅读历史记录(MemberReadHistory)表控制层
 */
@RestController
@RequestMapping("/app/memberReadHistory")
@Tag(name = "MemberReadHistoryController", description = "")
public class MemberReadHistoryController {
    /**
     * 服务对象
     */
    @Resource
    private MemberReadHistoryService memberReadHistoryService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有数据")
    @GetMapping
    public ResponseEntity<Object> select(Page<MemberReadHistoryEntity> page) {
        return ResponseEntity.ok(this.memberReadHistoryService.page(page));
    }

    /**
     * 新增数据
     *
     * @param memberReadHistoryDto 实体对象
     * @return 新增结果
     */
    @Operation(summary = "新增数据")
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody MemberReadHistoryDto memberReadHistoryDto) {
        if (this.memberReadHistoryService.save(memberReadHistoryDto)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("添加失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @Operation(summary = "删除数据")
    @DeleteMapping
    public ResponseEntity<String> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        LambdaQueryWrapper<MemberReadHistoryEntity> memberReadHistoryEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        memberReadHistoryEntityLambdaQueryWrapper.eq(MemberReadHistoryEntity::getMemberId, SecurityUtils.getCurrentUserId());
        memberReadHistoryEntityLambdaQueryWrapper.in(MemberReadHistoryEntity::getId, ids);

        if (this.memberReadHistoryService.remove(memberReadHistoryEntityLambdaQueryWrapper)) {
            return ResponseEntity.ok("删除成功");
        }
        throw new BaseRequestException("删除失败");
    }
}

