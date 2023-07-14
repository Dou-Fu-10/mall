package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.product.entity.ProductCategoryAttributeRelationEntity;
import org.example.modules.product.entity.dto.ProductCategoryAttributeRelationDto;
import org.example.modules.product.mapper.ProductCategoryAttributeRelationMapper;
import org.example.modules.product.service.ProductCategoryAttributeRelationService;

/**
 * Created by PanShiFu 2023-07-14 13:54:17
 *
 * @author PanShiFu
 * @date 2023-07-14 13:54:17
 * @Description 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）(ProductCategoryAttributeRelation)表服务实现类
 */
@Service("productCategoryAttributeRelationService")
public class ProductCategoryAttributeRelationServiceImpl extends ServiceImpl<ProductCategoryAttributeRelationMapper, ProductCategoryAttributeRelationEntity> implements ProductCategoryAttributeRelationService {
    @Override
    public boolean save(ProductCategoryAttributeRelationDto productCategoryAttributeRelation) {
        return false;
    }

    @Override
    public boolean updateById(ProductCategoryAttributeRelationDto productCategoryAttributeRelation) {
        return false;
    }
}

