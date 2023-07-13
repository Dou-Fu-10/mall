package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.product.entity.ProductAttributeEntity;
import org.example.modules.product.mapper.ProductAttributeMapper;
import org.example.modules.product.service.ProductAttributeService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:53
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:53
 * @Description 商品属性参数表(ProductAttribute)表服务实现类
 */
@Service("productAttributeService")
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttributeEntity> implements ProductAttributeService {

}

