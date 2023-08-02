package org.example.modules.member.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.entity.MemberEntity;
import org.example.modules.member.service.MemberService;
import org.example.security.entity.JwtMember;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Dou-Fu-10 2023-07-07 09:58:02
 *
 * @author Dou-Fu-10
 * @date 2023-07-07 09:58:02
 * @Description 后台用户表(User)表服务实现类
 */
@Slf4j
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private MemberService memberService;

    @Override
    public JwtMember loadUserByUsername(String phone) {
        // 根据用户名获取用户信息
        MemberEntity member = memberService.getByPhone(phone);
        if (Objects.isNull(member)) {
            throw new BadCredentialsException("登录失败");
        }
        // 判断账号是否激活
        if (!member.getEnabled()) {
            throw new BadCredentialsException("账号未激活！");
        }
        return new JwtMember(member, new ArrayList<>());
    }
}
