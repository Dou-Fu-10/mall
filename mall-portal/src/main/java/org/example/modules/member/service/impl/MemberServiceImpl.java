package org.example.modules.member.service.impl;

import cn.hutool.core.util.IdUtil;
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
import org.example.common.core.utils.BeanCopy;
import org.example.common.core.utils.StringUtils;
import org.example.common.redis.service.RedisService;
import org.example.config.AuthMember;
import org.example.modules.member.entity.dto.MemberDto;
import org.example.modules.member.entity.vo.MemberReferralCodeVo;
import org.example.modules.member.entity.vo.MemberVo;
import org.example.modules.member.mapper.MemberMapper;
import org.example.modules.member.service.MemberLoginLogService;
import org.example.modules.member.service.MemberReferralCodeService;
import org.example.modules.member.service.MemberService;
import org.example.modules.security.service.OnlineMemberService;
import org.example.security.config.SecurityProperties;
import org.example.security.entity.JwtMember;
import org.example.security.utils.JwtTokenUtil;
import org.example.security.utils.SecurityUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
    private MemberReferralCodeService memberReferralCodeService;

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
        if (Objects.isNull(memberEntity)) {
            throw new BaseRequestException("请登录");
        }
        Map<String, Object> data = new HashMap<>(3);
        data.put("phone", memberEntity.getPhone());
        // 头像
        data.put("icon", memberEntity.getIcon());
        data.put("data", memberEntity);
        return data;
    }

    @Override
    public String refreshHeadToken(HttpServletRequest request) {
        // 获取token
        String token = jwtTokenUtil.resolveToken(request);
        // 续约token
        return jwtTokenUtil.refreshHeadToken(token);
    }

    @Override
    public Boolean register(AuthMember authMember) {

        // 获取验证码
        String imageCaptcha = authMember.getImageCaptcha();
        // 获取 redis 的key
        String uuid = authMember.getUuid();
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

        String referralCode = authMember.getReferralCode();
        MemberReferralCodeVo memberReferralCodeVo = memberReferralCodeService.getByCode(referralCode);
        if (Objects.isNull(memberReferralCodeVo)) {
            throw new BaseRequestException("推荐码错误");
        }
        MemberEntity memberEntity = new MemberEntity();
        // 设置上级id
        memberEntity.setParentId(memberReferralCodeVo.getMemberId());

        return memberEntity.insert();
    }

    @Override
    public Page<MemberVo> children(Page<MemberEntity> page) {
        LambdaQueryWrapper<MemberEntity> memberEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 谁登录获取谁的下级
        memberEntityLambdaQueryWrapper.eq(MemberEntity::getParentId, SecurityUtils.getCurrentUserId());
        Page<MemberEntity> memberEntityPage = page(page, memberEntityLambdaQueryWrapper);
        IPage<MemberVo> memberVoIpage = memberEntityPage.convert(memberEntity -> BeanCopy.convert(memberEntity, MemberVo.class));
        // TODO 过滤信息
        return (Page) memberVoIpage;
    }

    @Override
    public MemberVo parent() {
        // 获取登录信息
        JwtMember jwtMember = (JwtMember) SecurityUtils.getCurrentUser();
        // 获取登陆信息
        MemberEntity memberEntity = jwtMember.getUser();
        // 获取登陆者的上级
        Long parentId = memberEntity.getParentId();
        // 获取上级信息
        MemberEntity parentMember = getById(parentId);
        // TODO 过滤信息
        return BeanCopy.convert(parentMember, MemberVo.class);
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
            put("uuid", uuid);
        }};
    }

}

