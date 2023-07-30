package org.example.modules.admin.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.admin.product.entity.SkuStockEntity;
import org.example.modules.admin.product.entity.dto.SkuStockDto;
import org.example.modules.admin.product.entity.vo.SkuStockVo;
import org.example.modules.admin.product.mapper.SkuStockMapper;
import org.example.modules.admin.product.service.SkuStockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean saveOrUpdate(List<SkuStockDto> skuStock) {
        List<SkuStockEntity> skuStockEntityList = BeanCopy.copytList(skuStock, SkuStockEntity.class);
        // TODO 数据校验
        return saveOrUpdateBatch(skuStockEntityList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean updateBatchById(Set<SkuStockDto> skuStock) {
        Set<SkuStockEntity> skuStockEntities = BeanCopy.copySet(skuStock, SkuStockEntity.class);
        return updateBatchById(skuStockEntities);
    }
}

