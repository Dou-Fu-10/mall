package org.example.modules.admin.order.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.exception.BaseRequestException;
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
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:29
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:29
 * @Description 订单表(Order)表服务实现类
 */
@Service("orderService")
@Slf4j
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
        // TODO 数据校验
        OrderEntity orderEntity = BeanCopy.convert(order, OrderEntity.class);
        return orderEntity.insert();
    }

    @Override
    public boolean updateById(OrderDto order) {
        // TODO 数据校验
        OrderEntity orderEntity = BeanCopy.convert(order, OrderEntity.class);
        return orderEntity.updateById();
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

    @Override
    public Page<OrderVo> findCompletedOrdersByMonth(Page<OrderEntity> page, OrderDto orderDto, DateTime date) {
        OrderEntity orderEntity = BeanCopy.convert(orderDto, OrderEntity.class);
        // 获取当前日期时间
        DateTime currentTime = DateUtil.date();
        // 校验 最后一天不可使用
        // 判断给定的日期是否是所在月份的最后一天
        // 即当前不是当月最后一天
//        boolean isLastDayOfMonth = DateUtil.isLastDayOfMonth(currentTime);
//        if (isLastDayOfMonth) {
//            throw new BaseRequestException("当月的最后一天不可查看");
//        }
//        // 当前时间月末
//        DateTime currentTimeEndOfMonth = DateUtil.endOfMonth(currentTime);
//        // 传入的时间不能 在这个月月底之后 ，即 一月份不能查询二月的数据
//        if (date.isAfter(currentTimeEndOfMonth)) {
//            throw new BaseRequestException("请传入正确的时间");
//        }
        // 获取月份的第一天
        DateTime firstDayOfMonth = DateUtil.beginOfMonth(DateUtil.date());
        log.info("第一天：" + firstDayOfMonth);
        // 获取月份的最后一天
        DateTime lastDayOfMonth = DateUtil.endOfMonth(DateUtil.date());
        log.info("最后一天：" + lastDayOfMonth);
        LambdaQueryWrapper<OrderEntity> orderEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(orderEntity);
        // 已完成的订单
        orderEntityLambdaQueryWrapper.eq(OrderEntity::getStatus, 3);
        // 查询时间范围
        orderEntityLambdaQueryWrapper.between(OrderEntity::getPaymentTime, firstDayOfMonth, lastDayOfMonth);
        Page<OrderEntity> orderEntityPage = page(page, orderEntityLambdaQueryWrapper);
        IPage<OrderVo> convert = orderEntityPage.convert(order -> BeanCopy.convert(order, OrderVo.class));
        return (Page) convert;
    }

    @Override
    public BigDecimal findTotalAmountCompletedOrdersByMonth(DateTime dateTime) {
        // 获取当前日期时间
        DateTime currentTime = DateUtil.date();
        // 校验 最后一天不可使用
        // 判断给定的日期是否是所在月份的最后一天
        // 即当前不是当月最后一天
//        boolean isLastDayOfMonth = DateUtil.isLastDayOfMonth(currentTime);
//        if (isLastDayOfMonth) {
//            throw new BaseRequestException("当月的最后一天不可查看");
//        }
//        // 当前时间月末
//        DateTime currentTimeEndOfMonth = DateUtil.endOfMonth(currentTime);
//        // 传入的时间不能 在这个月月底之后 ，即 一月份不能查询二月的数据
//        if (dateTime.isAfter(currentTimeEndOfMonth)) {
//            throw new BaseRequestException("请传入正确的时间");
//        }
        // 获取月份的第一天
        DateTime firstDayOfMonth = DateUtil.beginOfMonth(DateUtil.date());
        log.info("第一天：" + firstDayOfMonth);
        // 获取月份的最后一天
        DateTime lastDayOfMonth = DateUtil.endOfMonth(DateUtil.date());
        log.info("最后一天：" + lastDayOfMonth);
        LambdaQueryWrapper<OrderEntity> orderEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 已完成的订单
        orderEntityLambdaQueryWrapper.eq(OrderEntity::getStatus, 3);
        orderEntityLambdaQueryWrapper.isNotNull(OrderEntity::getPayAmount);
        // 查询时间范围
        orderEntityLambdaQueryWrapper.between(OrderEntity::getPaymentTime, firstDayOfMonth, lastDayOfMonth);
        // 只返回 PayAmount
        orderEntityLambdaQueryWrapper.select(OrderEntity::getPayAmount);
        List<OrderEntity> orderEntityList = list(orderEntityLambdaQueryWrapper);
        BigDecimal totalAmountCompletedOrder = BigDecimal.ZERO;
        // 将金额进行累加
        for (OrderEntity orderEntity : orderEntityList) {
            totalAmountCompletedOrder = totalAmountCompletedOrder.add(orderEntity.getPayAmount());
        }
        return totalAmountCompletedOrder;
    }

    @Override
    public Boolean updateStatus(Long id, Integer status, String deliveryCompany, String deliverySn) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(id);
        orderEntity.setStatus(status);
        orderEntity.setDeliveryCompany(deliveryCompany);
        orderEntity.setDeliverySn(deliverySn);
        return orderEntity.updateById();
    }
}

