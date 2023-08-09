package org.example.modules.member.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.modules.member.entity.MemberReferralCodeEntity;
import org.example.modules.member.service.MemberReferralCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * Created by Dou-Fu-10 2023-08-09 12:46:30
 *
 * @author Dou-Fu-10
 * @date 2023-08-09 12:46:30
 * @Description 推荐码(MemberReferralCode)表控制层
 */
@RestController
@RequestMapping("/app/memberReferralCode")
@Tag(name = "MemberReferralCodeController", description = "")
public class MemberReferralCodeController {
    /**
     * 服务对象
     */
    @Resource
    private MemberReferralCodeService memberReferralCodeService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @GetMapping
    public ResponseEntity<Object> select(Page<MemberReferralCodeEntity> page) {
        return ResponseEntity.ok(this.memberReferralCodeService.page(page));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.memberReferralCodeService.getByMemberReferralCodeId(id));
    }

    /**
     * 新增数据
     *
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<String> insert() {
        if (this.memberReferralCodeService.save()) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("添加失败");
    }
}

