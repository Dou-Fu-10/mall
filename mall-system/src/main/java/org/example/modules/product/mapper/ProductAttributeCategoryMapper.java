package org.example.modules.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.product.entity.ProductAttributeCategoryEntity;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:54
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:54
 * @Description 产品属性分类表(ProductAttributeCategory)表数据库访问层
 */
@Mapper
public interface ProductAttributeCategoryMapper extends BaseMapper<ProductAttributeCategoryEntity> {

}

