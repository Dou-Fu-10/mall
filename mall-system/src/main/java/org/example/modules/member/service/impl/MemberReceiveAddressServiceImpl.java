package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.member.entity.MemberReceiveAddressEntity;
import org.example.modules.member.entity.dto.MemberReceiveAddressDto;
import org.example.modules.member.entity.vo.MemberReceiveAddressVo;
import org.example.modules.member.mapper.MemberReceiveAddressMapper;
import org.example.modules.member.service.MemberReceiveAddressService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-31 15:49:05
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:49:05
 * @Description 会员收货地址表(MemberReceiveAddress)表服务实现类
 */
@Service("memberReceiveAddressService")
public class MemberReceiveAddressServiceImpl extends ServiceImpl<MemberReceiveAddressMapper, MemberReceiveAddressEntity> implements MemberReceiveAddressService {
    @Override
    public Boolean save(MemberReceiveAddressDto memberReceiveAddressDto) {
        MemberReceiveAddressEntity memberReceiveAddressEntity = BeanCopy.convert(memberReceiveAddressDto, MemberReceiveAddressEntity.class);
        return save(memberReceiveAddressEntity);
    }

    @Override
    public Boolean updateById(MemberReceiveAddressDto memberReceiveAddressDto) {
        MemberReceiveAddressEntity memberReceiveAddressEntity = BeanCopy.convert(memberReceiveAddressDto, MemberReceiveAddressEntity.class);
        return updateById(memberReceiveAddressEntity);
    }

    @Override
    public Page<MemberReceiveAddressVo> page(Page<MemberReceiveAddressEntity> page, MemberReceiveAddressDto memberReceiveAddressDto) {
        MemberReceiveAddressEntity memberReceiveAddressEntity = BeanCopy.convert(memberReceiveAddressDto, MemberReceiveAddressEntity.class);
        LambdaQueryWrapper<MemberReceiveAddressEntity> memberReceiveAddressEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(memberReceiveAddressEntity);
        Page<MemberReceiveAddressEntity> memberReceiveAddressEntityPage = page(page, memberReceiveAddressEntityLambdaQueryWrapper);
        IPage<MemberReceiveAddressVo> memberReceiveAddressEntityPageVoIpage = memberReceiveAddressEntityPage.convert(memberReceiveAddress -> BeanCopy.convert(memberReceiveAddress, MemberReceiveAddressVo.class));
        return (Page) memberReceiveAddressEntityPageVoIpage;
    }
}

