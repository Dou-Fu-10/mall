package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.member.entity.MemberPriceEntity;
import org.springframework.stereotype.Service;
import org.example.modules.product.entity.ProductAttributeValueEntity;
import org.example.modules.product.entity.dto.ProductAttributeValueDto;
import org.example.modules.product.mapper.ProductAttributeValueMapper;
import org.example.modules.product.service.ProductAttributeValueService;

import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-14 13:54:16
 *
 * @author Dou-Fu-10
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
    public boolean save(List<ProductAttributeValueDto> productAttributeValue) {
        List<ProductAttributeValueEntity> productAttributeValueEntityList = BeanCopy.copytList(productAttributeValue, ProductAttributeValueEntity.class);
        // TODO 数据校验
        return saveBatch(productAttributeValueEntityList);
    }

    @Override
    public boolean updateById(ProductAttributeValueDto productAttributeValue) {
        return false;
    }

    @Override
    public List<ProductAttributeValueDto> getProductAttributeValueByProductId(Long productId) {
        return null;
    }
}

