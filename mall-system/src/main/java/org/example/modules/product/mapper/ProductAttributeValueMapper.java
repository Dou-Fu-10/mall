package org.example.modules.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.product.entity.ProductAttributeValueEntity;

/**
 * Created by PanShiFu 2023-07-14 13:54:16
 *
 * @author PanShiFu
 * @date 2023-07-14 13:54:16
 * @Description 存储产品参数信息的表(ProductAttributeValue)表数据库访问层
 */
@Mapper
public interface ProductAttributeValueMapper extends BaseMapper<ProductAttributeValueEntity> {

}

