package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.order.entity.OrderItemEntity;
import org.example.modules.order.mapper.OrderItemMapper;
import org.example.modules.order.service.OrderItemService;

/**
 * Created by Dou-Fu-10 2023-07-13 15:31:35
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:31:35
 * @Description 订单中所包含的商品(OrderItem)表服务实现类
 */
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItemEntity> implements OrderItemService {

}

