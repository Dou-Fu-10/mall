package org.example.modules.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.product.entity.SkuStockEntity;

/**
 * Created by PanShiFu 2023-07-13 15:35:57
 *
 * @author PanShiFu
 * @date 2023-07-13 15:35:57
 * @Description sku的库存(SkuStock)表数据库访问层
 */
@Mapper
public interface SkuStockMapper extends BaseMapper<SkuStockEntity> {

}

