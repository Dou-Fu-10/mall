package org.example.modules.order.service;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.order.entity.OrderEntity;
import org.example.modules.order.entity.dto.OrderDeliveryDto;
import org.example.modules.order.entity.dto.OrderDto;
import org.example.modules.order.entity.dto.ReceiverInfoDto;
import org.example.modules.order.entity.vo.OrderVo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:28
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:28
 * @Description 订单表(Order)表服务接口
 */
public interface OrderService extends IService<OrderEntity> {
    /**
     * 新增数据
     *
     * @param order 实体对象
     * @return 新增结果
     */
    Boolean save(OrderDto order);

    /**
     * 修改数据
     *
     * @param order 实体对象
     * @return 修改结果
     */
    Boolean updateById(OrderDto order);

    /**
     * 获取订单信息
     *
     * @param id 订单id
     * @return 订单信息
     */
    OrderVo getOrderById(Serializable id);

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param orderDto 查询实体
     * @return 所有数据
     */
    Page<OrderVo> page(Page<OrderEntity> page, OrderDto orderDto);

    /**
     * 按传入的月份查找月份已完成的订单
     *
     * @param page     分页对象
     * @param orderDto 查询实体
     * @param date     月份
     * @return 已完成的订单
     */
    Page<OrderVo> findCompletedOrdersByMonth(Page<OrderEntity> page, OrderDto orderDto, DateTime date);

    /**
     * 按月查找已完成交易订单的总金额
     *
     * @param dateTime 时间
     * @return 金额
     */
    BigDecimal findTotalAmountCompletedOrdersByMonth(DateTime dateTime);

    /**
     * 修改帐号状态
     *
     * @param id     用户id
     * @param status 状态
     * @return String
     */
    Boolean updateStatus(Long id, Integer status, String deliveryCompany, String deliverySn);

    /**
     * 修改订单状态
     *
     * @param orderDeliveryDto 订单发货
     * @return String
     */
    Boolean delivery(OrderDeliveryDto orderDeliveryDto);

    /**
     * 取消订单
     *
     * @param id   订单id
     * @param note 备注
     * @return /
     */
    Boolean close(Long id, String note);

    /**
     * 修改收货人信息
     *
     * @param receiverInfoDto 收货人
     * @return /
     */
    Boolean updateReceiverInfo(ReceiverInfoDto receiverInfoDto);

    /**
     * 订单备注
     *
     * @param id   订单id
     * @param note 订单备注
     * @return /
     */
    Boolean updateNote(Long id, String note);
}
