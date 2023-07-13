package org.example.modules.product.service;

import org.example.modules.product.entity.ProductCategoryAttributeRelationEntity;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * Created by PanShiFu 2023-07-13 15:35:55
 *
 * @author PanShiFu
 * @date 2023-07-13 15:35:55
 * @Description 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）(ProductCategoryAttributeRelation)表服务接口
 */
public interface ProductCategoryAttributeRelationService extends IService<ProductCategoryAttributeRelationEntity> {

}
