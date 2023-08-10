package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.order.entity.OrderItemEntity;
import org.example.modules.order.entity.dto.OrderItemDto;
import org.example.modules.order.entity.vo.OrderItemVo;
import org.example.modules.order.mapper.OrderItemMapper;
import org.example.modules.order.service.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-08-04 11:32:58
 *
 * @author Dou-Fu-10
 * @date 2023-08-04 11:32:58
 * @Description 订单中所包含的商品(OrderItem)表服务实现类
 */
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItemEntity> implements OrderItemService {
    @Override
    public Boolean save(OrderItemDto orderItemDto) {
        OrderItemEntity orderItemEntity = BeanCopy.convert(orderItemDto, OrderItemEntity.class);
        return save(orderItemEntity);
    }

    @Override
    public Boolean updateById(OrderItemDto orderItemDto) {
        OrderItemEntity orderItemEntity = BeanCopy.convert(orderItemDto, OrderItemEntity.class);
        return updateById(orderItemEntity);
    }

    @Override
    public Page<OrderItemVo> page(Page<OrderItemEntity> page, OrderItemDto orderItemDto) {
        OrderItemEntity orderItemEntity = BeanCopy.convert(orderItemDto, OrderItemEntity.class);
        LambdaQueryWrapper<OrderItemEntity> orderItemEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(orderItemEntity);
        Page<OrderItemEntity> orderItemEntityPage = page(page, orderItemEntityLambdaQueryWrapper);
        IPage<OrderItemVo> orderItemEntityPageVoIpage = orderItemEntityPage.convert(orderItem -> BeanCopy.convert(orderItem, OrderItemVo.class));
        return (Page<OrderItemVo>) orderItemEntityPageVoIpage;
    }

    @Override
    public List<OrderItemVo> getByOrderIds(Set<Long> orderIds) {
        if (CollectionUtils.isEmpty(orderIds)) {
            return new ArrayList<>();
        }
        List<OrderItemEntity> orderItemEntityList = lambdaQuery().in(OrderItemEntity::getOrderId, orderIds).list();
        if (CollectionUtils.isEmpty(orderItemEntityList)) {
            return new ArrayList<>();
        }
        return BeanCopy.copytList(orderItemEntityList, OrderItemVo.class);
    }

}

