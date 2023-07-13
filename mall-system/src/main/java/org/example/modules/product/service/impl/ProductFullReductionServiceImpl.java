package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.product.entity.ProductFullReductionEntity;
import org.example.modules.product.mapper.ProductFullReductionMapper;
import org.example.modules.product.service.ProductFullReductionService;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:56
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:56
 * @Description 产品满减表(只针对同商品)(ProductFullReduction)表服务实现类
 */
@Service("productFullReductionService")
public class ProductFullReductionServiceImpl extends ServiceImpl<ProductFullReductionMapper, ProductFullReductionEntity> implements ProductFullReductionService {

}

