package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.server.MinioServer;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.product.entity.SkuStockEntity;
import org.example.modules.product.entity.dto.SkuStockDto;
import org.example.modules.product.entity.vo.SkuStockVo;
import org.example.modules.product.mapper.SkuStockMapper;
import org.example.modules.product.service.SkuStockService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @Override
    public Boolean save(SkuStockDto skuStock) {
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean save(List<SkuStockDto> skuStock) {
        List<SkuStockEntity> skuStockEntityList = BeanCopy.copytList(skuStock, SkuStockEntity.class);
        // TODO 数据校验
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
        // TODO 数据校验
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

