package org.example.modules.finance.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.finance.entity.MemberDescriptionEntity;
import org.example.modules.finance.entity.dto.MemberDescriptionDto;
import org.example.modules.finance.service.MemberDescriptionService;
import org.example.security.annotaion.rest.AnonymousDeleteMapping;
import org.example.security.annotaion.rest.AnonymousPutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-31 15:40:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:40:17
 * @Description 会员详细说明(MemberDescription)表控制层
 */
@RestController
@RequestMapping("/api/memberDescription")
@Tag(name = "MemberDescriptionController", description = "会员详细说明(MemberDescription)表控制层")
public class MemberDescriptionController {
    /**
     * 服务对象
     */
    @Resource
    private MemberDescriptionService memberDescriptionService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有数据", description = "memberDescription::select")
    @GetMapping
    @PreAuthorize("@hasPermission.check('memberDescription::select')")
    public ResponseEntity<Object> select(Page<MemberDescriptionEntity> page) {
        return ResponseEntity.ok(this.memberDescriptionService.page(page, new MemberDescriptionDto()));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
//    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
//        return ResponseEntity.ok(this.memberDescriptionService.getById(id));
//    }

    /**
     * 新增数据
     *
     * @param memberDescriptionDto 实体对象
     * @return 新增结果
     */
    @Operation(summary = "新增数据", description = "memberDescription::insert")
    @PostMapping
    @PreAuthorize("@hasPermission.check('memberDescription::insert')")
    public ResponseEntity<Object> insert(@RequestBody MemberDescriptionDto memberDescriptionDto) {
        if (this.memberDescriptionService.save(memberDescriptionDto)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param memberDescriptionDto 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改数据", description = "memberDescription::update")
    @PutMapping
    @PreAuthorize("@hasPermission.check('memberDescription::update')")
    public ResponseEntity<Object> update(@RequestBody MemberDescriptionDto memberDescriptionDto) {
        if (this.memberDescriptionService.updateById(memberDescriptionDto)) {
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
    @Operation(summary = "删除数据", description = "memberDescription::remove")
    @DeleteMapping
    @PreAuthorize("@hasPermission.check('memberDescription::remove')")
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        return ResponseEntity.ok(this.memberDescriptionService.removeByIds(ids) ? "删除成功" : "删除失败");
    }
}

