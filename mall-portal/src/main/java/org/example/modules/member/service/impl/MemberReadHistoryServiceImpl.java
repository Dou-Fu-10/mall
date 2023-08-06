package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.member.entity.MemberReadHistoryEntity;
import org.example.modules.member.entity.dto.MemberReadHistoryDto;
import org.example.modules.member.entity.vo.MemberReadHistoryVo;
import org.example.modules.member.mapper.MemberReadHistoryMapper;
import org.example.modules.member.service.MemberReadHistoryService;
import org.example.modules.product.entity.vo.ProductVo;
import org.example.modules.product.serveice.ProductService;
import org.example.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-08-05 13:27:10
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 13:27:10
 * @Description 会员阅读历史记录(MemberReadHistory)表服务实现类
 */
@Service("memberReadHistoryService")
public class MemberReadHistoryServiceImpl extends ServiceImpl<MemberReadHistoryMapper, MemberReadHistoryEntity> implements MemberReadHistoryService {
    @Resource
    private ProductService productService;

    @Override
    public Boolean save(MemberReadHistoryDto memberReadHistoryDto) {
        memberReadHistoryDto.setMemberId(SecurityUtils.getCurrentUserId());
        MemberReadHistoryEntity memberReadHistoryEntity = BeanCopy.convert(memberReadHistoryDto, MemberReadHistoryEntity.class);
        return save(memberReadHistoryEntity);
    }

    @Override
    public Boolean updateById(MemberReadHistoryDto memberReadHistoryDto) {
        MemberReadHistoryEntity memberReadHistoryEntity = BeanCopy.convert(memberReadHistoryDto, MemberReadHistoryEntity.class);
        return updateById(memberReadHistoryEntity);
    }

    @Override
    public Page<MemberReadHistoryVo> page(Page<MemberReadHistoryEntity> page) {
        Long memberId = SecurityUtils.getCurrentUserId();
        LambdaQueryWrapper<MemberReadHistoryEntity> memberReadHistoryEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        memberReadHistoryEntityLambdaQueryWrapper.eq(MemberReadHistoryEntity::getMemberId, memberId);
        Page<MemberReadHistoryEntity> memberReadHistoryEntityPage = page(page, memberReadHistoryEntityLambdaQueryWrapper);
        IPage<MemberReadHistoryVo> memberReadHistoryEntityPageVoIpage = memberReadHistoryEntityPage.convert(memberReadHistory -> BeanCopy.convert(memberReadHistory, MemberReadHistoryVo.class));

        List<MemberReadHistoryVo> memberReadHistoryVoList = memberReadHistoryEntityPageVoIpage.getRecords();

        if (CollectionUtils.isEmpty(memberReadHistoryVoList)) {
            return (Page) memberReadHistoryEntityPageVoIpage;
        }

        Set<Long> productIdList = memberReadHistoryVoList.stream().map(MemberReadHistoryVo::getProductId).collect(Collectors.toSet());

        List<ProductVo> productVoList = productService.getByIdsInVerifyStatusAndPublishStatus(productIdList);
        if (CollectionUtils.isEmpty(productVoList)) {
            return (Page) memberReadHistoryEntityPageVoIpage;
        }
        Map<Long, ProductVo> longProductVoMap = longProductVoMap(productVoList);

        memberReadHistoryVoList.forEach(memberReadHistoryVo -> {
            Long productId = memberReadHistoryVo.getProductId();
            if (longProductVoMap.containsKey(productId)) {
                memberReadHistoryVo.setProduct(longProductVoMap.get(productId));
            }
        });

        return (Page) memberReadHistoryEntityPageVoIpage;
    }

    private Map<Long, ProductVo> longProductVoMap(List<ProductVo> productVoList) {
        Map<Long, ProductVo> longProductVoMap = new HashMap<>();
        for (ProductVo productVo : productVoList) {
            Long productVoId = productVo.getId();
            if (!longProductVoMap.containsKey(productVoId)) {
                longProductVoMap.put(productVoId, productVo);
            }
        }
        return longProductVoMap;
    }
}

