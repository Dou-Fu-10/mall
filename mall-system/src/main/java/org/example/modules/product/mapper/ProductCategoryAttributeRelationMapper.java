package org.example.modules.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.product.entity.ProductCategoryAttributeRelationEntity;

/**
 * Created by PanShiFu 2023-07-14 13:54:17
 *
 * @author PanShiFu
 * @date 2023-07-14 13:54:17
 * @Description 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）(ProductCategoryAttributeRelation)表数据库访问层
 */
@Mapper
public interface ProductCategoryAttributeRelationMapper extends BaseMapper<ProductCategoryAttributeRelationEntity> {

}

