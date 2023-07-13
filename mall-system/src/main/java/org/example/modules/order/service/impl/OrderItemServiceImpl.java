package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.order.entity.OrderItemEntity;
import org.example.modules.order.mapper.OrderItemMapper;
import org.example.modules.order.service.OrderItemService;

/**
 * Created by PanShiFu 2023-07-13 14:31:57
 *
 * @author PanShiFu
 * @date 2023-07-13 14:31:57
 * @Description 订单中所包含的商品(OrderItem)表服务实现类
 */
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItemEntity> implements OrderItemService {

}

