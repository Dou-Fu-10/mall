package org.example.modules.order.service.impl;

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
import org.example.common.core.utils.StringUtils;
import org.example.modules.order.entity.OrderEntity;
import org.example.modules.order.entity.OrderSettingEntity;
import org.example.modules.order.entity.dto.OrderDeliveryDto;
import org.example.modules.order.entity.dto.OrderDto;
import org.example.modules.order.entity.dto.ReceiverInfoDto;
import org.example.modules.order.entity.vo.OrderItemVo;
import org.example.modules.order.entity.vo.OrderOperateHistoryVo;
import org.example.modules.order.entity.vo.OrderVo;
import org.example.modules.order.mapper.OrderMapper;
import org.example.modules.order.service.OrderItemService;
import org.example.modules.order.service.OrderOperateHistoryService;
import org.example.modules.order.service.OrderService;
import org.example.modules.order.service.OrderSettingService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

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
    @Resource
    private OrderSettingService orderSettingService;

    @Override
    public Boolean save(OrderDto order) {
        // TODO 数据校验
        OrderEntity orderEntity = BeanCopy.convert(order, OrderEntity.class);
        return orderEntity.insert();
    }

    @Override
    public Boolean updateById(OrderDto order) {
        // TODO 数据校验
        OrderEntity orderEntity = BeanCopy.convert(order, OrderEntity.class);
        return orderEntity.updateById();
    }

    @Override
    public OrderVo getOrderById(Serializable id) {
        if (Objects.isNull(id)) {
            throw new BaseRequestException("参数有误");
        }
        OrderEntity orderEntity = getById(id);
        OrderVo orderVo = BeanCopy.convert(orderEntity, OrderVo.class);
        if (Objects.isNull(orderVo)) {
            return null;
        }
        // 数据可能返回为空   订单中所包含的商品
        List<OrderItemVo> orderItemVoList = orderItemService.getOrderItemByOrderId(orderEntity.getId());
        // 数据可能返回为空   订单操作历史记录
        List<OrderOperateHistoryVo> orderOperateHistoryVoList = orderOperateHistoryService.getOrderOperateHistoryByOrderId(orderEntity.getId());
        orderVo.setOrderItemList(orderItemVoList);
        orderVo.setHistoryList(orderOperateHistoryVoList);
        return orderVo;
    }

    @Override
    public Page<OrderVo> page(Page<OrderEntity> page, OrderDto orderDto) {
        OrderEntity orderEntity = BeanCopy.convert(orderDto, OrderEntity.class);
        LambdaQueryWrapper<OrderEntity> orderEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(orderEntity);
        // 以创建时间排序
        orderEntityLambdaQueryWrapper.orderByAsc(OrderEntity::getCreateTime);
        Page<OrderEntity> orderEntityPage = page(page, orderEntityLambdaQueryWrapper);
        // 分页展示
        IPage<OrderVo> orderVoIPage = orderEntityPage.convert(order -> BeanCopy.convert(order, OrderVo.class));
        return (Page<OrderVo>) orderVoIPage;
    }

    @Override
    public Page<OrderVo> findCompletedOrdersByMonth(Page<OrderEntity> page, OrderDto orderDto, DateTime dateTime) {
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
        DateTime firstDayOfMonth = DateUtil.beginOfMonth(dateTime);
        log.info("第一天：" + firstDayOfMonth);
        // 获取月份的最后一天
        DateTime lastDayOfMonth = DateUtil.endOfMonth(dateTime);
        log.info("最后一天：" + lastDayOfMonth);
        LambdaQueryWrapper<OrderEntity> orderEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(orderEntity);
        // 已完成的订单
        orderEntityLambdaQueryWrapper.eq(OrderEntity::getStatus, 3);
        // 查询时间范围
        orderEntityLambdaQueryWrapper.between(OrderEntity::getPaymentTime, firstDayOfMonth, lastDayOfMonth);
        Page<OrderEntity> orderEntityPage = page(page, orderEntityLambdaQueryWrapper);
        IPage<OrderVo> orderVoIPage = orderEntityPage.convert(order -> BeanCopy.convert(order, OrderVo.class));
        return (Page<OrderVo>) orderVoIPage;
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
        DateTime firstDayOfMonth = DateUtil.beginOfMonth(dateTime);
        log.info("第一天：" + firstDayOfMonth);
        // 获取月份的最后一天
        DateTime lastDayOfMonth = DateUtil.endOfMonth(dateTime);
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
        // id 必传
        if (Objects.isNull(id)) {
            return false;
        }
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(id);
        orderEntity.setStatus(status);
        orderEntity.setDeliveryCompany(deliveryCompany);
        orderEntity.setDeliverySn(deliverySn);
        return orderEntity.updateById();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean delivery(@NotNull OrderDeliveryDto orderDeliveryDto) {

        Long orderId = orderDeliveryDto.getOrderId();
        String deliveryCompany = orderDeliveryDto.getDeliveryCompany();
        String deliverySn = orderDeliveryDto.getDeliverySn();
        OrderVo orderVo = getOrderById(orderId);
        if (Objects.isNull(orderVo) || StringUtils.isBlank(deliveryCompany) || StringUtils.isBlank(deliverySn)) {
            throw new BaseRequestException("正确的填写商品");
        }

        if (orderVo.getStatus() != 1) {
            throw new BaseRequestException("只能发送未发货的商品");
        }

        OrderEntity orderEntity = new OrderEntity();
        // 操作订单
        orderEntity.setId(orderVo.getId());
        // 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
        orderEntity.setStatus(2);
        // 发货公司
        orderEntity.setDeliveryCompany(deliveryCompany);
        // 发货单号
        orderEntity.setDeliverySn(deliverySn);


        if (!updateById(orderEntity)) {
            return false;
        }
        // 添加操作记录
        orderOperateHistoryService.addOperationRecord(Set.of(orderEntity.getId()));
        return true;
    }

    @Override
    public Boolean close(Long id, String note) {

        if (Objects.isNull(note)) {
            throw new BaseRequestException("请填写备注");
        }
        OrderEntity order = getById(id);
        // 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
        if (order.getStatus() == 0 || order.getStatus() == 1) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setId(id);
            orderEntity.setNote(note);
            orderEntity.setStatus(4);
            return orderEntity.updateById();
        }
        throw new BaseRequestException("只能取消待付款或者待发货的订单");
    }

    @Override
    public Boolean updateReceiverInfo(@NotNull ReceiverInfoDto receiverInfoDto) {
        Long orderId = receiverInfoDto.getOrderId();
        OrderEntity orderEntity = getById(orderId);
        if (orderEntity.getStatus() != 1) {
            throw new BaseRequestException("只能修改带待发货的订单");
        }
        OrderEntity order = BeanCopy.convert(receiverInfoDto, OrderEntity.class);
        return order.updateById();
    }

    @Override
    public Boolean updateNote(Long id, String note) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(id);
        orderEntity.setNote(note);
        return orderEntity.updateById();
    }


    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean cancelUnpaidOrdersBulk() {
        OrderSettingEntity orderSettingEntity = orderSettingService.getById(1);

        Integer normalOrderOvertime = orderSettingEntity.getNormalOrderOvertime();

        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();
        // 将当前时间减去10分钟
        calendar.setTime(currentTime);
        calendar.add(Calendar.MINUTE, -normalOrderOvertime);
        // 获取减去10分钟后的时间
        Date endTime = calendar.getTime();
        // 获取今年的第一天
        DateTime startTime = DateUtil.beginOfYear(DateUtil.date());

        LambdaQueryWrapper<OrderEntity> orderEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderEntityLambdaQueryWrapper.eq(OrderEntity::getStatus, 0);
        orderEntityLambdaQueryWrapper.between(OrderEntity::getCreateTime, startTime, endTime);
        List<OrderEntity> orderEntityList = list(orderEntityLambdaQueryWrapper);
        orderEntityList.forEach(orderEntity -> orderEntity.setStatus(4));
        return updateBatchById(orderEntityList);
    }
}

