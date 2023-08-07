package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.order.entity.vo.OrderItemVo;
import org.example.modules.product.entity.ProductEntity;
import org.example.modules.product.entity.SkuStockEntity;
import org.example.modules.product.entity.dto.SkuStockDto;
import org.example.modules.product.entity.vo.SkuStockVo;
import org.example.modules.product.mapper.SkuStockMapper;
import org.example.modules.product.serveice.ProductService;
import org.example.modules.product.service.SkuStockService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-08-01 22:28:59
 *
 * @author Dou-Fu-10
 * @date 2023-08-01 22:28:59
 * @Description sku的库存(SkuStock)表服务实现类
 */
@Service("skuStockService")
public class SkuStockServiceImpl extends ServiceImpl<SkuStockMapper, SkuStockEntity> implements SkuStockService {
    @Resource
    @Lazy
    private ProductService productService;

    @Override
    public Boolean save(SkuStockDto skuStockDto) {
        SkuStockEntity skuStockEntity = BeanCopy.convert(skuStockDto, SkuStockEntity.class);
        return save(skuStockEntity);
    }

    @Override
    public Boolean updateById(SkuStockDto skuStockDto) {
        SkuStockEntity skuStockEntity = BeanCopy.convert(skuStockDto, SkuStockEntity.class);
        return updateById(skuStockEntity);
    }

    @Override
    public Page<SkuStockVo> page(Page<SkuStockEntity> page, SkuStockDto skuStockDto) {
        SkuStockEntity skuStockEntity = BeanCopy.convert(skuStockDto, SkuStockEntity.class);
        LambdaQueryWrapper<SkuStockEntity> skuStockEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(skuStockEntity);
        Page<SkuStockEntity> skuStockEntityPage = page(page, skuStockEntityLambdaQueryWrapper);
        IPage<SkuStockVo> skuStockEntityPageVoIpage = skuStockEntityPage.convert(skuStock -> BeanCopy.convert(skuStock, SkuStockVo.class));
        return (Page) skuStockEntityPageVoIpage;
    }

    @Override
    public List<SkuStockVo> getSkuStockByProductId(Long productId) {
        if (Objects.isNull(productId)) {
            throw new BaseRequestException("参数有误");
        }
        List<SkuStockEntity> skuStockEntityList = lambdaQuery().eq(SkuStockEntity::getProductId, productId).list();
        return BeanCopy.copytList(skuStockEntityList, SkuStockVo.class);
    }

    @Override
    public SkuStockVo getByIdAndProductId(Long productSkuId, Long productId) {
        // 获取商品
        ProductEntity productEntity = productService.getById(productId);
        // 确保商品不为空
        if (Objects.isNull(productEntity)) {
            throw new BaseRequestException("请正确的填写商品数据");
        }
        SkuStockEntity skuStockEntity = new SkuStockEntity();
        skuStockEntity.setId(productSkuId);
        skuStockEntity.setProductId(productEntity.getId());
        // 获取sku
        SkuStockEntity stockEntity = getOne(new QueryWrapper<>(skuStockEntity));
        // 确保sku不为空
        if (Objects.isNull(stockEntity)) {
            throw new BaseRequestException("请正确的填写商品数据");
        }
        SkuStockVo skuStockVo = BeanCopy.convert(stockEntity, SkuStockVo.class);
        skuStockVo.setProduct(productEntity);
        return skuStockVo;
    }

    @Override
    public Boolean releaseSkuStockLock(List<OrderItemVo> orderItemVoList) {
        Set<Long> productIds = orderItemVoList.stream().map(OrderItemVo::getProductId).collect(Collectors.toSet());
        return releaseSkuStockLock(productIds);
    }

    @Override
    public Boolean releaseSkuStockLock(Set<Long> productIds) {
        // TODO 解除取消订单的库存锁定
        return false;
    }

    @Override
    public List<SkuStockVo> getSkuStockByProductIds(Set<Long> productIds) {
        if (Objects.isNull(productIds)) {
            return new ArrayList<>();
        }
        List<SkuStockEntity> skuStockEntityList = lambdaQuery().in(SkuStockEntity::getProductId, productIds).list();
        return BeanCopy.copytList(skuStockEntityList, SkuStockVo.class);
    }
}

