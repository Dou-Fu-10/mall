package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.product.entity.ProductAttributeCategoryEntity;
import org.example.modules.product.entity.dto.ProductAttributeCategoryDto;
import org.example.modules.product.mapper.ProductAttributeCategoryMapper;
import org.example.modules.product.service.ProductAttributeCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by PanShiFu 2023-07-14 11:03:43
 *
 * @author PanShiFu
 * @date 2023-07-14 11:03:43
 * @Description 产品属性分类表(ProductAttributeCategory)表服务实现类
 */
@Service("productAttributeCategoryService")
public class ProductAttributeCategoryServiceImpl extends ServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategoryEntity> implements ProductAttributeCategoryService {

    @Override
    public ProductAttributeCategoryEntity getByProductAttributeCategoryName(String productAttributeCategory) {
        return lambdaQuery().eq(ProductAttributeCategoryEntity::getName, productAttributeCategory).one();
    }

    @Override
    public ProductAttributeCategoryEntity getByProductAttributeCategoryId(Long id) {
        return lambdaQuery().eq(ProductAttributeCategoryEntity::getId, id).one();
    }

    @Override
    public Boolean save(ProductAttributeCategoryDto productAttributeCategory) {
        ProductAttributeCategoryEntity convert = BeanCopy.convert(productAttributeCategory, ProductAttributeCategoryEntity.class);
        if (Objects.nonNull(getByProductAttributeCategoryName(convert.getName()))) {
            throw new BaseRequestException("属性分类名不唯一");
        }

        return save(convert);
    }

    @Override
    public List<ProductAttributeCategoryEntity> getListWithAttr() {
        List<ProductAttributeCategoryEntity> listWithAttr = this.getBaseMapper().getListWithAttr();
        return null;
    }

    @Override
    public Boolean updateById(ProductAttributeCategoryDto productAttributeCategory) {
        ProductAttributeCategoryEntity convert = BeanCopy.convert(productAttributeCategory, ProductAttributeCategoryEntity.class);
        return updateById(convert);
    }
}

