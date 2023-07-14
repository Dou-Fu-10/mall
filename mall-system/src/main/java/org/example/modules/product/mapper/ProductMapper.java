package org.example.modules.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.product.entity.ProductEntity;

/**
 * Created by PanShiFu 2023-07-14 13:05:47
 *
 * @author PanShiFu
 * @date 2023-07-14 13:05:47
 * @Description 商品信息(Product)表数据库访问层
 */
@Mapper
public interface ProductMapper extends BaseMapper<ProductEntity> {

}

