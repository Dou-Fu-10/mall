package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.order.entity.OrderEntity;
import org.example.modules.order.mapper.OrderMapper;
import org.example.modules.order.service.OrderService;

/**
 * Created by PanShiFu 2023-07-13 14:31:56
 *
 * @author PanShiFu
 * @date 2023-07-13 14:31:56
 * @Description 订单表(Order)表服务实现类
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {

}

