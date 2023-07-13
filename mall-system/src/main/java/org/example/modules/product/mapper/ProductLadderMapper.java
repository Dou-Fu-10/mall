package org.example.modules.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.product.entity.ProductLadderEntity;

/**
 * Created by PanShiFu 2023-07-13 15:35:56
 *
 * @author PanShiFu
 * @date 2023-07-13 15:35:56
 * @Description 产品阶梯价格表(只针对同商品)(ProductLadder)表数据库访问层
 */
@Mapper
public interface ProductLadderMapper extends BaseMapper<ProductLadderEntity> {

}

