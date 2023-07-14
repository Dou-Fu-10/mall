package org.example.modules.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.order.entity.OrderEntity;
import org.example.modules.order.entity.dto.OrderDto;

/**
 * Created by PanShiFu 2023-07-14 14:34:28
 *
 * @author PanShiFu
 * @date 2023-07-14 14:34:28
 * @Description 订单表(Order)表服务接口
 */
public interface OrderService extends IService<OrderEntity> {
    /**
     * 新增数据
     *
     * @param Order 实体对象
     * @return 新增结果
     */
    boolean save(OrderDto Order);

    /**
     * 修改数据
     *
     * @param Order 实体对象
     * @return 修改结果
     */
    boolean updateById(OrderDto Order);
}
