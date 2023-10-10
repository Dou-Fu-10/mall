package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.member.entity.MemberCollectionEntity;
import org.example.modules.member.entity.dto.MemberCollectionDto;
import org.example.modules.member.entity.vo.MemberCollectionVo;
import org.example.modules.member.mapper.MemberCollectionMapper;
import org.example.modules.member.service.MemberCollectionService;
import org.example.modules.product.entity.vo.ProductVo;
import org.example.modules.product.serveice.ProductService;
import org.example.security.utils.SecurityUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-08-05 13:27:10
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 13:27:10
 * @Description 会员收藏(MemberCollection)表服务实现类
 */
@Service("memberCollectionService")
public class MemberCollectionServiceImpl extends ServiceImpl<MemberCollectionMapper, MemberCollectionEntity> implements MemberCollectionService {
    @Resource
    private ProductService productService;

    @Override
    public Boolean save(@NotNull MemberCollectionDto memberCollectionDto) {
        Long memberId = SecurityUtils.getCurrentUserId();
        Long productId = memberCollectionDto.getProductId();
        if (collectOrNot(productId)) {
            throw new BaseRequestException("重复收藏");
        }
        ProductVo byProductId = productService.getByProductId(productId);
        if (Objects.isNull(byProductId)) {
            throw new BaseRequestException("收藏失败");
        }
        memberCollectionDto.setMemberId(memberId);
        MemberCollectionEntity memberCollectionEntity = BeanCopy.convert(memberCollectionDto, MemberCollectionEntity.class);
        return save(memberCollectionEntity);
    }

    @Override
    public Boolean updateById(MemberCollectionDto memberCollectionDto) {
        MemberCollectionEntity memberCollectionEntity = BeanCopy.convert(memberCollectionDto, MemberCollectionEntity.class);
        return updateById(memberCollectionEntity);
    }

    @Override
    public Page<MemberCollectionVo> page(Page<MemberCollectionEntity> page) {
        Long memberId = SecurityUtils.getCurrentUserId();
        LambdaQueryWrapper<MemberCollectionEntity> memberCollectionEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 以会员id 为条件
        memberCollectionEntityLambdaQueryWrapper.eq(MemberCollectionEntity::getMemberId, memberId);
        // 以创建时间 来进行倒序
        memberCollectionEntityLambdaQueryWrapper.orderByDesc(MemberCollectionEntity::getCreateTime);
        // 获取到 收藏历史
        Page<MemberCollectionEntity> memberCollectionEntityPage = page(page, memberCollectionEntityLambdaQueryWrapper);
        // 获取到 收藏历史
        IPage<MemberCollectionVo> memberCollectionEntityPageVoIPage = memberCollectionEntityPage.convert(memberCollection -> BeanCopy.convert(memberCollection, MemberCollectionVo.class));
        // 获取到 收藏历史
        List<MemberCollectionVo> memberCollectionVoList = memberCollectionEntityPageVoIPage.getRecords();
        // 校验是否为空
        if (CollectionUtils.isEmpty(memberCollectionVoList)) {
            return (Page<MemberCollectionVo>) memberCollectionEntityPageVoIPage;
        }
        // 获取收藏的商品 id列表
        Set<Long> productIds = memberCollectionVoList.stream().map(MemberCollectionVo::getProductId).collect(Collectors.toSet());
        // 获取到商品
        List<ProductVo> productVoList = productService.getByProductIds(productIds);

        if (CollectionUtils.isEmpty(productVoList)) {
            return (Page<MemberCollectionVo>) memberCollectionEntityPageVoIPage;
        }
        MemberCollectionVo memberCollectionVo = new MemberCollectionVo();
        memberCollectionVo.setProductList(productVoList);
        memberCollectionVo.setMemberId(memberId);

        memberCollectionEntityPageVoIPage.setRecords(List.of(memberCollectionVo));


        return (Page<MemberCollectionVo>) memberCollectionEntityPageVoIPage;
    }

    @Override
    public Boolean collectOrNot(Serializable productId) {
        LambdaQueryWrapper<MemberCollectionEntity> memberCollectionEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 商品
        memberCollectionEntityLambdaQueryWrapper.eq(MemberCollectionEntity::getProductId, productId);
        // 用户
        memberCollectionEntityLambdaQueryWrapper.eq(MemberCollectionEntity::getMemberId, SecurityUtils.getCurrentUserId());
        MemberCollectionEntity memberCollectionEntity = getOne(memberCollectionEntityLambdaQueryWrapper);
        return Objects.nonNull(memberCollectionEntity);
    }

}

