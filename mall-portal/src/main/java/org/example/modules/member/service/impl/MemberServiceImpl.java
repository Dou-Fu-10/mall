package org.example.modules.member.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.captcha.base.Captcha;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.example.common.core.config.CaptchaCodeConfig;
import org.example.common.core.entity.MemberEntity;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.server.MinioServer;
import org.example.common.core.utils.BeanCopy;
import org.example.common.core.utils.StringUtils;
import org.example.common.redis.service.RedisService;
import org.example.config.AuthMember;
import org.example.config.UpdatePassword;
import org.example.modules.member.entity.dto.MemberDto;
import org.example.modules.member.entity.vo.MemberVo;
import org.example.modules.member.entity.vo.ParentOrChildren;
import org.example.modules.member.mapper.MemberMapper;
import org.example.modules.member.service.MemberBonusService;
import org.example.modules.member.service.MemberLoginLogService;
import org.example.modules.member.service.MemberService;
import org.example.modules.security.service.OnlineMemberService;
import org.example.security.config.SecurityProperties;
import org.example.security.entity.JwtMember;
import org.example.security.utils.JwtTokenUtil;
import org.example.security.utils.SecurityUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
    @Resource
    private RedisService redisService;
    @Resource
    private CaptchaCodeConfig captchaCodeConfig;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private MinioServer minioServer;
    @Resource
    private MemberBonusService memberBonusService;
    @Override
    public Boolean save(MemberDto memberDto) {
        MemberEntity memberEntity = BeanCopy.convert(memberDto, MemberEntity.class);
        return save(memberEntity);
    }


    @Override
    public Boolean updateById(MemberDto memberDto) {
        MemberEntity memberEntity = BeanCopy.convert(memberDto, MemberEntity.class);
        String icon = memberEntity.getIcon();
        if (Objects.nonNull(icon)) {
            if (!minioServer.checkObjectIsExist(icon)) {
                throw new BaseRequestException("请输入正确的头像");
            }
        }
        memberEntity.setId(SecurityUtils.getCurrentUserId());
        return updateById(memberEntity);
    }

    @Override
    public Page<MemberVo> page(Page<MemberEntity> page, MemberDto memberDto) {
        MemberEntity memberEntity = BeanCopy.convert(memberDto, MemberEntity.class);
        LambdaQueryWrapper<MemberEntity> memberEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(memberEntity);
        Page<MemberEntity> memberEntityPage = page(page, memberEntityLambdaQueryWrapper);
        IPage<MemberVo> memberEntityPageVoIPage = memberEntityPage.convert(member -> BeanCopy.convert(member, MemberVo.class));
        return (Page<MemberVo>) memberEntityPageVoIPage;
    }

    @Override
    public Map<String, Object> login(@NotNull AuthMember authMember, HttpServletRequest request) {
        // 验证码校验
        verificationCode(authMember);

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
        if (StringUtils.isBlank(token)) {
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
        if (StringUtils.isBlank(phone)) {
            throw new BaseRequestException("参数有误");
        }
        return lambdaQuery().eq(MemberEntity::getPhone, phone).one();
    }

    @Override
    public Map<String, Object> info(Principal principal) {
        // 判断是否登录
        if (Objects.isNull(principal)) {
            throw new BaseRequestException("请登录");
        }
        String phone = principal.getName();
        if (StringUtils.isBlank(phone)) {
            throw new BaseRequestException("请登录");
        }
        MemberEntity memberEntity = getByPhone(phone);
        MemberVo memberVo = BeanCopy.convert(memberEntity, MemberVo.class);
        if (Objects.isNull(memberEntity)) {
            throw new BaseRequestException("请登录");
        }
        Map<String, Object> data = new HashMap<>(3);
        data.put("phone", memberVo.getPhone());
        // 头像
        data.put("icon", memberVo.getIcon());
        data.put("data", memberVo);
        data.put("memberBonus", memberBonusService.getMemberBonus());
        data.put("invitationCode", memberEntity.getInvitationCode());
        return data;
    }

    @Override
    public HashMap<String, String> refreshHeadToken(HttpServletRequest request) {
        // 获取token
        String token = jwtTokenUtil.resolveToken(request);
        if (StringUtils.isBlank(token)) {
            throw new BaseRequestException("续约失败");
        }
        // 续约token
        HashMap<String, String> hashMap = new HashMap<>(1);
        hashMap.put("token", securityProperties.getTokenStartWith() + jwtTokenUtil.refreshHeadToken(token));
        return hashMap;
    }

    private MemberEntity getByInvitationCode(String invitationCode) {
        if (Objects.isNull(invitationCode)) {
            throw new BaseRequestException("参数有误");
        }
        return lambdaQuery().eq(MemberEntity::getInvitationCode, invitationCode).one();
    }

    @Override
    public Boolean register(@NotNull AuthMember authMember) {
        // 验证码校验
        verificationCode(authMember);

        String phone = authMember.getPhone();
        // 验证手机号
        MemberEntity memberPhone = getByPhone(phone);
        if (Objects.nonNull(memberPhone)) {
            throw new BaseRequestException("手机号已被占用");
        }

        MemberEntity memberEntity = new MemberEntity();
        // 推荐码
        String referralCode = authMember.getReferralCode();
        // 当填写了推荐码在校验
        if (Objects.nonNull(referralCode)) {
            MemberEntity byInvitationCode = getByInvitationCode(referralCode);
            if (Objects.isNull(byInvitationCode)) {
                throw new BaseRequestException("推荐码错误");
            }
            // 设置上级id
            memberEntity.setParentId(byInvitationCode.getId());
        }

        // 设置手机号
        memberEntity.setPhone(phone);
        String encode = passwordEncoder.encode(authMember.getPassword());
        // 密码
        memberEntity.setPassword(encode);
        String invitationCode = null;
        // 设置邀请码
        while (Objects.isNull(invitationCode)) {
            // TODO 此方法需要重构 无法抵御高并发
            // 获取生成的邀请码
            Set<String> invitationCodes = generateInvitationCode();
            // 获取已存在数据库的邀请码
            List<MemberEntity> invitationCodeExists = lambdaQuery().eq(MemberEntity::getInvitationCode, invitationCodes).list();
            // 获取已存在数据库的邀请码
            Set<String> collect = invitationCodeExists.stream().map(MemberEntity::getInvitationCode).collect(Collectors.toSet());
            // 从获取生成的邀请码中删除已存在的邀请码
            invitationCodes.removeAll(collect);
            // 当获取的邀请 不为空时
            if (!invitationCodes.isEmpty()) {
                // 获取第一个邀请码
                Iterator<String> iterator = invitationCodes.iterator();
                invitationCode = iterator.next();
            }
        }
        memberEntity.setInvitationCode(invitationCode);
        return memberEntity.insert();
    }

    @NotNull
    private Set<String> generateInvitationCode() {
        Set<String> invitationCodes = new HashSet<>();
        while (invitationCodes.size() < 10) {
            String code = RandomUtil.randomString(6);
            invitationCodes.add(code);
        }
        return invitationCodes;
    }

    @Override
    public Page<ParentOrChildren> children(Page<MemberEntity> page) {
        LambdaQueryWrapper<MemberEntity> memberEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 谁登录获取谁的下级
        memberEntityLambdaQueryWrapper.eq(MemberEntity::getParentId, SecurityUtils.getCurrentUserId());
        Page<MemberEntity> memberEntityPage = page(page, memberEntityLambdaQueryWrapper);
        IPage<ParentOrChildren> memberVoIPage = memberEntityPage.convert(memberEntity -> BeanCopy.convert(memberEntity, ParentOrChildren.class));
        return (Page<ParentOrChildren>) memberVoIPage;
    }

    @Override
    public ParentOrChildren parent() {
        // 获取登录信息
        JwtMember jwtMember = (JwtMember) SecurityUtils.getCurrentUser();
        // 获取登陆信息
        MemberEntity memberEntity = jwtMember.getUser();
        // 获取登陆者的上级
        Long parentId = memberEntity.getParentId();
        // 获取上级信息
        MemberEntity parentMember = getById(parentId);
        return BeanCopy.convert(parentMember, ParentOrChildren.class);
    }

    @Override
    public void logout() {
        // 获取登陆 信息
        String adminName = SecurityUtils.getCurrentUsername();
        //退出登录
        onlineMemberService.kickOutForUsername(adminName);
    }

    @Override
    public Map<String, Object> generateVerificationCode() {
        Captcha captcha = captchaCodeConfig.switchCaptcha();
        // 设置 验证的key
        String uuid = IdUtil.simpleUUID();
        String captchaValue = captcha.text();
        redisService.set(securityProperties.getCaptchaKey() + uuid, captchaValue, captchaCodeConfig.getExpiration(), TimeUnit.MINUTES);
        // 验证码信息
        return new HashMap<>(2) {{
            put("img", captcha.toBase64());
            put("captchaUuid", uuid);
        }};
    }

    @Override
    public Object sendSMS() {
        return null;
    }

    @Override
    public Boolean updatePassword(@NotNull UpdatePassword updatePassword) {
        // 获取 用户名信息
        MemberEntity memberEntity = lambdaQuery().eq(MemberEntity::getPhone, updatePassword.getPhone()).one();
        // 判断密码是否正确
        if (passwordEncoder.matches(updatePassword.getOldPassword(), memberEntity.getPassword())) {
            // 修改密码
            memberEntity.setPassword(passwordEncoder.encode(updatePassword.getNewPassword()));
            // 更新
            return memberEntity.updateById();
        }
        return false;
    }

    private void verificationCode(@NotNull AuthMember authMember) {
        // 获取验证码
        String imageCaptcha = authMember.getImageCaptcha();
        // 获取 redis 的key
        String uuid = authMember.getCaptchaUuid();
        if (Objects.isNull(uuid)) {
            throw new BaseRequestException("验证码错误");
        }
        String uuidKey = securityProperties.getCaptchaKey() + uuid;
        // 查询验证码
        String code = (String) redisService.get(uuidKey);
        // 清除验证码
        redisService.del(uuidKey);
        if (StringUtils.isBlank(code)) {
            throw new BaseRequestException("验证码不存在或已过期");
        }
        // 忽略大小写校验
        if (StringUtils.isBlank(imageCaptcha) || !imageCaptcha.equalsIgnoreCase(code)) {
            throw new BaseRequestException("验证码错误");
        }
    }
}

