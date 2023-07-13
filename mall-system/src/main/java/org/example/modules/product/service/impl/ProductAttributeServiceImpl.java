package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.product.entity.ProductAttributeEntity;
import org.example.modules.product.mapper.ProductAttributeMapper;
import org.example.modules.product.service.ProductAttributeService;

/**
 * Created by PanShiFu 2023-07-13 15:35:53
 *
 * @author PanShiFu
 * @date 2023-07-13 15:35:53
 * @Description 商品属性参数表(ProductAttribute)表服务实现类
 */
@Service("productAttributeService")
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttributeEntity> implements ProductAttributeService {

}

