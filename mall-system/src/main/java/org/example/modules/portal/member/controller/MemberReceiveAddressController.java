package org.example.modules.portal.member.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.portal.member.entity.MemberReceiveAddressEntity;
import org.example.modules.portal.member.entity.dto.MemberReceiveAddressDto;
import org.example.modules.portal.member.service.MemberReceiveAddressService;
import org.example.security.annotaion.rest.AnonymousDeleteMapping;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.example.security.annotaion.rest.AnonymousPutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:17
 * @Description 会员收货地址表(MemberReceiveAddress)表控制层
 */
@RestController
@RequestMapping("/api/memberReceiveAddress")
@Tag(name = "MemberReceiveAddressController", description = "会员收货地址表(MemberReceiveAddress)表控制层")
public class MemberReceiveAddressController {
    /**
     * 服务对象
     */
    @Resource
    private MemberReceiveAddressService memberReceiveAddressService;

    /**
     * 分页查询所有数据
     *
     * @param page                 分页对象
     * @param memberReceiveAddress 查询实体
     * @return 所有数据
     */
    @AnonymousGetMapping
    public ResponseEntity<Object> selectAll(Page<MemberReceiveAddressEntity> page, MemberReceiveAddressEntity memberReceiveAddress) {
        return ResponseEntity.ok(this.memberReceiveAddressService.page(page, new QueryWrapper<>(memberReceiveAddress)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @AnonymousGetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.memberReceiveAddressService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param memberReceiveAddress 实体对象
     * @return 新增结果
     */
    @AnonymousPostMapping
    public ResponseEntity<Object> insert(@RequestBody MemberReceiveAddressDto memberReceiveAddress) {
        if (this.memberReceiveAddressService.save(memberReceiveAddress)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param memberReceiveAddress 实体对象
     * @return 修改结果
     */
    @AnonymousPutMapping
    public ResponseEntity<Object> update(@RequestBody MemberReceiveAddressDto memberReceiveAddress) {
        if (this.memberReceiveAddressService.updateById(memberReceiveAddress)) {
            return ResponseEntity.ok("修改成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("修改失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @AnonymousDeleteMapping
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        return ResponseEntity.ok(this.memberReceiveAddressService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() > 1).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败");
    }
}
