package org.example.modules.member.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.member.entity.MemberReferralCodeEntity;
import org.example.modules.member.entity.vo.MemberReferralCodeVo;
import org.example.modules.member.mapper.MemberReferralCodeMapper;
import org.example.modules.member.service.MemberReferralCodeService;
import org.example.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by Dou-Fu-10 2023-08-09 12:46:30
 *
 * @author Dou-Fu-10
 * @date 2023-08-09 12:46:30
 * @Description 推荐码(MemberReferralCode)表服务实现类
 */
@Service("memberReferralCodeService")
public class MemberReferralCodeServiceImpl extends ServiceImpl<MemberReferralCodeMapper, MemberReferralCodeEntity> implements MemberReferralCodeService {
    @Override
    public Boolean save() {
        MemberReferralCodeEntity memberReferralCodeEntity = new MemberReferralCodeEntity();
        // 设置会员id
        memberReferralCodeEntity.setMemberId(SecurityUtils.getCurrentUserId());
        // 设置推荐码
        memberReferralCodeEntity.setCode(IdUtil.simpleUUID() + SecurityUtils.getCurrentUserId());
        return save(memberReferralCodeEntity);
    }


    @Override
    public Page<MemberReferralCodeVo> page(Page<MemberReferralCodeEntity> page) {
        MemberReferralCodeEntity memberReferralCodeEntity = new MemberReferralCodeEntity();
        // 设置 会员id
        memberReferralCodeEntity.setMemberId(SecurityUtils.getCurrentUserId());

        LambdaQueryWrapper<MemberReferralCodeEntity> memberReferralCodeEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(memberReferralCodeEntity);
        // 获取结果
        Page<MemberReferralCodeEntity> memberReferralCodeEntityPage = page(page, memberReferralCodeEntityLambdaQueryWrapper);
        //
        IPage<MemberReferralCodeVo> memberReferralCodeEntityPageVoIpage = memberReferralCodeEntityPage.convert(memberReferralCode -> BeanCopy.convert(memberReferralCode, MemberReferralCodeVo.class));
        return (Page) memberReferralCodeEntityPageVoIpage;
    }

    @Override
    public MemberReferralCodeVo getByMemberReferralCodeId(Serializable id) {
        MemberReferralCodeEntity memberReferralCodeEntity = getById(id);
        return BeanCopy.convert(memberReferralCodeEntity, MemberReferralCodeVo.class);
    }

    @Override
    public MemberReferralCodeVo getByCode(String code) {
        MemberReferralCodeEntity memberReferralCodeEntity = lambdaQuery().eq(MemberReferralCodeEntity::getCode, code).one();
        return BeanCopy.convert(memberReferralCodeEntity, MemberReferralCodeVo.class);
    }
}

