package org.example.modules.admin.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.admin.finance.entity.MemberDescriptionEntity;
import org.example.modules.admin.finance.entity.dto.MemberDescriptionDto;
import org.example.modules.admin.finance.entity.vo.MemberDescriptionVo;
import org.example.modules.admin.finance.mapper.MemberDescriptionMapper;
import org.example.modules.admin.finance.service.MemberDescriptionService;

/**
 * Created by Dou-Fu-10 2023-07-31 15:40:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:40:17
 * @Description 会员详细说明(MemberDescription)表服务实现类
 */
@Service("memberDescriptionService")
public class MemberDescriptionServiceImpl extends ServiceImpl<MemberDescriptionMapper, MemberDescriptionEntity> implements MemberDescriptionService {
    @Override
    public Boolean save(MemberDescriptionDto memberDescriptionDto) {
        MemberDescriptionEntity memberDescriptionEntity = BeanCopy.convert(memberDescriptionDto, MemberDescriptionEntity.class);
        return save(memberDescriptionEntity);
    }

    @Override
    public Boolean updateById(MemberDescriptionDto memberDescriptionDto) {
        MemberDescriptionEntity memberDescriptionEntity = BeanCopy.convert(memberDescriptionDto, MemberDescriptionEntity.class);
        return updateById(memberDescriptionEntity);
    }

    @Override
    public Page<MemberDescriptionVo> page(Page<MemberDescriptionEntity> page, MemberDescriptionDto memberDescriptionDto) {
        MemberDescriptionEntity memberDescriptionEntity = BeanCopy.convert(memberDescriptionDto, MemberDescriptionEntity.class);
        LambdaQueryWrapper<MemberDescriptionEntity> memberDescriptionEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(memberDescriptionEntity);
        Page<MemberDescriptionEntity> memberDescriptionEntityPage = page(page, memberDescriptionEntityLambdaQueryWrapper);
        IPage<MemberDescriptionVo> memberDescriptionEntityPageVoIpage = memberDescriptionEntityPage.convert(memberDescription -> BeanCopy.convert(memberDescription, MemberDescriptionVo.class));
        return (Page) memberDescriptionEntityPageVoIpage;
    }
}

