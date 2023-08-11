package org.example.modules.member.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.base.ValidationDto;
import org.example.modules.member.entity.MemberReceiveAddressEntity;
import org.example.modules.member.entity.dto.MemberReceiveAddressDto;
import org.example.modules.member.service.MemberReceiveAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * 会员收货地址表 MemberReceiveAddressController
 * Created by Dou-Fu-10 2023-07-31 15:49:05
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:49:05
 * @Description 会员收货地址表(MemberReceiveAddress)表控制层
 */
@RestController
@RequestMapping("/api/memberReceiveAddress")
@Tag(name = "MemberReceiveAddressController", description = "")
public class MemberReceiveAddressController {
    /**
     * 服务对象
     */
    @Resource
    private MemberReceiveAddressService memberReceiveAddressService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @Operation(summary = "通过主键查询单条数据", description = "memberReceiveAddress::selectOne")
    @PreAuthorize("@hasPermission.check('memberReceiveAddress::selectOne')")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.memberReceiveAddressService.getByMemberReceiveAddressId(id));
    }

    /**
     * 通过会员id 查询所有数据
     *
     * @param id 会员id
     * @return 所有数据
     */
    @GetMapping("/memberId/{id}")
    @Operation(summary = "通过主键查询单条数据", description = "memberReceiveAddress::memberId")
    @PreAuthorize("@hasPermission.check('memberReceiveAddress::memberId')")
    public ResponseEntity<Object> selectByMemberId(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.memberReceiveAddressService.getByMemberId(id));
    }
}

