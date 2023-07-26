package org.example.modules.admin.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.admin.product.entity.ProductAttributeCategoryEntity;

/**
 * Created by Dou-Fu-10 2023-07-14 11:03:42
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 11:03:42
 * @Description 产品属性分类表(ProductAttributeCategory)表数据库访问层
 */
@Mapper
public interface ProductAttributeCategoryMapper extends BaseMapper<ProductAttributeCategoryEntity> {

}

