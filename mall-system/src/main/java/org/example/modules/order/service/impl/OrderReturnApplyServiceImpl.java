package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.order.entity.OrderReturnApplyEntity;
import org.example.modules.order.entity.dto.OrderReturnApplyDto;
import org.example.modules.order.entity.vo.OrderItemVo;
import org.example.modules.order.entity.vo.OrderReturnApplyVo;
import org.example.modules.order.entity.vo.OrderVo;
import org.example.modules.order.mapper.OrderReturnApplyMapper;
import org.example.modules.order.service.OrderItemService;
import org.example.modules.order.service.OrderReturnApplyService;
import org.example.modules.order.service.OrderService;
import org.example.modules.tools.entity.vo.CompanyAddressVo;
import org.example.modules.tools.service.CompanyAddressService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:30
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:30
 * @Description 订单退货申请(OrderReturnApply)表服务实现类
 */
@Service("orderReturnApplyService")
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyMapper, OrderReturnApplyEntity> implements OrderReturnApplyService {
    @Resource
    private CompanyAddressService companyAddressService;
    @Resource
    private OrderItemService orderItemService;
    @Resource
    private OrderService orderService;

    @Override
    public Boolean save(OrderReturnApplyDto orderReturnApply) {
        OrderReturnApplyEntity orderReturnApplyEntity = BeanCopy.convert(orderReturnApply, OrderReturnApplyEntity.class);
        return orderReturnApplyEntity.insert();
    }

    @Override
    public Boolean updateById(OrderReturnApplyDto orderReturnApply) {
        OrderReturnApplyEntity orderReturnApplyEntity = BeanCopy.convert(orderReturnApply, OrderReturnApplyEntity.class);
        return orderReturnApplyEntity.insert();
    }

    @Override
    public Page<OrderReturnApplyVo> page(Page<OrderReturnApplyEntity> page, OrderReturnApplyDto orderReturnApply) {
        OrderReturnApplyEntity orderReturnApplyEntity = BeanCopy.convert(orderReturnApply, OrderReturnApplyEntity.class);
        LambdaQueryWrapper<OrderReturnApplyEntity> orderEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(orderReturnApplyEntity);
        // 以创建时间进行排序
        orderEntityLambdaQueryWrapper.orderByAsc(OrderReturnApplyEntity::getCreateTime);
        Page<OrderReturnApplyEntity> orderReturnApplyEntityPage = page(page, orderEntityLambdaQueryWrapper);
        IPage<OrderReturnApplyVo> orderReturnApplyVoIpage = orderReturnApplyEntityPage.convert(order -> BeanCopy.convert(order, OrderReturnApplyVo.class));
        return (Page<OrderReturnApplyVo>) orderReturnApplyVoIpage;
    }

    @Override
    public OrderReturnApplyVo getByOrderReturnApplyId(Serializable id) {
        if (Objects.isNull(id)) {
            throw new BaseRequestException("参数错误");
        }
        // 获取退货申请
        OrderReturnApplyEntity orderReturnApplyEntity = getById(id);
        if (Objects.isNull(orderReturnApplyEntity)) {
            throw new BaseRequestException("未获取到相应的订单退货");
        }
        OrderReturnApplyVo orderReturnApplyVo = BeanCopy.convert(orderReturnApplyEntity, OrderReturnApplyVo.class);
        // 获取 公司收货地址id
        Long companyAddressId = orderReturnApplyVo.getCompanyAddressId();
        // 公司收货地址
        if (Objects.nonNull(companyAddressId)) {
            CompanyAddressVo companyAddressVo = companyAddressService.getByCompanyAddressIdId(companyAddressId);
            // 退货地址
            orderReturnApplyVo.setCompanyAddress(companyAddressVo);
        }
        Long orderId = orderReturnApplyVo.getOrderId();
        List<OrderItemVo> orderItemByOrderId = orderItemService.getOrderItemByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderItemByOrderId)) {
            throw new BaseRequestException("获取订单失败");
        }
        orderReturnApplyVo.setOrderItem(orderItemByOrderId);
        return orderReturnApplyVo;

    }

    @NotNull
    private Map<Long, List<OrderItemVo>> longOrderItemVoMap(@NotNull List<OrderItemVo> orderItemVo) {
        // 将 订单中所包含的商品   以map 的形式进行存储    键为 订单的id   值为 订单中所包含的商品
        Map<Long, List<OrderItemVo>> longListHashMap = new HashMap<>();
        for (OrderItemVo orderItem : orderItemVo) {
            Long orderId = orderItem.getOrderId();
            // 使用computeIfAbsent方法来避免重复的get和put操作
            longListHashMap.computeIfAbsent(orderId, k -> new ArrayList<>()).add(orderItem);
        }
        return longListHashMap;
    }
}

