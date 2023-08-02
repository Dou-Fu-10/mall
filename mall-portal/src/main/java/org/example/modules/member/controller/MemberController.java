package org.example.modules.member.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.example.common.core.entity.MemberEntity;
import org.example.common.core.exception.BaseRequestException;
import org.example.config.AuthMember;
import org.example.modules.member.entity.dto.MemberDto;
import org.example.modules.member.entity.vo.MemberVo;
import org.example.modules.member.service.MemberService;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-31 15:49:04
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:49:04
 * @Description 会员表(Member)表控制层
 */
@RestController
@RequestMapping("/member")
@Tag(name = "MemberController", description = "")
public class MemberController {
    /**
     * 服务对象
     */
    @Resource
    private MemberService memberService;

    /**
     * 分页查询所有数据
     *
     * @param page      分页对象
     * @param memberDto 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseEntity<Object> selectAll(Page<MemberEntity> page, MemberDto memberDto) {
        return ResponseEntity.ok(this.memberService.page(page, memberDto));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.memberService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param memberDto 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody MemberDto memberDto) {
        if (this.memberService.save(memberDto)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param memberDto 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody MemberDto memberDto) {
        if (this.memberService.updateById(memberDto)) {
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
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        Set<Long> collect = idList.stream().filter(id -> String.valueOf(id).length() < 20 && String.valueOf(id).length() >= 1).limit(10).collect(Collectors.toSet());
        return ResponseEntity.ok(this.memberService.removeByIds(collect) ? "删除成功" : "删除失败");
    }

    /**
     * 登录以后返回token
     *
     * @param authMember 登录用户
     * @param request    Http Servlet请求
     * @return token
     */
    @Operation(
            summary = "登录",
            description = "用户登录返回token"
    )
    @AnonymousPostMapping(value = "/login")
    public ResponseEntity<Map<String, Object>> login(@Validated @RequestBody AuthMember authMember, HttpServletRequest request) {
        return ResponseEntity.ok(memberService.login(authMember, request));
    }

    /**
     * info
     *
     * @return 身份信息
     */
    @Operation(summary = "info")
    @AnonymousGetMapping(value = "/info")
    public ResponseEntity<MemberVo> info(HttpServletRequest request) {
        return ResponseEntity.ok(memberService.info(request));
    }
}

