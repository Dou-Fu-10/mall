package org.example.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.product.entity.ProductAttributeEntity;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:53
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:53
 * @Description 商品属性参数表(ProductAttribute)表数据库访问层
 */
@Mapper
public interface ProductAttributeMapper extends BaseMapper<ProductAttributeEntity> {

}

