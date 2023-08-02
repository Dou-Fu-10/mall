package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.product.entity.SkuStockEntity;
import org.example.modules.product.entity.dto.SkuStockDto;
import org.example.modules.product.entity.vo.SkuStockVo;
import org.example.modules.product.mapper.SkuStockMapper;
import org.example.modules.product.service.SkuStockService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dou-Fu-10 2023-08-01 22:28:59
 *
 * @author Dou-Fu-10
 * @date 2023-08-01 22:28:59
 * @Description sku的库存(SkuStock)表服务实现类
 */
@Service("skuStockService")
public class SkuStockServiceImpl extends ServiceImpl<SkuStockMapper, SkuStockEntity> implements SkuStockService {
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
        List<SkuStockEntity> skuStockEntityList = lambdaQuery().eq(SkuStockEntity::getProductId, productId).list();
        return BeanCopy.copytList(skuStockEntityList, SkuStockVo.class);
    }
}

