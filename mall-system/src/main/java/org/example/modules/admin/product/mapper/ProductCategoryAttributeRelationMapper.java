package org.example.modules.admin.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.admin.product.entity.ProductCategoryAttributeRelationEntity;

/**
 * Created by Dou-Fu-10 2023-07-14 13:54:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:54:17
 * @Description 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）(ProductCategoryAttributeRelation)表数据库访问层
 */
@Mapper
public interface ProductCategoryAttributeRelationMapper extends BaseMapper<ProductCategoryAttributeRelationEntity> {

}

