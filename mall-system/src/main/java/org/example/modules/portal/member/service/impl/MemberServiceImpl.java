package org.example.modules.portal.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.portal.member.entity.MemberEntity;
import org.example.modules.portal.member.entity.dto.MemberDto;
import org.example.modules.portal.member.entity.vo.MemberVo;
import org.example.modules.portal.member.mapper.MemberMapper;
import org.example.modules.portal.member.service.MemberService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:16
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:16
 * @Description 会员表(Member)表服务实现类
 */
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberEntity> implements MemberService {
    @Override
    public boolean save(MemberDto member) {
        MemberEntity memberEntity = BeanCopy.convert(member, MemberEntity.class);
        return memberEntity.insert();
    }

    @Override
    public boolean updateById(MemberDto member) {
        MemberEntity memberEntity = BeanCopy.convert(member, MemberEntity.class);
        return memberEntity.updateById();
    }

    @Override
    public Page<MemberVo> page(Page<MemberEntity> page, MemberEntity member) {
        LambdaQueryWrapper<MemberEntity> memberEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(member);
        Page<MemberEntity> memberEntityPage = page(page, memberEntityLambdaQueryWrapper);
        IPage<MemberVo> memberVoIpage = memberEntityPage.convert(memberEntity -> BeanCopy.convert(memberEntity, MemberVo.class));
        return (Page) memberVoIpage;
    }
}

