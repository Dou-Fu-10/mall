package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.member.entity.MemberCollectionEntity;
import org.example.modules.member.entity.dto.MemberCollectionDto;
import org.example.modules.member.entity.vo.MemberCollectionVo;
import org.example.modules.member.mapper.MemberCollectionMapper;
import org.example.modules.member.service.MemberCollectionService;
import org.example.modules.product.entity.vo.ProductVo;
import org.example.modules.product.serveice.ProductService;
import org.example.security.utils.SecurityUtils;
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
    public Page<MemberCollectionVo> page(Page<MemberCollectionEntity> page) {
        Long memberId = SecurityUtils.getCurrentUserId();
        QueryWrapper<MemberCollectionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT product_id").lambda()
                .eq(MemberCollectionEntity::getMemberId, memberId);
        Page<MemberCollectionEntity> memberCollectionEntityPage = page(page, queryWrapper);

        IPage<MemberCollectionVo> memberCollectionEntityPageVoIpage = memberCollectionEntityPage.convert(memberCollection -> BeanCopy.convert(memberCollection, MemberCollectionVo.class));
        // 获取到 收藏历史
        List<MemberCollectionVo> memberCollectionVoList = memberCollectionEntityPageVoIpage.getRecords();

        if (CollectionUtils.isEmpty(memberCollectionVoList)) {
            return (Page) memberCollectionEntityPageVoIpage;
        }
        // 获取商品 id列表
        Set<Long> productIds = memberCollectionVoList.stream().map(MemberCollectionVo::getProductId).collect(Collectors.toSet());
        // 获取到商品
        List<ProductVo> productVoList = productService.getByIdsInVerifyStatusAndPublishStatus(productIds);

        if (CollectionUtils.isEmpty(productVoList)) {
            return (Page) memberCollectionEntityPageVoIpage;
        }
        MemberCollectionVo memberCollectionVo = new MemberCollectionVo();
        memberCollectionVo.setProductList(productVoList);
        memberCollectionVo.setMemberId(memberId);

        memberCollectionEntityPageVoIpage.setRecords(List.of(memberCollectionVo));


        return (Page) memberCollectionEntityPageVoIpage;
    }

    @Override
    public Boolean collectOrNot(Serializable productId) {
        LambdaQueryWrapper<MemberCollectionEntity> memberCollectionEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        memberCollectionEntityLambdaQueryWrapper.eq(MemberCollectionEntity::getProductId, productId);
        memberCollectionEntityLambdaQueryWrapper.eq(MemberCollectionEntity::getMemberId, SecurityUtils.getCurrentUserId());
        MemberCollectionEntity memberCollectionEntity = getOne(memberCollectionEntityLambdaQueryWrapper);
        return Objects.nonNull(memberCollectionEntity);
    }

}
