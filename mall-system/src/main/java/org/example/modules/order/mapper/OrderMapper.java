package org.example.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.order.entity.OrderEntity;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:28
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:28
 * @Description 订单表(Order)表数据库访问层
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {

}

