package org.example.modules.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.member.entity.MemberReadHistoryEntity;
import org.example.modules.member.entity.dto.MemberReadHistoryDto;
import org.example.modules.member.entity.vo.MemberReadHistoryVo;
import org.example.modules.member.mapper.MemberReadHistoryMapper;
import org.example.modules.member.service.MemberReadHistoryService;
import org.example.modules.product.entity.ProductEntity;
import org.example.modules.product.entity.vo.ProductVo;
import org.example.modules.product.serveice.ProductService;
import org.example.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.*;
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
        Long productId = memberReadHistoryDto.getProductId();
        ProductEntity productEntity = productService.getById(productId);
        if (Objects.isNull(productEntity)) {
            throw new BaseRequestException("商品信息错误");
        }
        memberReadHistoryDto.setMemberId(SecurityUtils.getCurrentUserId());
        MemberReadHistoryEntity memberReadHistoryEntity = new MemberReadHistoryEntity();
        memberReadHistoryEntity.setMemberId(SecurityUtils.getCurrentUserId());
        memberReadHistoryEntity.setProductId(productId);
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
        // 获取浏览历史
        Page<MemberReadHistoryEntity> memberReadHistoryEntityPage = page(page, memberReadHistoryEntityLambdaQueryWrapper);
        // 获取浏览历史
        IPage<MemberReadHistoryVo> memberReadHistoryEntityPageVoIpage = memberReadHistoryEntityPage.convert(memberReadHistory -> BeanCopy.convert(memberReadHistory, MemberReadHistoryVo.class));
        // 获取浏览历史
        List<MemberReadHistoryVo> memberReadHistoryVoList = memberReadHistoryEntityPageVoIpage.getRecords();
        // 校验是否为空
        if (CollectionUtils.isEmpty(memberReadHistoryVoList)) {
            return (Page<MemberReadHistoryVo>) memberReadHistoryEntityPageVoIpage;
        }
        // 获取到浏览的商品ids
        Set<Long> productIdList = memberReadHistoryVoList.stream().map(MemberReadHistoryVo::getProductId).collect(Collectors.toSet());
        // 获取到浏览的商品
        List<ProductVo> productVoList = productService.getByIdsInVerifyStatusAndPublishStatus(productIdList);
        // 校验是否为空
        if (CollectionUtils.isEmpty(productVoList)) {
            memberReadHistoryEntityPageVoIpage.setRecords(new ArrayList<>());
            return (Page<MemberReadHistoryVo>) memberReadHistoryEntityPageVoIpage;
        }
        // 对 商品进行排序
        Map<Long, ProductVo> longProductVoMap = longProductVoMap(productVoList);


//        List<MemberReadHistoryVo> memberReadHistoryVos = memberReadHistoryVoList.stream().map(memberReadHistoryVo -> {
//            // 获取商品id
//            Long productId = memberReadHistoryVo.getProductId();
//            // 判断 商品是否存在
//            if (longProductVoMap.containsKey(productId)) {
//                memberReadHistoryVo.setProduct(longProductVoMap.get(productId));
//                // 商品存在即返回  创建一个新的数组
//                return memberReadHistoryVo;
//            }
//            return null;
//        }).collect(Collectors.toList());

        List<MemberReadHistoryVo> memberReadHistoryVos = memberReadHistoryVoList.stream().filter(memberReadHistoryVo ->
                        longProductVoMap.containsKey(memberReadHistoryVo.getProductId())
                )
                .peek(memberReadHistoryVo -> memberReadHistoryVo.setProduct(longProductVoMap.get(memberReadHistoryVo.getProductId())))
                .collect(Collectors.toList());
        memberReadHistoryEntityPageVoIpage.setRecords(memberReadHistoryVos);

        return (Page<MemberReadHistoryVo>) memberReadHistoryEntityPageVoIpage;
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

