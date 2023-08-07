package org.example.modules.member.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.member.entity.MemberCollectionEntity;
import org.example.modules.member.entity.dto.MemberCollectionDto;
import org.example.modules.member.service.MemberCollectionService;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.utils.SecurityUtils;
import org.springframework.http.ResponseEntity;
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
     * @param page 分页对象
     * @return 所有数据
     */
    @GetMapping
    public ResponseEntity<Object> select(Page<MemberCollectionEntity> page) {
        return ResponseEntity.ok(this.memberCollectionService.page(page));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param productId 商品id
     * @return 单条数据
     */
    @GetMapping("collection/{productId}")
    public ResponseEntity<Object> collectOrNot(@PathVariable Serializable productId) {
        return ResponseEntity.ok(this.memberCollectionService.collectOrNot(productId));
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
//    @PutMapping
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
        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        LambdaQueryWrapper<MemberCollectionEntity> memberCollectionEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        memberCollectionEntityLambdaQueryWrapper.eq(MemberCollectionEntity::getMemberId, SecurityUtils.getCurrentUserId());
        memberCollectionEntityLambdaQueryWrapper.in(MemberCollectionEntity::getProductId, ids);
        if (this.memberCollectionService.remove(memberCollectionEntityLambdaQueryWrapper)) {
            return ResponseEntity.ok("删除成功");
        }
        throw new BaseRequestException("删除失败");
    }
}

