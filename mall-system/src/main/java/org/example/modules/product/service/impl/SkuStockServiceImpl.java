package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.product.entity.SkuStockEntity;
import org.example.modules.product.mapper.SkuStockMapper;
import org.example.modules.product.service.SkuStockService;

/**
 * Created by PanShiFu 2023-07-13 15:36:00
 *
 * @author PanShiFu
 * @date 2023-07-13 15:36:00
 * @Description sku的库存(SkuStock)表服务实现类
 */
@Service("skuStockService")
public class SkuStockServiceImpl extends ServiceImpl<SkuStockMapper, SkuStockEntity> implements SkuStockService {

}

