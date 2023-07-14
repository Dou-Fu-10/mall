package org.example.modules.product.service;

import org.example.modules.product.entity.ProductCategoryAttributeRelationEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.product.entity.dto.ProductCategoryAttributeRelationDto;

/**
 * Created by PanShiFu 2023-07-14 13:54:17
 *
 * @author PanShiFu
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
    boolean save(ProductCategoryAttributeRelationDto productCategoryAttributeRelation);

    /**
     * 修改数据
     *
     * @param productCategoryAttributeRelation 实体对象
     * @return 修改结果
     */
    boolean updateById(ProductCategoryAttributeRelationDto productCategoryAttributeRelation);
}
