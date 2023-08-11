package org.example.modules.finance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.finance.entity.MemberDescriptionEntity;
import org.example.modules.finance.entity.dto.MemberDescriptionDto;
import org.example.modules.finance.entity.vo.MemberDescriptionVo;
import org.example.modules.finance.mapper.MemberDescriptionMapper;
import org.example.modules.finance.service.MemberDescriptionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
    public MemberDescriptionEntity getByTitle(String title) {
        if (Objects.isNull(title)) {
            throw new BaseRequestException("参数有误");
        }
        return lambdaQuery().eq(MemberDescriptionEntity::getTitle, title).one();
    }

    @Override
    public Boolean insert(@NotNull MemberDescriptionEntity memberDescriptionEntity) {
        if (Objects.nonNull(getByTitle(memberDescriptionEntity.getTitle()))) {
            throw new BaseRequestException("标题输入有误或者标题被占用");
        }
        return save(memberDescriptionEntity);
    }


    @Override
    public Page<MemberDescriptionVo> page(Page<MemberDescriptionEntity> page, MemberDescriptionDto memberDescriptionDto) {
        MemberDescriptionEntity memberDescriptionEntity = BeanCopy.convert(memberDescriptionDto, MemberDescriptionEntity.class);
        LambdaQueryWrapper<MemberDescriptionEntity> memberDescriptionEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(memberDescriptionEntity);
        // 以id进行排序
        memberDescriptionEntityLambdaQueryWrapper.orderByDesc(MemberDescriptionEntity::getId);
        Page<MemberDescriptionEntity> memberDescriptionEntityPage = page(page, memberDescriptionEntityLambdaQueryWrapper);
        IPage<MemberDescriptionVo> memberDescriptionEntityPageVoIPage = memberDescriptionEntityPage.convert(memberDescription -> BeanCopy.convert(memberDescription, MemberDescriptionVo.class));
        return (Page<MemberDescriptionVo>) memberDescriptionEntityPageVoIPage;
    }

    @Override
    public Boolean updateByMemberDescriptionId(MemberDescriptionEntity memberDescriptionEntity) {
        String title = memberDescriptionEntity.getTitle();
        MemberDescriptionEntity memberDescription = lambdaQuery()
                .eq(MemberDescriptionEntity::getTitle, title)
                .ne(MemberDescriptionEntity::getId, memberDescriptionEntity.getId()).one();
        if (Objects.nonNull(memberDescription)) {
            throw new BaseRequestException("标题输入有误或者标题被占用");
        }
        return memberDescriptionEntity.updateById();
    }
}

