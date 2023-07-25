package org.example.modules.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.order.entity.OrderItemEntity;
import org.example.modules.order.entity.dto.OrderItemDto;
import org.example.modules.order.entity.vo.OrderItemVo;

import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:29
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:29
 * @Description 订单中所包含的商品(OrderItem)表服务接口
 */
public interface OrderItemService extends IService<OrderItemEntity> {
    /**
     * 新增数据
     *
     * @param orderItem 实体对象
     * @return 新增结果
     */
    boolean save(OrderItemDto orderItem);

    /**
     * 修改数据
     *
     * @param orderItem 实体对象
     * @return 修改结果
     */
    boolean updateById(OrderItemDto orderItem);

    /**
     * 根据订单id 获取订单中所包含的商品
     *
     * @param id 订单id
     * @return 订单中所包含的商品
     */
    List<OrderItemVo> getOrderItemByOrderId(Long id);
}
