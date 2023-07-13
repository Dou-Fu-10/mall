package org.example.modules.product.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.product.entity.ProductOperateLogEntity;

/**
 * Created by PanShiFu 2023-07-13 15:35:56
 *
 * @author PanShiFu
 * @date 2023-07-13 15:35:56
 * @Description (ProductOperateLog)表数据库访问层
 */
@Mapper
public interface ProductOperateLogMapper extends BaseMapper<ProductOperateLogEntity> {

}

