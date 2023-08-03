package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.order.entity.OrderEntity;
import org.example.modules.order.entity.dto.OrderDto;
import org.example.modules.order.entity.vo.OrderVo;
import org.example.modules.order.mapper.OrderMapper;
import org.example.modules.order.service.OrderService;
import org.example.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-08-03 14:28:08
 *
 * @author Dou-Fu-10
 * @date 2023-08-03 14:28:08
 * @Description 订单表(Order)表服务实现类
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {
    @Override
    public Boolean save(OrderDto orderDto) {
        OrderEntity orderEntity = BeanCopy.convert(orderDto, OrderEntity.class);
        return save(orderEntity);
    }

    @Override
    public Boolean updateById(OrderDto orderDto) {
        OrderEntity orderEntity = BeanCopy.convert(orderDto, OrderEntity.class);
        return updateById(orderEntity);
    }

    @Override
    public Page<OrderVo> page(Page<OrderEntity> page, OrderDto orderDto) {
        OrderEntity orderEntity = BeanCopy.convert(orderDto, OrderEntity.class);
        // 获取登录者id
        Long currentUserId = SecurityUtils.getCurrentUserId();
        // 只获取登录者的订单信息
        orderEntity.setMemberId(currentUserId);
        LambdaQueryWrapper<OrderEntity> orderEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(orderEntity);
        Page<OrderEntity> orderEntityPage = page(page, orderEntityLambdaQueryWrapper);
        IPage<OrderVo> orderEntityPageVoIpage = orderEntityPage.convert(order -> BeanCopy.convert(order, OrderVo.class));
        return (Page) orderEntityPageVoIpage;
    }
}

