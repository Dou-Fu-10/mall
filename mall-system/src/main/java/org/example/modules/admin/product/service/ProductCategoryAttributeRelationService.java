package org.example.modules.admin.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.admin.product.entity.ProductCategoryAttributeRelationEntity;
import org.example.modules.admin.product.entity.dto.ProductCategoryAttributeRelationDto;

import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-07-14 13:54:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:54:17
 * @Description 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）(ProductCategoryAttributeRelation)表服务接口
 */
public interface ProductCategoryAttributeRelationService extends IService<ProductCategoryAttributeRelationEntity> {
    /**
     * 新增数据
     *
     * @param productCategoryAttributeRelation 实体对象
     * @return 新增结果
     */
    Boolean save(ProductCategoryAttributeRelationDto productCategoryAttributeRelation);

    /**
     * 修改数据
     *
     * @param productCategoryAttributeRelation 实体对象
     * @return 修改结果
     */
    Boolean updateById(ProductCategoryAttributeRelationDto productCategoryAttributeRelation);

    /**
     * 新增数据
     *
     * @param id                     商品分类id
     * @param productAttributeIdList 商品属性id列表
     * @return 新增结果
     */
    Boolean save(Long id, Set<Long> productAttributeIdList);

    /**
     * 新增数据
     *
     * @param id                     商品分类id
     * @param productAttributeIdList 商品属性id列表
     * @return 新增结果
     */
    Boolean updateByProductCategoryId(Long id, Set<Long> productAttributeIdList);
}
