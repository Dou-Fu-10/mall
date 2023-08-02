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
import org.example.modules.member.service.MemberService;
import org.example.security.config.SecurityProperties;
import org.example.security.utils.JwtTokenUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private PasswordEncoder passwordEncoder;

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
        HashMap<String, Object> tokenMap = new HashMap<>();
        // 手机密码登录
        MemberEntity memberEntity = getByPhone(authMember.getPhone());
        if (Objects.isNull(memberEntity)) {
            throw new BaseRequestException("账号或密码错误");
        }
        // 校验密码是否正确
        if (passwordEncoder.matches(authMember.getPassword(), memberEntity.getPassword())) {
            // 成功登录
            String memberToken = jwtTokenUtil.createMemberToken(memberEntity.getPhone());
            tokenMap.put("token", securityProperties.getTokenStartWith() + memberToken);
        }

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
}

