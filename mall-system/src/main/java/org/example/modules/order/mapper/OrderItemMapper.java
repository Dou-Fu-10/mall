package org.example.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.order.entity.OrderItemEntity;

/**
 * Created by PanShiFu 2023-07-14 14:34:29
 *
 * @author PanShiFu
 * @date 2023-07-14 14:34:29
 * @Description 订单中所包含的商品(OrderItem)表数据库访问层
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItemEntity> {

}

