package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.member.entity.MemberCollectionEntity;
import org.example.modules.member.entity.dto.MemberCollectionDto;
import org.example.modules.member.entity.vo.MemberCollectionVo;
import org.example.modules.member.mapper.MemberCollectionMapper;
import org.example.modules.member.service.MemberCollectionService;

/**
 * Created by Dou-Fu-10 2023-08-05 13:27:10
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 13:27:10
 * @Description 会员收藏(MemberCollection)表服务实现类
 */
@Service("memberCollectionService")
public class MemberCollectionServiceImpl extends ServiceImpl<MemberCollectionMapper, MemberCollectionEntity> implements MemberCollectionService {
    @Override
    public Boolean save(MemberCollectionDto memberCollectionDto) {
        MemberCollectionEntity memberCollectionEntity = BeanCopy.convert(memberCollectionDto, MemberCollectionEntity.class);
        return save(memberCollectionEntity);
    }

    @Override
    public Boolean updateById(MemberCollectionDto memberCollectionDto) {
        MemberCollectionEntity memberCollectionEntity = BeanCopy.convert(memberCollectionDto, MemberCollectionEntity.class);
        return updateById(memberCollectionEntity);
    }

    @Override
    public Page<MemberCollectionVo> page(Page<MemberCollectionEntity> page, MemberCollectionDto memberCollectionDto) {
        MemberCollectionEntity memberCollectionEntity = BeanCopy.convert(memberCollectionDto, MemberCollectionEntity.class);
        LambdaQueryWrapper<MemberCollectionEntity> memberCollectionEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(memberCollectionEntity);
        Page<MemberCollectionEntity> memberCollectionEntityPage = page(page, memberCollectionEntityLambdaQueryWrapper);
        IPage<MemberCollectionVo> memberCollectionEntityPageVoIpage = memberCollectionEntityPage.convert(memberCollection -> BeanCopy.convert(memberCollection, MemberCollectionVo.class));
        return (Page) memberCollectionEntityPageVoIpage;
    }
}

