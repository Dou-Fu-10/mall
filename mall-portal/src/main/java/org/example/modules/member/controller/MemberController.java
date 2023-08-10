package org.example.modules.member.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.example.common.core.base.ValidationDto;
import org.example.common.core.entity.MemberEntity;
import org.example.modules.member.entity.dto.MemberDto;
import org.example.modules.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * 会员 MemberController
 * Created by Dou-Fu-10 2023-07-31 15:49:04
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:49:04
 * @Description 会员表(Member)表控制层
 */
@RestController
@RequestMapping("/app/member")
@Tag(name = "MemberController", description = "")
public class MemberController {
    /**
     * 服务对象
     */
    @Resource
    private MemberService memberService;


    /**
     * 修改数据
     *
     * @param memberDto 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改")
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody @Validated(ValidationDto.Update.class) MemberDto memberDto) {
        if (this.memberService.updateById(memberDto)) {
            return ResponseEntity.ok("修改成功");
        }
        // 修改成自定义的 错误类型
        throw new RuntimeException("修改失败");
    }

    /**
     * info
     *
     * @return 身份信息
     */
    @Operation(summary = "info")
    @GetMapping(value = "/info")
    public ResponseEntity<Map<String, Object>> getAdminInfo(Principal principal) {
        Map<String, Object> data = memberService.info(principal);
        return ResponseEntity.ok(data);
    }

    /**
     * 获取下级
     *
     * @param page 分页数据
     * @return 获取下级
     */

    @GetMapping("children")
    public ResponseEntity<Object> children(Page<MemberEntity> page) {
        return ResponseEntity.ok(this.memberService.children(page));
    }

    /**
     * 获取上级
     *
     * @return 获取上级
     */
    @GetMapping("parent")
    public ResponseEntity<Object> parent() {
        return ResponseEntity.ok(this.memberService.parent());
    }

    /**
     * token续约
     *
     * @param request token
     * @return token
     */
    @Operation(summary = "token续约")
    @GetMapping(value = "/refresh")
    public ResponseEntity<HashMap<String, String>> refresh(HttpServletRequest request) {
        // Token 续期
        return ResponseEntity.ok(memberService.refreshHeadToken(request));
    }


    @Operation(summary = "退出功能")
    @GetMapping(value = "/logout")
    public ResponseEntity<String> logout() {
        memberService.logout();
        return ResponseEntity.ok("退出登录成功");
    }
}

