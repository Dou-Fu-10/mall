package org.example.modules.admin.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.admin.order.entity.OrderItemEntity;
import org.example.modules.admin.order.entity.dto.OrderItemDto;
import org.example.modules.admin.order.entity.vo.OrderItemVo;
import org.example.modules.admin.order.mapper.OrderItemMapper;
import org.example.modules.admin.order.service.OrderItemService;
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
    public boolean save(OrderItemDto orderItem) {
        return false;
    }

    @Override
    public boolean updateById(OrderItemDto orderItem) {
        return false;
    }

    @Override
    public List<OrderItemVo> getOrderItemByOrderId(Long orderId) {
        List<OrderItemEntity> list = lambdaQuery().eq(OrderItemEntity::getOrderId, orderId).list();
        return BeanCopy.copytList(list, OrderItemVo.class);
    }
}

