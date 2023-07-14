package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.product.entity.ProductAttributeValueEntity;
import org.example.modules.product.entity.dto.ProductAttributeValueDto;
import org.example.modules.product.mapper.ProductAttributeValueMapper;
import org.example.modules.product.service.ProductAttributeValueService;

/**
 * Created by PanShiFu 2023-07-14 13:54:16
 *
 * @author PanShiFu
 * @date 2023-07-14 13:54:16
 * @Description 存储产品参数信息的表(ProductAttributeValue)表服务实现类
 */
@Service("productAttributeValueService")
public class ProductAttributeValueServiceImpl extends ServiceImpl<ProductAttributeValueMapper, ProductAttributeValueEntity> implements ProductAttributeValueService {
    @Override
    public boolean save(ProductAttributeValueDto productAttributeValue) {
        return false;
    }

    @Override
    public boolean updateById(ProductAttributeValueDto productAttributeValue) {
        return false;
    }
}

