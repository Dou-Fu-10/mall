package org.example.modules.admin.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.admin.product.entity.ProductOperateLogEntity;

/**
 * Created by Dou-Fu-10 2023-07-14 13:54:18
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:54:18
 * @Description 商品价格变动记录(ProductOperateLog)表数据库访问层
 */
@Mapper
public interface ProductOperateLogMapper extends BaseMapper<ProductOperateLogEntity> {

}

