package org.example.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.product.entity.ProductAttributeValueEntity;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:54
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:54
 * @Description 存储产品参数信息的表(ProductAttributeValue)表数据库访问层
 */
@Mapper
public interface ProductAttributeValueMapper extends BaseMapper<ProductAttributeValueEntity> {

}

