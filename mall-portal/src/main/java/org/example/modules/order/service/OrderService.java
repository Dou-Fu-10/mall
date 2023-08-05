package org.example.modules.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.order.entity.OrderEntity;
import org.example.modules.order.entity.dto.GenerateOrderDto;
import org.example.modules.order.entity.dto.OrderDto;
import org.example.modules.order.entity.vo.ConfirmOrderVo;
import org.example.modules.order.entity.vo.OrderVo;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-08-03 14:28:08
 *
 * @author Dou-Fu-10
 * @date 2023-08-03 14:28:08
 * @Description 订单表(Order)表服务接口
 */
public interface OrderService extends IService<OrderEntity> {
    /**
     * 新增数据
     *
     * @param orderDto 实体对象
     * @return 新增结果
     */
    Boolean save(OrderDto orderDto);

    /**
     * 修改数据
     *
     * @param orderDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(OrderDto orderDto);

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param orderDto 查询实体
     * @return 所有数据
     */
    Page<OrderVo> page(Page<OrderEntity> page, OrderDto orderDto);

    /**
     * 根据购物车信息生成确认单
     *
     * @param cartIds 购物车id列表
     * @return 生成确认单
     */
    ConfirmOrderVo generateConfirmOrder(Set<Long> cartIds);

    /**
     * 生成订单
     *
     * @param generateOrderDto 订单信息
     * @return 生成结果
     */
    Map<String, Object> generateOrder(GenerateOrderDto generateOrderDto);
    /**
     * 确认收货
     * @param orderId 订单id
     * @return /
     */
    Boolean confirmReceiveOrder(Long orderId);
    /**
     * 用户删除订单
     *
     * @param orderId 订单id
     * @return /
     */
    Boolean deleteOrder(Long orderId);
    /**
     * 用户取消订单
     *
     * @param orderId 订单id
     * @return /
     */
    Boolean cancelOrder(Long orderId);
}
