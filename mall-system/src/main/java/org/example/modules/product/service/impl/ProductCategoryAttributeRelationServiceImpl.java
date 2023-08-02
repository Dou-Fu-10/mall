package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.modules.product.entity.ProductAttributeEntity;
import org.example.modules.product.entity.ProductCategoryAttributeRelationEntity;
import org.example.modules.product.entity.dto.ProductCategoryAttributeRelationDto;
import org.example.modules.product.mapper.ProductCategoryAttributeRelationMapper;
import org.example.modules.product.service.ProductAttributeService;
import org.example.modules.product.service.ProductCategoryAttributeRelationService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-14 13:54:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:54:17
 * @Description 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）(ProductCategoryAttributeRelation)表服务实现类
 */
@Service("productCategoryAttributeRelationService")
public class ProductCategoryAttributeRelationServiceImpl extends ServiceImpl<ProductCategoryAttributeRelationMapper, ProductCategoryAttributeRelationEntity> implements ProductCategoryAttributeRelationService {
    @Resource
    private ProductAttributeService productAttributeService;

    @Override
    public Boolean save(ProductCategoryAttributeRelationDto productCategoryAttributeRelation) {
        return false;
    }

    @Override
    public Boolean updateById(ProductCategoryAttributeRelationDto productCategoryAttributeRelation) {
        return false;
    }

    @Override
    @SuppressWarnings("all")
    public Boolean save(Long id, Set<Long> productAttributeIdList) {
        // 获取有效的商品属性id列表
        productAttributeIdList = productAttributeService.getBaseMapper().selectBatchIds(productAttributeIdList).stream().map(ProductAttributeEntity::getId).collect(Collectors.toSet());
        // 获取商品分类和商品属性以及绑定的
        Set<Long> collect = lambdaQuery().in(ProductCategoryAttributeRelationEntity::getProductAttributeId, productAttributeIdList)
                .eq(ProductCategoryAttributeRelationEntity::getProductCategoryId, id).list()
                .stream().map(ProductCategoryAttributeRelationEntity::getProductAttributeId).collect(Collectors.toSet());
        // 去除 商品分类和商品属性相绑定的
        productAttributeIdList.removeAll(collect);

        // 创建存放 ProductCategoryAttributeRelationEntity 的列表
        Set<ProductCategoryAttributeRelationEntity> productCategoryAttributeRelationEntityList = productAttributeIdList.stream()
                .map(productAttributeId -> new ProductCategoryAttributeRelationEntity(id, productAttributeId)).collect(Collectors.toSet());
        return saveBatch(productCategoryAttributeRelationEntityList);
    }

    @Override
    @SuppressWarnings("all")
    public Boolean updateByProductCategoryId(Long id, Set<Long> productAttributeIdList) {
        // 获取有效的商品属性id列表
        // TODO 修改逻辑
        productAttributeIdList = productAttributeService.getBaseMapper().selectBatchIds(productAttributeIdList).stream().map(ProductAttributeEntity::getId).collect(Collectors.toSet());
        // 获取商品分类和商品属性以及绑定的
        Set<Long> collect = lambdaQuery().in(ProductCategoryAttributeRelationEntity::getProductAttributeId, productAttributeIdList)
                .eq(ProductCategoryAttributeRelationEntity::getProductCategoryId, id).list()
                .stream().map(ProductCategoryAttributeRelationEntity::getProductAttributeId).collect(Collectors.toSet());
        // 去除 商品分类和商品属性相绑定的
        productAttributeIdList.removeAll(collect);

        // 创建存放 ProductCategoryAttributeRelationEntity 的列表
        Set<ProductCategoryAttributeRelationEntity> productCategoryAttributeRelationEntityList = productAttributeIdList.stream()
                .map(productAttributeId -> new ProductCategoryAttributeRelationEntity(id, productAttributeId)).collect(Collectors.toSet());
        return saveBatch(productCategoryAttributeRelationEntityList);
    }


}

