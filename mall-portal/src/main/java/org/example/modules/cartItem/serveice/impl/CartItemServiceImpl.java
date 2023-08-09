package org.example.modules.cartItem.serveice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.SetUtils;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.cartItem.entity.CartItemEntity;
import org.example.modules.cartItem.entity.dto.AddCartItemDto;
import org.example.modules.cartItem.entity.dto.CartItemDto;
import org.example.modules.cartItem.entity.vo.CartItemVo;
import org.example.modules.cartItem.mapper.CartItemMapper;
import org.example.modules.cartItem.serveice.CartItemService;
import org.example.modules.product.entity.ProductEntity;
import org.example.modules.product.entity.vo.ProductVo;
import org.example.modules.product.entity.vo.SkuStockVo;
import org.example.modules.product.serveice.ProductService;
import org.example.modules.product.service.SkuStockService;
import org.example.security.entity.JwtMember;
import org.example.security.utils.SecurityUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
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
    @Resource
    private SkuStockService skuStockService;

    @Override
    public Boolean save(AddCartItemDto cartItem) {
        // 购买数量
        Integer quantity = cartItem.getQuantity();
        if (quantity < 1) {
            throw new BaseRequestException("请正确的填写购买数量");
        }
        CartItemEntity cartItemEntity = new CartItemEntity();
        // 获取用户信息
        JwtMember currentUser = (JwtMember) SecurityUtils.getCurrentUser();
        if (Objects.isNull(currentUser)) {
            throw new BaseRequestException("登录信息错误");
        }
        // 获取用户下但的sku  犯法已经做判断能
        SkuStockVo skuStockVo = skuStockService.getByIdAndProductId(cartItem.getProductSkuId(), cartItem.getProductId());
        if (Objects.isNull(skuStockVo)) {
            throw new BaseRequestException("请正确的填写商品数据");
        }
        // 判断 购买数量是否在范围之内
        if (skuStockVo.getStock() < quantity) {
            throw new BaseRequestException("请正确的填写购买数量");
        }
        ProductEntity productEntity = skuStockVo.getProduct();
        // 设置商品id
        cartItemEntity.setProductId(productEntity.getId());
        // 商品SKUid
        cartItemEntity.setProductSkuId(skuStockVo.getId());
        // 商品分类
        cartItemEntity.setProductCategoryId(productEntity.getProductCategoryId());
        // 设置用户id
        cartItemEntity.setMemberId(currentUser.getUser().getId());
        // 设置用户昵称
        cartItemEntity.setMemberNickname(currentUser.getUser().getNickname());
        // 商品编码
        cartItemEntity.setProductSn(productEntity.getProductSn());
        // 购买数量
        cartItemEntity.setQuantity(quantity);
        // 单个商品的价格
        BigDecimal price = skuStockVo.getPrice();
        // 添加到购物车的价格   数量承单价
        cartItemEntity.setPrice(price);
        return cartItemEntity.insert();
    }

    @Override
    public Boolean updateById(CartItemDto cartItem) {
        CartItemEntity cartItemEntity = BeanCopy.convert(cartItem, CartItemEntity.class);
        return cartItemEntity.updateById();
    }

    @Override
    public List<CartItemVo> getCartItemByMemberIdAndCartIds(Long memberId, Set<Long> cartIds) {
        if (Objects.isNull(memberId) || CollectionUtils.isEmpty(cartIds)) {
            throw new BaseRequestException("参数有误");
        }
        // 获取购物车里的信息
        List<CartItemEntity> cartItemEntityList = lambdaQuery().eq(CartItemEntity::getMemberId, memberId).in(CartItemEntity::getId, cartIds).list();
        if (CollectionUtils.isEmpty(cartItemEntityList)) {
            throw new BaseRequestException("购物车信息为空");
        }
        List<CartItemVo> cartItemVoList = BeanCopy.copytList(cartItemEntityList, CartItemVo.class);
        // 获取购物车里的商品id
        Set<Long> productIdList = cartItemVoList.stream().map(CartItemVo::getProductId).collect(Collectors.toSet());
        // 获取商品信息
        List<ProductVo> productVoList = productService.getByProductIds(productIdList);
        // 如果商品为空说明商品下架
        if (CollectionUtils.isEmpty(productVoList)) {
            throw new BaseRequestException("商品已下架");
        }
        // 获取 商品id
        Set<Long> productIds = productVoList.stream().map(ProductVo::getId).collect(Collectors.toSet());
        // 判断 (商品id列表)和(购物车)里的(商品id列表)是否相当   不相等 就显示缺货中
        if (!SetUtils.isEqualSet(productIds, productIdList)) {
            // 不相等说明有商品缺货
            List<String> stringList = new ArrayList<>();
            // 获取缺货的商品
            List<Long> list = org.apache.commons.collections4.CollectionUtils.subtract(productIdList, productIds).stream().toList();
            for (Long id : list) {
                // 循环商品列表
                for (ProductVo productVo : productVoList) {
                    // 获取到缺货商品
                    if (productVo.getId().equals(id)) {
                        // 将缺货的商品添加到数组里面
                        stringList.add(productVo.getName());
                    }
                }
            }
            // 将信息进行报错回显
            throw new BaseRequestException(StringUtils.collectionToCommaDelimitedString(stringList) + "缺货中");
        }
        // 已经确保 购物车的商品 和 正在出售的商品一致
        Map<Long, ProductVo> longProductVoMap = longProductVoMap(productVoList);
        Set<Long> productSkuIds = cartItemVoList.stream().map(CartItemVo::getProductSkuId).collect(Collectors.toSet());
        // 获取商品属性
        List<SkuStockVo> skuStockVoList = skuStockService.getByIds(productSkuIds);
        // 将商品属性进行转换
        Map<Long, SkuStockVo> longSkuStockVoMap = longSkuStockVoMap(skuStockVoList);
        // 循环购物车
        cartItemVoList.forEach(cartItemVo -> {
            // 获取购物车的商品id
            Long productId = cartItemVo.getProductId();
            Long productSkuId = cartItemVo.getProductSkuId();
            if (longProductVoMap.containsKey(productId)) {
                // 获取购物车的商品id
                ProductVo productVo = longProductVoMap.get(productId);
                cartItemVo.setRealStock(productVo.getStock());
                cartItemVo.setProduct(productVo);
                cartItemVo.setSkuStock(longSkuStockVoMap.get(productSkuId));
            }
        });

        return cartItemVoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean removeBatchByIdsAndMemberId(Set<Long> cartItemIds, Long memberId) {
        if (CollectionUtils.isEmpty(cartItemIds) || Objects.isNull(memberId)) {
            throw new BaseRequestException("下单失败");
        }
        List<CartItemEntity> collect = cartItemIds.stream().map(id -> new CartItemEntity(id, memberId)).toList();
        return removeBatchByIds(collect);
    }

    @Override
    public Page<CartItemVo> page(Page<CartItemEntity> page) {
        CartItemEntity cartItemEntity = new CartItemEntity();
        // 设置 会员
        cartItemEntity.setMemberId(SecurityUtils.getCurrentUserId());
        Page<CartItemEntity> cartItemEntityPage = page(page, new QueryWrapper<>(cartItemEntity));
        // 获取购物车列表
        IPage<CartItemVo> cartItemVoIpage = cartItemEntityPage.convert(cartItem -> BeanCopy.convert(cartItem, CartItemVo.class));
        List<CartItemVo> cartItemVoList = cartItemVoIpage.getRecords();
        if (CollectionUtils.isEmpty(cartItemVoList)) {
            return (Page<CartItemVo>) cartItemVoIpage;
        }
        // 获取商品id
        Set<Long> productIds = cartItemVoList.stream().map(CartItemVo::getProductId).collect(Collectors.toSet());
        Set<Long> productSkuIds = cartItemVoList.stream().map(CartItemVo::getProductSkuId).collect(Collectors.toSet());
        // 获取购物的商品
        List<ProductVo> productVoList = productService.getByProductIds(productIds);

        // 将商品进行转换
        Map<Long, ProductVo> longProductVoMap = longProductVoMap(productVoList);
        // 获取商品属性
        List<SkuStockVo> skuStockVoList = skuStockService.getByIds(productSkuIds);
        // 将商品属性进行转换
        Map<Long, SkuStockVo> longSkuStockVoMap = longSkuStockVoMap(skuStockVoList);
        cartItemVoList.forEach(cartItemVo -> {
            // 获取购物车的商品
            Long productId = cartItemVo.getProductId();
            Long productSkuId = cartItemVo.getProductSkuId();
            if (longProductVoMap.containsKey(productId)) {
                // 将商品添入购物车
                cartItemVo.setProduct(longProductVoMap.get(productId));
                cartItemVo.setSkuStock(longSkuStockVoMap.get(productSkuId));
            }
        });
        cartItemVoIpage.setRecords(cartItemVoList);

        return (Page<CartItemVo>) cartItemVoIpage;
    }


    @NotNull
    private Map<Long, ProductVo> longProductVoMap(List<ProductVo> productVoList) {
        if (CollectionUtils.isEmpty(productVoList)) {
            return new HashMap<>();
        }
        Map<Long, ProductVo> longListMap = new HashMap<>();
        productVoList.forEach(productVo -> longListMap.put(productVo.getId(), productVo));
        return longListMap;
    }

    @NotNull
    private Map<Long, SkuStockVo> longSkuStockVoMap(List<SkuStockVo> skuStockVoList) {
        if (CollectionUtils.isEmpty(skuStockVoList)) {
            return new HashMap<>();
        }
        Map<Long, SkuStockVo> longListMap = new HashMap<>();
        skuStockVoList.forEach(productVo -> longListMap.put(productVo.getId(), productVo));
        return longListMap;
    }

    @Override
    public Boolean modifiedQuantity(Long cartItemId, Integer quantity) {
        // 校验数据范围
        if (Objects.isNull(cartItemId) || Objects.isNull(quantity) || quantity < 1) {
            return false;
        }
        CartItemEntity cartItemEntity = lambdaQuery()
                .eq(CartItemEntity::getId, cartItemId)
                .eq(CartItemEntity::getMemberId, SecurityUtils.getCurrentUserId()).one();
        // 校验数据
        if (Objects.isNull(cartItemEntity)) {
            return false;
        }
        // 校验库存
        SkuStockVo skuStockVo = skuStockService.getByIdAndProductId(cartItemEntity.getProductSkuId(), cartItemEntity.getProductId());
        Integer stock = skuStockVo.getStock();
        if (quantity >= stock) {
            throw new BaseRequestException("库存不足");
        }
        cartItemEntity.setQuantity(quantity);
        return updateById(cartItemEntity);
    }
}

