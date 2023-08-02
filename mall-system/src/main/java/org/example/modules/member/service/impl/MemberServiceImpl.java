package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.member.entity.MemberEntity;
import org.example.modules.member.entity.dto.MemberDto;
import org.example.modules.member.entity.vo.MemberVo;
import org.example.modules.member.mapper.MemberMapper;
import org.example.modules.member.service.MemberService;
import org.springframework.stereotype.Service;


/**
 * Created by Dou-Fu-10 2023-07-31 15:49:05
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:49:05
 * @Description 会员表(Member)表服务实现类
 */
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberEntity> implements MemberService {

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
}

