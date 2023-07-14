package org.example.modules.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.order.entity.OrderItemEntity;
import org.example.modules.order.entity.dto.OrderItemDto;

/**
 * Created by PanShiFu 2023-07-14 14:34:29
 *
 * @author PanShiFu
 * @date 2023-07-14 14:34:29
 * @Description 订单中所包含的商品(OrderItem)表服务接口
 */
public interface OrderItemService extends IService<OrderItemEntity> {
    /**
     * 新增数据
     *
     * @param OrderItem 实体对象
     * @return 新增结果
     */
    boolean save(OrderItemDto OrderItem);

    /**
     * 修改数据
     *
     * @param OrderItem 实体对象
     * @return 修改结果
     */
    boolean updateById(OrderItemDto OrderItem);
}
