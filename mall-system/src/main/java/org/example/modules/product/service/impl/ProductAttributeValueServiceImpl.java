package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.product.entity.ProductAttributeValueEntity;
import org.example.modules.product.mapper.ProductAttributeValueMapper;
import org.example.modules.product.service.ProductAttributeValueService;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:54
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:54
 * @Description 存储产品参数信息的表(ProductAttributeValue)表服务实现类
 */
@Service("productAttributeValueService")
public class ProductAttributeValueServiceImpl extends ServiceImpl<ProductAttributeValueMapper, ProductAttributeValueEntity> implements ProductAttributeValueService {

}

