package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.order.entity.OrderItemEntity;
import org.example.modules.order.entity.dto.OrderItemDto;
import org.example.modules.order.entity.vo.OrderItemVo;
import org.example.modules.order.mapper.OrderItemMapper;
import org.example.modules.order.service.OrderItemService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:29
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:29
 * @Description 订单中所包含的商品(OrderItem)表服务实现类
 */
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItemEntity> implements OrderItemService {
    @Override
    public boolean save(@NotNull OrderItemDto orderItem) {
        OrderItemEntity orderItemEntity = BeanCopy.convert(orderItem, OrderItemEntity.class);
        return orderItemEntity.insert();
    }

    @Override
    public boolean updateById(@NotNull OrderItemDto orderItem) {
        OrderItemEntity orderItemEntity = BeanCopy.convert(orderItem, OrderItemEntity.class);
        return orderItemEntity.updateById();
    }

    @Override
    public List<OrderItemVo> getOrderItemByOrderId(Long orderId) {
        List<OrderItemEntity> list = lambdaQuery().eq(OrderItemEntity::getOrderId, orderId).list();
        return BeanCopy.copytList(list, OrderItemVo.class);
    }
}

