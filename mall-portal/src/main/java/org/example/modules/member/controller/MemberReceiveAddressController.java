package org.example.modules.member.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.member.entity.MemberReceiveAddressEntity;
import org.example.modules.member.entity.dto.MemberReceiveAddressDto;
import org.example.modules.member.entity.vo.MemberReceiveAddressVo;
import org.example.modules.member.service.MemberReceiveAddressService;
import org.example.security.utils.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 会员收货地址 MemberReceiveAddressController
 * Created by Dou-Fu-10 2023-07-31 15:49:05
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:49:05
 * @Description 会员收货地址表(MemberReceiveAddress)表控制层
 */
@RestController
@RequestMapping("/app/memberReceiveAddress")
@Tag(name = "MemberReceiveAddressController", description = "")
public class MemberReceiveAddressController {
    /**
     * 服务对象
     */
    @Resource
    private MemberReceiveAddressService memberReceiveAddressService;

    /**
     * 查询全部数据
     *
     * @return 查询全部数据
     */
    @Operation(summary = "查询全部数据")
    @GetMapping
    public ResponseEntity<Object> selectAll() {
        List<MemberReceiveAddressVo> memberReceiveAddressEntity = memberReceiveAddressService.selectAll();
        return ResponseEntity.ok(memberReceiveAddressEntity);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "通过主键查询单条数据")
    @GetMapping("/{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        MemberReceiveAddressVo memberReceiveAddressVo = memberReceiveAddressService.selectOne(id);
        return ResponseEntity.ok(memberReceiveAddressVo);
    }

    /**
     * 新增数据
     *
     * @param memberReceiveAddressDto 实体对象
     * @return 新增结果
     */
    @Operation(summary = "新增数据")
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody MemberReceiveAddressDto memberReceiveAddressDto) {
        if (this.memberReceiveAddressService.save(memberReceiveAddressDto)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param memberReceiveAddressDto 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改数据")
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody MemberReceiveAddressDto memberReceiveAddressDto) {
        if (this.memberReceiveAddressService.updateById(memberReceiveAddressDto)) {
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
    @Operation(summary = "删除数据")
    @DeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        LambdaQueryWrapper<MemberReceiveAddressEntity> memberReceiveAddressEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 会员id
        memberReceiveAddressEntityLambdaQueryWrapper.eq(MemberReceiveAddressEntity::getMemberId, SecurityUtils.getCurrentUserId());
        // 要删除的 浏览记录
        memberReceiveAddressEntityLambdaQueryWrapper.in(MemberReceiveAddressEntity::getId, ids);

        if (this.memberReceiveAddressService.remove(memberReceiveAddressEntityLambdaQueryWrapper)) {
            return ResponseEntity.ok("删除成功");
        }
        throw new BaseRequestException("删除失败");
    }
}

