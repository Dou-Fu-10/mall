package org.example.modules.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.order.entity.OrderItemEntity;

/**
 * Created by Dou-Fu-10 2023-08-04 11:32:58
 *
 * @author Dou-Fu-10
 * @date 2023-08-04 11:32:58
 * @Description 订单中所包含的商品(OrderItem)表数据库访问层
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItemEntity> {

}

