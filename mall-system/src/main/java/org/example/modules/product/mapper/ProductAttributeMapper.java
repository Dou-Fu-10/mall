package org.example.modules.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.product.entity.ProductAttributeEntity;

/**
 * Created by PanShiFu 2023-07-14 12:49:51
 *
 * @author PanShiFu
 * @date 2023-07-14 12:49:51
 * @Description 商品属性参数表(ProductAttribute)表数据库访问层
 */
@Mapper
public interface ProductAttributeMapper extends BaseMapper<ProductAttributeEntity> {

}
