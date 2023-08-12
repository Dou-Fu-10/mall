package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.SetUtils;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.server.MinioServer;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.product.entity.SkuStockEntity;
import org.example.modules.product.entity.dto.SkuStockDto;
import org.example.modules.product.entity.vo.ProductVo;
import org.example.modules.product.entity.vo.SkuStockVo;
import org.example.modules.product.mapper.SkuStockMapper;
import org.example.modules.product.service.ProductService;
import org.example.modules.product.service.SkuStockService;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-15 11:35:50
 *
 * @author Dou-Fu-10
 * @date 2023-07-15 11:35:50
 * @Description sku的库存(SkuStock)表服务实现类
 */
@Service("skuStockService")
public class SkuStockServiceImpl extends ServiceImpl<SkuStockMapper, SkuStockEntity> implements SkuStockService {
    @Resource
    private MinioServer minioServer;
    @Resource
    @Lazy
    private ProductService productService;

    @Override
    public Boolean save(SkuStockDto skuStock) {
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean save(List<SkuStockDto> skuStock) {
        List<SkuStockEntity> skuStockEntityList = BeanCopy.copytList(skuStock, SkuStockEntity.class);
        Set<String> picList = skuStockEntityList.stream().map(SkuStockEntity::getPic).collect(Collectors.toSet());
        Set<String> pics = minioServer.checkObjectIsExist(picList);
        if (!SetUtils.isEqualSet(picList, pics)) {
            throw new BaseRequestException("sku图添加失败");
        }
        Set<Long> productIdList = skuStockEntityList.stream().map(SkuStockEntity::getProductId).collect(Collectors.toSet());
        Set<Long> productIds = productService.getByIds(productIdList).stream().map(ProductVo::getId).collect(Collectors.toSet());
        if (!SetUtils.isEqualSet(productIds, productIdList)) {
            throw new BaseRequestException("请填写正确的商品id");
        }
        return saveBatch(skuStockEntityList);
    }

    @Override
    public Boolean updateById(SkuStockDto skuStock) {
        return false;
    }

    @Override
    public List<SkuStockVo> getSkuStockByProductId(Long productId) {
        List<SkuStockEntity> skuStockEntityList = lambdaQuery().eq(SkuStockEntity::getProductId, productId).list();
        return BeanCopy.copytList(skuStockEntityList, SkuStockVo.class);
    }

    @Override
    public List<SkuStockVo> getSkuStockByProductIds(Set<Long> productIds) {
        if (Objects.isNull(productIds)) {
            return new ArrayList<>();
        }
        List<SkuStockEntity> skuStockEntityList = lambdaQuery().in(SkuStockEntity::getProductId, productIds).list();
        return BeanCopy.copytList(skuStockEntityList, SkuStockVo.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean saveOrUpdate(List<SkuStockDto> skuStock) {
        List<SkuStockEntity> skuStockEntityList = BeanCopy.copytList(skuStock, SkuStockEntity.class);
        Set<String> picList = skuStockEntityList.stream().map(SkuStockEntity::getPic).collect(Collectors.toSet());
        Set<String> pics = minioServer.checkObjectIsExist(picList);
        if (!SetUtils.isEqualSet(picList, pics)) {
            throw new BaseRequestException("sku图添加失败");
        }
        Set<Long> productIdList = skuStockEntityList.stream().map(SkuStockEntity::getProductId).collect(Collectors.toSet());
        Set<Long> productIds = productService.getByIds(productIdList).stream().map(ProductVo::getId).collect(Collectors.toSet());
        if (!SetUtils.isEqualSet(productIds, productIdList)) {
            throw new BaseRequestException("请填写正确的商品id");
        }
        return saveOrUpdateBatch(skuStockEntityList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean updateBatchById(@NotNull Set<SkuStockDto> skuStock) {
        // 校验图片是否存在
        for (SkuStockDto skuStockDto : skuStock) {
            String pic = skuStockDto.getPic();
            if (minioServer.checkObjectIsExist(pic)) {
                skuStockDto.setPic(pic);
            }
        }
        Set<SkuStockEntity> skuStockEntities = BeanCopy.copySet(skuStock, SkuStockEntity.class);
        return updateBatchById(skuStockEntities);
    }

    @Override
    public Boolean removeByProductId(Long productId) {
        if (Objects.isNull(productId)) {
            return false;
        }
        return remove(lambdaQuery().eq(SkuStockEntity::getProductId, productId).getWrapper());
    }
}

