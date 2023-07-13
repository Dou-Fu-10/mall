package org.example.modules.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.order.entity.OrderItemEntity;

/**
 * Created by PanShiFu 2023-07-13 15:31:34
 *
 * @author PanShiFu
 * @date 2023-07-13 15:31:34
 * @Description 订单中所包含的商品(OrderItem)表数据库访问层
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItemEntity> {

}

