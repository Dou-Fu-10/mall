package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.order.entity.OrderEntity;
import org.example.modules.order.mapper.OrderMapper;
import org.example.modules.order.service.OrderService;

/**
 * Created by Dou-Fu-10 2023-07-13 15:31:34
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:31:34
 * @Description 订单表(Order)表服务实现类
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {

}

