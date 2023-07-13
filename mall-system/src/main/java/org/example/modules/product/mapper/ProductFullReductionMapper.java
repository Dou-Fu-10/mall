package org.example.modules.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.product.entity.ProductFullReductionEntity;

/**
 * Created by PanShiFu 2023-07-13 15:35:55
 *
 * @author PanShiFu
 * @date 2023-07-13 15:35:55
 * @Description 产品满减表(只针对同商品)(ProductFullReduction)表数据库访问层
 */
@Mapper
public interface ProductFullReductionMapper extends BaseMapper<ProductFullReductionEntity> {

}

