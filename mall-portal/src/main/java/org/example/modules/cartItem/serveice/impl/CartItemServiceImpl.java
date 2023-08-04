package org.example.modules.cartItem.serveice.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.collections4.SetUtils;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.cartItem.entity.CartItemEntity;
import org.example.modules.cartItem.entity.dto.CartItemDto;
import org.example.modules.cartItem.entity.vo.CartItemVo;
import org.example.modules.cartItem.mapper.CartItemMapper;
import org.example.modules.cartItem.serveice.CartItemService;
import org.example.modules.product.entity.ProductEntity;
import org.example.modules.product.serveice.ProductService;
import org.example.security.entity.JwtMember;
import org.example.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-14 14:35:11
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:35:11
 * @Description 购物车表(CartItem)表服务实现类
 */
@Service("cartItemService")
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItemEntity> implements CartItemService {
    @Resource
    private ProductService productService;

    @Override
    public Boolean save(CartItemDto cartItem) {
        JwtMember currentUser = (JwtMember) SecurityUtils.getCurrentUser();
        CartItemEntity cartItemEntity = BeanCopy.convert(cartItem, CartItemEntity.class);
        cartItemEntity.setMemberId(currentUser.getUser().getId());
        cartItemEntity.setMemberNickname(currentUser.getUser().getNickname());
        return cartItemEntity.insert();
    }

    @Override
    public Boolean updateById(CartItemDto cartItem) {
        CartItemEntity cartItemEntity = BeanCopy.convert(cartItem, CartItemEntity.class);
        return cartItemEntity.updateById();
    }

    @Override
    public List<CartItemVo> getCartItemByMemberIdAndCartIds(Long memberId, List<Long> cartIds) {
        // 获取购物车里的信息
        List<CartItemEntity> cartItemEntityList = lambdaQuery().eq(CartItemEntity::getMemberId, memberId).in(CartItemEntity::getId, cartIds).list();
        if (CollectionUtils.isEmpty(cartItemEntityList)) {
            throw new BaseRequestException("购物车信息为空");
        }
        List<CartItemVo> cartItemVoList = BeanCopy.copytList(cartItemEntityList, CartItemVo.class);
        // 获取商品id
        Set<Long> productIdList = cartItemVoList.stream().map(CartItemVo::getProductId).collect(Collectors.toSet());
        // 获取商品信息
        List<ProductEntity> productEntityList = productService.listByIds(productIdList);
        // 如果商品为空说明商品下架
        if (CollectionUtils.isEmpty(productEntityList)) {
            throw new BaseRequestException("商品已下架");
        }
        // 获取 商品id
        Set<Long> productIds = productEntityList.stream().map(ProductEntity::getId).collect(Collectors.toSet());
        // 判断 商品id列表和 购物车里的商品是否相当
        if (!SetUtils.isEqualSet(productIds, productIdList)) {
            // 不相等说明有商品缺货
            List<String> stringList = new ArrayList<>();
            // 获取缺货的商品
            List<Long> list = org.apache.commons.collections4.CollectionUtils.subtract(productIdList, productIds).stream().toList();
            for (Long id : list) {
                for (CartItemVo cartItemVo : cartItemVoList) {
                    if (cartItemVo.getId().equals(id)) {
                        // 将缺货的商品添加到数组里面
                        stringList.add(cartItemVo.getProductName());
                    }
                }
            }
            // 将信息进行报错回显
            throw new BaseRequestException(stringList + "缺货中");
        }
        // TODO 待定库存信息
        cartItemVoList.forEach(cartItemVo -> productEntityList.forEach(productEntity -> {
            if (productEntity.getId().equals(cartItemVo.getProductId())) {
                cartItemVo.setRealStock(productEntity.getStock());
            }
        }));

        return cartItemVoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean removeBatchByIdsAndMemberId(@NotNull Set<Long> cartItemIds,@NotNull Long memberId) {
        List<CartItemEntity> collect = cartItemIds.stream().map(id -> new CartItemEntity(id, memberId)).toList();
        return removeBatchByIds(collect);
    }
}

