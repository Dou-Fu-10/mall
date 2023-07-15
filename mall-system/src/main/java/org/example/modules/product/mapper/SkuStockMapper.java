package org.example.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.product.entity.SkuStockEntity;

/**
 * Created by Dou-Fu-10 2023-07-15 11:35:49
 *
 * @author Dou-Fu-10
 * @date 2023-07-15 11:35:49
 * @Description sku的库存(SkuStock)表数据库访问层
 */
@Mapper
public interface SkuStockMapper extends BaseMapper<SkuStockEntity> {

}

