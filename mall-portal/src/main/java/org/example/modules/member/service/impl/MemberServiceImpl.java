package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.example.common.core.entity.MemberEntity;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.config.AuthMember;
import org.example.modules.member.entity.dto.MemberDto;
import org.example.modules.member.entity.vo.MemberVo;
import org.example.modules.member.mapper.MemberMapper;
import org.example.modules.member.service.MemberLoginLogService;
import org.example.modules.member.service.MemberService;
import org.example.modules.security.service.OnlineMemberService;
import org.example.security.config.SecurityProperties;
import org.example.security.entity.JwtMember;
import org.example.security.utils.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Dou-Fu-10 2023-07-31 15:49:05
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:49:05
 * @Description 会员表(Member)表服务实现类
 */
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberEntity> implements MemberService {
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private SecurityProperties securityProperties;
    @Resource
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Resource
    private OnlineMemberService onlineMemberService;
    @Resource
    private MemberLoginLogService memberLoginLogService;
    @Override
    public Boolean save(MemberDto memberDto) {
        MemberEntity memberEntity = BeanCopy.convert(memberDto, MemberEntity.class);
        return save(memberEntity);
    }

    @Override
    public Boolean updateById(MemberDto memberDto) {
        MemberEntity memberEntity = BeanCopy.convert(memberDto, MemberEntity.class);
        return updateById(memberEntity);
    }

    @Override
    public Page<MemberVo> page(Page<MemberEntity> page, MemberDto memberDto) {
        MemberEntity memberEntity = BeanCopy.convert(memberDto, MemberEntity.class);
        LambdaQueryWrapper<MemberEntity> memberEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(memberEntity);
        Page<MemberEntity> memberEntityPage = page(page, memberEntityLambdaQueryWrapper);
        IPage<MemberVo> memberEntityPageVoIpage = memberEntityPage.convert(member -> BeanCopy.convert(member, MemberVo.class));
        return (Page) memberEntityPageVoIpage;
    }

    @Override
    public Map<String, Object> login(AuthMember authMember, HttpServletRequest request) {

        // 调用 UserDetailsServiceImpl 获取身份信息 同时存储用户信息 判断身份信息是否合法
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authMember.getPhone(), authMember.getPassword());
        // 对用户的密码进行验证
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 判断是否认证通过
        if (Objects.isNull(authentication)) {
            throw new BaseRequestException("用户名或者密码错误");
        }
        // 获取到用户信息
        final JwtMember jwtMember = (JwtMember) authentication.getPrincipal();

        // 使用用户的username作为key获取token
        String token = jwtTokenUtil.createMemberToken(jwtMember);
        // 确保token可以 功能非必须
        if (!StringUtils.hasText(token)) {
            throw new BaseRequestException("登录失败");
        }
        Map<String, Object> tokenMap = new HashMap<>(2);
        tokenMap.put("token", securityProperties.getTokenStartWith() + token);
        // 是否唯一登录
        if (securityProperties.getSingleLogin()) {
            // 踢掉之前已经登录的token   getUsername 对应的是用户的手机号码
            onlineMemberService.kickOutForUsername(jwtMember.getUsername());
        }
        // 保存用户的在线信息
        if (!onlineMemberService.save(jwtMember, token, request)) {
            throw new BaseRequestException("登录失败");
        }
        // 记录登录痕迹
        memberLoginLogService.insertLoginLog(jwtMember.getUser().getId(), request);
        return tokenMap;
    }

    @Override
    public MemberEntity getByPhone(String phone) {
        return lambdaQuery().eq(MemberEntity::getPhone, phone).one();
    }

    @Override
    public MemberVo info(HttpServletRequest request) {
        return null;
    }
    @Override
    public String refreshHeadToken(HttpServletRequest request) {
        // 获取token
        String token = jwtTokenUtil.resolveToken(request);
        // 续约token
        return jwtTokenUtil.refreshHeadToken(token);
    }

    @Override
    public Boolean register(MemberDto memberDto) {
        return null;
    }
}

