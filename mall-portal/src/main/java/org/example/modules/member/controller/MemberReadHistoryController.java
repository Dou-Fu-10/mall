package org.example.modules.member.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.security.utils.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.common.core.exception.BaseRequestException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.modules.member.entity.dto.MemberReadHistoryDto;
import org.example.modules.member.entity.MemberReadHistoryEntity;
import org.example.modules.member.service.MemberReadHistoryService;
import jakarta.annotation.Resource;
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
     * @param page                 分页对象
     * @return 所有数据
     */
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<MemberReadHistoryEntity> page ) {
        return ResponseEntity.ok(this.memberReadHistoryService.page(page));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.memberReadHistoryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param memberReadHistoryDto 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody MemberReadHistoryDto memberReadHistoryDto) {
        if (this.memberReadHistoryService.save(memberReadHistoryDto)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param memberReadHistoryDto 实体对象
     * @return 修改结果
     */
//    @PutMapping
    public ResponseEntity<String> update(@RequestBody MemberReadHistoryDto memberReadHistoryDto) {
        if (this.memberReadHistoryService.updateById(memberReadHistoryDto)) {
            return ResponseEntity.ok("修改成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("修改失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseEntity<String> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<Long> collect = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        LambdaQueryWrapper<MemberReadHistoryEntity> memberReadHistoryEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        memberReadHistoryEntityLambdaQueryWrapper.eq(MemberReadHistoryEntity::getMemberId, SecurityUtils.getCurrentUserId());
        memberReadHistoryEntityLambdaQueryWrapper.in(MemberReadHistoryEntity::getId, collect);
        return ResponseEntity.ok(this.memberReadHistoryService.remove(memberReadHistoryEntityLambdaQueryWrapper) ? "删除成功" : "删除失败");
    }
}

