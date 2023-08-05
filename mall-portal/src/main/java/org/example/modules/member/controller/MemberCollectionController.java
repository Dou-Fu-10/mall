package org.example.modules.member.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.common.core.exception.BaseRequestException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.modules.member.entity.dto.MemberCollectionDto;
import org.example.modules.member.entity.MemberCollectionEntity;
import org.example.modules.member.service.MemberCollectionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-08-05 13:27:10
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 13:27:10
 * @Description 会员收藏(MemberCollection)表控制层
 */
@RestController
@RequestMapping("/app/memberCollection")
@Tag(name = "MemberCollectionController", description = "")
public class MemberCollectionController {
    /**
     * 服务对象
     */
    @Resource
    private MemberCollectionService memberCollectionService;

    /**
     * 分页查询所有数据
     *
     * @param page                分页对象
     * @param memberCollectionDto 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<MemberCollectionEntity> page, MemberCollectionDto memberCollectionDto) {
        return ResponseEntity.ok(this.memberCollectionService.page(page, memberCollectionDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.memberCollectionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param memberCollectionDto 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody MemberCollectionDto memberCollectionDto) {
        if (this.memberCollectionService.save(memberCollectionDto)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param memberCollectionDto 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<String> update(@RequestBody MemberCollectionDto memberCollectionDto) {
        if (this.memberCollectionService.updateById(memberCollectionDto)) {
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
        Set<Long> collect = idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() >= 1).limit(10).collect(Collectors.toSet());
        return ResponseEntity.ok(this.memberCollectionService.removeByIds(collect) ? "删除成功" : "删除失败");
    }
}

