package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.validation.constraints.NotNull;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.member.entity.MemberReceiveAddressEntity;
import org.example.modules.member.entity.dto.MemberReceiveAddressDto;
import org.example.modules.member.entity.vo.MemberReceiveAddressVo;
import org.example.modules.member.mapper.MemberReceiveAddressMapper;
import org.example.modules.member.service.MemberReceiveAddressService;
import org.example.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean save(MemberReceiveAddressDto memberReceiveAddressDto) {
        MemberReceiveAddressEntity memberReceiveAddressEntity = BeanCopy.convert(memberReceiveAddressDto, MemberReceiveAddressEntity.class);
        // 设置会员的 id
        memberReceiveAddressEntity.setMemberId(SecurityUtils.getCurrentUserId());
        // 校验是否是默认的
        if (Objects.nonNull(memberReceiveAddressDto.getDefaultStatus()) && memberReceiveAddressDto.getDefaultStatus()) {
            // 如果要设置成默认的 就获取之前全部的 地址
            List<MemberReceiveAddressEntity> memberReceiveAddressEntityList = lambdaQuery().eq(MemberReceiveAddressEntity::getMemberId, SecurityUtils.getCurrentUserId()).list();
            // 将地址设置成 非默认
            for (MemberReceiveAddressEntity receiveAddressEntity : memberReceiveAddressEntityList) {
                receiveAddressEntity.setDefaultStatus(false);
            }
            // 更新
            return updateBatchById(memberReceiveAddressEntityList);
        }
        // 保证
        return save(memberReceiveAddressEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean updateById(MemberReceiveAddressDto memberReceiveAddressDto) {

        Long memberReceiveAddressDtoId = memberReceiveAddressDto.getId();

        Long memberId = SecurityUtils.getCurrentUserId();

        MemberReceiveAddressEntity one = lambdaQuery().eq(MemberReceiveAddressEntity::getId, memberReceiveAddressDtoId).eq(MemberReceiveAddressEntity::getMemberId, memberId).one();

        if (Objects.isNull(one)) {
            return false;
        }

        MemberReceiveAddressEntity memberReceiveAddressEntity = BeanCopy.convert(memberReceiveAddressDto, MemberReceiveAddressEntity.class);
        memberReceiveAddressEntity.setMemberId(memberId);
        // 判断是否修改的是默认地址
        if (Objects.isNull(memberReceiveAddressDto.getDefaultStatus())) {
            return updateById(memberReceiveAddressEntity);
        } else {
            // 要修改的 地址
            Long memberReceiveAddressId = memberReceiveAddressEntity.getId();
            MemberReceiveAddressEntity memberReceiveAddress = lambdaQuery().eq(MemberReceiveAddressEntity::getId, memberReceiveAddressId) // 以地址id为条件
                    .eq(MemberReceiveAddressEntity::getMemberId, memberId) // 以会员id为条件
                    .eq(MemberReceiveAddressEntity::getDefaultStatus, memberReceiveAddressDto.getDefaultStatus()).one(); // 是否为默认地址为条件
            // 不为空说明 不修改默认地址
            if (Objects.nonNull(memberReceiveAddress)) {
                return updateById(memberReceiveAddressEntity);
            } else {
                // 简要修改默认地址
                // 查询 会员的所有地址
                List<MemberReceiveAddressEntity> memberReceiveAddressEntityList = lambdaQuery().eq(MemberReceiveAddressEntity::getMemberId, SecurityUtils.getCurrentUserId()).list();
                List<MemberReceiveAddressEntity> newMemberReceiveAddressEntityList = new ArrayList<>();
                newMemberReceiveAddressEntityList.add(memberReceiveAddressEntity);
                // 设置成非默认的
                for (MemberReceiveAddressEntity receiveAddressEntity : memberReceiveAddressEntityList) {
                    if (!receiveAddressEntity.getId().equals(memberReceiveAddressId)) {
                        receiveAddressEntity.setDefaultStatus(false);
                        newMemberReceiveAddressEntityList.add(receiveAddressEntity);
                    }
                }
                return updateBatchById(newMemberReceiveAddressEntityList);
            }
        }
    }

    @Override
    public Page<MemberReceiveAddressVo> page(Page<MemberReceiveAddressEntity> page, MemberReceiveAddressDto memberReceiveAddressDto) {
        MemberReceiveAddressEntity memberReceiveAddressEntity = BeanCopy.convert(memberReceiveAddressDto, MemberReceiveAddressEntity.class);
        LambdaQueryWrapper<MemberReceiveAddressEntity> memberReceiveAddressEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(memberReceiveAddressEntity);
        Page<MemberReceiveAddressEntity> memberReceiveAddressEntityPage = page(page, memberReceiveAddressEntityLambdaQueryWrapper);
        IPage<MemberReceiveAddressVo> memberReceiveAddressEntityPageVoIPage = memberReceiveAddressEntityPage.convert(memberReceiveAddress -> BeanCopy.convert(memberReceiveAddress, MemberReceiveAddressVo.class));
        return (Page<MemberReceiveAddressVo>) memberReceiveAddressEntityPageVoIPage;
    }

    @Override
    public List<MemberReceiveAddressVo> getReceiveAddressByMemberId(@NotNull Serializable memberId) {
        List<MemberReceiveAddressEntity> memberReceiveAddressEntityList = lambdaQuery().eq(MemberReceiveAddressEntity::getMemberId, memberId).list();
        return BeanCopy.copytList(memberReceiveAddressEntityList, MemberReceiveAddressVo.class);
    }

    @Override
    public MemberReceiveAddressVo getReceiveAddressByMemberIdAndMemberReceiveAddressId(Long memberReceiveAddressId, Long memberId) {
        MemberReceiveAddressEntity memberReceiveAddressEntity = lambdaQuery().eq(MemberReceiveAddressEntity::getMemberId, memberId).eq(MemberReceiveAddressEntity::getId, memberReceiveAddressId).one();
        return BeanCopy.convert(memberReceiveAddressEntity, MemberReceiveAddressVo.class);
    }

    @Override
    public MemberReceiveAddressVo selectOne(Serializable id) {
        // 只能获取会员的地址
        MemberReceiveAddressEntity memberReceiveAddressEntity = lambdaQuery()
                .eq(MemberReceiveAddressEntity::getMemberId, SecurityUtils.getCurrentUserId())
                .eq(MemberReceiveAddressEntity::getId, id).one();

        return BeanCopy.convert(memberReceiveAddressEntity, MemberReceiveAddressVo.class);
    }

    @Override
    public List<MemberReceiveAddressVo> selectAll() {
        List<MemberReceiveAddressEntity> memberReceiveAddressEntity = lambdaQuery()
                .eq(MemberReceiveAddressEntity::getMemberId, SecurityUtils.getCurrentUserId()).list();
        return BeanCopy.copytList(memberReceiveAddressEntity, MemberReceiveAddressVo.class);
    }
}

