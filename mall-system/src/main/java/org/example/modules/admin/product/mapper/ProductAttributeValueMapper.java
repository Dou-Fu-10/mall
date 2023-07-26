package org.example.modules.admin.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.admin.product.entity.ProductAttributeValueEntity;

/**
 * Created by Dou-Fu-10 2023-07-14 13:54:16
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:54:16
 * @Description 存储产品参数信息的表(ProductAttributeValue)表数据库访问层
 */
@Mapper
public interface ProductAttributeValueMapper extends BaseMapper<ProductAttributeValueEntity> {

}

