package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.product.entity.SkuStockEntity;
import org.example.modules.product.entity.dto.SkuStockDto;
import org.example.modules.product.mapper.SkuStockMapper;
import org.example.modules.product.service.SkuStockService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean save(SkuStockDto skuStock) {
        return false;
    }

    @Override
    public boolean save(List<SkuStockDto> skuStock) {
        List<SkuStockEntity> skuStockEntityList = BeanCopy.copytList(skuStock, SkuStockEntity.class);
        // TODO 数据校验
        return saveBatch(skuStockEntityList);
    }

    @Override
    public boolean updateById(SkuStockDto skuStock) {
        return false;
    }

    @Override
    public List<SkuStockDto> getSkuStockByProductId(Long productId) {
        return null;
    }
}

