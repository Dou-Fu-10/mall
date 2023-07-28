package org.example.modules.admin.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.admin.order.entity.OrderEntity;
import org.example.modules.admin.order.entity.dto.OrderDto;
import org.example.modules.admin.order.entity.vo.OrderItemVo;
import org.example.modules.admin.order.entity.vo.OrderOperateHistoryVo;
import org.example.modules.admin.order.entity.vo.OrderVo;
import org.example.modules.admin.order.mapper.OrderMapper;
import org.example.modules.admin.order.service.OrderItemService;
import org.example.modules.admin.order.service.OrderOperateHistoryService;
import org.example.modules.admin.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:29
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:29
 * @Description 订单表(Order)表服务实现类
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {
    /**
     * 订单与商品的详细信息绑定
     */
    @Resource
    private OrderItemService orderItemService;
    @Resource
    private OrderOperateHistoryService orderOperateHistoryService;

    @Override
    public boolean save(OrderDto order) {
        return false;
    }

    @Override
    public boolean updateById(OrderDto order) {
        return false;
    }

    @Override
    public OrderVo getOrderById(Serializable id) {
        OrderEntity orderEntity = getById(id);
        OrderVo orderVo = BeanCopy.convert(orderEntity, OrderVo.class);
        List<OrderItemVo> orderItemVoList = orderItemService.getOrderItemByOrderId(orderEntity.getId());
        List<OrderOperateHistoryVo> orderOperateHistoryVoList = orderOperateHistoryService.getOrderOperateHistoryByOrderId(orderEntity.getId());
        orderVo.setOrderItemList(orderItemVoList);
        orderVo.setHistoryList(orderOperateHistoryVoList);
        return orderVo;
    }

    @Override
    public Page<OrderVo> page(Page<OrderEntity> page, OrderDto orderDto) {
        OrderEntity orderEntity = BeanCopy.convert(orderDto, OrderEntity.class);
        LambdaQueryWrapper<OrderEntity> orderEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(orderEntity);
        orderEntityLambdaQueryWrapper.orderByAsc(OrderEntity::getId);
        Page<OrderEntity> orderEntityPage = page(page, orderEntityLambdaQueryWrapper);
        IPage<OrderVo> orderVoIpage = orderEntityPage.convert(order -> BeanCopy.convert(order, OrderVo.class));
        return (Page) orderVoIpage;
    }
}

