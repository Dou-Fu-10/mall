package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.member.entity.MemberReadHistoryEntity;
import org.example.modules.member.entity.dto.MemberReadHistoryDto;
import org.example.modules.member.entity.vo.MemberReadHistoryVo;
import org.example.modules.member.mapper.MemberReadHistoryMapper;
import org.example.modules.member.service.MemberReadHistoryService;

/**
 * Created by Dou-Fu-10 2023-08-05 13:27:10
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 13:27:10
 * @Description 会员阅读历史记录(MemberReadHistory)表服务实现类
 */
@Service("memberReadHistoryService")
public class MemberReadHistoryServiceImpl extends ServiceImpl<MemberReadHistoryMapper, MemberReadHistoryEntity> implements MemberReadHistoryService {
    @Override
    public Boolean save(MemberReadHistoryDto memberReadHistoryDto) {
        MemberReadHistoryEntity memberReadHistoryEntity = BeanCopy.convert(memberReadHistoryDto, MemberReadHistoryEntity.class);
        return save(memberReadHistoryEntity);
    }

    @Override
    public Boolean updateById(MemberReadHistoryDto memberReadHistoryDto) {
        MemberReadHistoryEntity memberReadHistoryEntity = BeanCopy.convert(memberReadHistoryDto, MemberReadHistoryEntity.class);
        return updateById(memberReadHistoryEntity);
    }

    @Override
    public Page<MemberReadHistoryVo> page(Page<MemberReadHistoryEntity> page, MemberReadHistoryDto memberReadHistoryDto) {
        MemberReadHistoryEntity memberReadHistoryEntity = BeanCopy.convert(memberReadHistoryDto, MemberReadHistoryEntity.class);
        LambdaQueryWrapper<MemberReadHistoryEntity> memberReadHistoryEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(memberReadHistoryEntity);
        Page<MemberReadHistoryEntity> memberReadHistoryEntityPage = page(page, memberReadHistoryEntityLambdaQueryWrapper);
        IPage<MemberReadHistoryVo> memberReadHistoryEntityPageVoIpage = memberReadHistoryEntityPage.convert(memberReadHistory -> BeanCopy.convert(memberReadHistory, MemberReadHistoryVo.class));
        return (Page) memberReadHistoryEntityPageVoIpage;
    }
}

