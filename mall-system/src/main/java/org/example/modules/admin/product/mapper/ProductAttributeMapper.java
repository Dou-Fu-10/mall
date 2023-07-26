package org.example.modules.admin.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.admin.product.entity.ProductAttributeEntity;

/**
 * Created by Dou-Fu-10 2023-07-14 12:49:51
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 12:49:51
 * @Description 商品属性参数表(ProductAttribute)表数据库访问层
 */
@Mapper
public interface ProductAttributeMapper extends BaseMapper<ProductAttributeEntity> {

}

