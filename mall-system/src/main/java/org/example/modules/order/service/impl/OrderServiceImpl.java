package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.order.entity.OrderEntity;
import org.example.modules.order.entity.dto.OrderDto;
import org.example.modules.order.mapper.OrderMapper;
import org.example.modules.order.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * Created by PanShiFu 2023-07-14 14:34:29
 *
 * @author PanShiFu
 * @date 2023-07-14 14:34:29
 * @Description 订单表(Order)表服务实现类
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {
    @Override
    public boolean save(OrderDto Order) {
        return false;
    }

    @Override
    public boolean updateById(OrderDto Order) {
        return false;
    }
}

