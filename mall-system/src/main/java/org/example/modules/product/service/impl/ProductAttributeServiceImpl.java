package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.product.entity.ProductAttributeEntity;
import org.example.modules.product.entity.dto.ProductAttributeDto;
import org.example.modules.product.mapper.ProductAttributeMapper;
import org.example.modules.product.service.ProductAttributeService;
import org.springframework.stereotype.Service;

/**
 * Created by PanShiFu 2023-07-14 12:49:52
 *
 * @author PanShiFu
 * @date 2023-07-14 12:49:52
 * @Description 商品属性参数表(ProductAttribute)表服务实现类
 */
@Service("productAttributeService")
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttributeEntity> implements ProductAttributeService {

    @Override
    public Boolean save(ProductAttributeDto productAttribute) {
        ProductAttributeEntity convert = BeanCopy.convert(productAttribute, ProductAttributeEntity.class);
        // TODO 校验数据
        return save(convert);
    }

    @Override
    public boolean updateById(ProductAttributeDto productAttribute) {
        ProductAttributeEntity convert = BeanCopy.convert(productAttribute, ProductAttributeEntity.class);
        // TODO 校验数据
        return updateById(convert);
    }
}

