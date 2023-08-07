package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.product.entity.ProductAttributeValueEntity;
import org.example.modules.product.entity.dto.ProductAttributeValueDto;
import org.example.modules.product.entity.vo.ProductAttributeValueVo;
import org.example.modules.product.mapper.ProductAttributeValueMapper;
import org.example.modules.product.service.ProductAttributeValueService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-08-01 22:28:59
 *
 * @author Dou-Fu-10
 * @date 2023-08-01 22:28:59
 * @Description 存储产品参数信息的表(ProductAttributeValue)表服务实现类
 */
@Service("productAttributeValueService")
public class ProductAttributeValueServiceImpl extends ServiceImpl<ProductAttributeValueMapper, ProductAttributeValueEntity> implements ProductAttributeValueService {
    @Override
    public Boolean save(ProductAttributeValueDto productAttributeValueDto) {
        ProductAttributeValueEntity productAttributeValueEntity = BeanCopy.convert(productAttributeValueDto, ProductAttributeValueEntity.class);
        return save(productAttributeValueEntity);
    }

    @Override
    public Boolean updateById(ProductAttributeValueDto productAttributeValueDto) {
        ProductAttributeValueEntity productAttributeValueEntity = BeanCopy.convert(productAttributeValueDto, ProductAttributeValueEntity.class);
        return updateById(productAttributeValueEntity);
    }

    @Override
    public Page<ProductAttributeValueVo> page(Page<ProductAttributeValueEntity> page, ProductAttributeValueDto productAttributeValueDto) {
        ProductAttributeValueEntity productAttributeValueEntity = BeanCopy.convert(productAttributeValueDto, ProductAttributeValueEntity.class);
        LambdaQueryWrapper<ProductAttributeValueEntity> productAttributeValueEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(productAttributeValueEntity);
        Page<ProductAttributeValueEntity> productAttributeValueEntityPage = page(page, productAttributeValueEntityLambdaQueryWrapper);
        IPage<ProductAttributeValueVo> productAttributeValueEntityPageVoIpage = productAttributeValueEntityPage.convert(productAttributeValue -> BeanCopy.convert(productAttributeValue, ProductAttributeValueVo.class));
        return (Page) productAttributeValueEntityPageVoIpage;
    }

    @Override
    public List<ProductAttributeValueVo> getProductAttributeValueByProductId(Long productId) {
        List<ProductAttributeValueEntity> productAttributeValueEntityList = lambdaQuery().eq(ProductAttributeValueEntity::getProductId, productId).list();
        return BeanCopy.copytList(productAttributeValueEntityList, ProductAttributeValueVo.class);
    }
    @Override
    public List<ProductAttributeValueVo> getByProductAttributeIds(Set<Long> productIds) {
        if (Objects.isNull(productIds)) {
            return new ArrayList<>();
        }
        List<ProductAttributeValueEntity> productAttributeValueEntityList = lambdaQuery().in(ProductAttributeValueEntity::getProductId, productIds).list();
        return BeanCopy.copytList(productAttributeValueEntityList, ProductAttributeValueVo.class);
    }
}

