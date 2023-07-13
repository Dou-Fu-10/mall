package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.product.entity.ProductCategoryAttributeRelationEntity;
import org.example.modules.product.mapper.ProductCategoryAttributeRelationMapper;
import org.example.modules.product.service.ProductCategoryAttributeRelationService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:55
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:55
 * @Description 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）(ProductCategoryAttributeRelation)表服务实现类
 */
@Service("productCategoryAttributeRelationService")
public class ProductCategoryAttributeRelationServiceImpl extends ServiceImpl<ProductCategoryAttributeRelationMapper, ProductCategoryAttributeRelationEntity> implements ProductCategoryAttributeRelationService {

}
