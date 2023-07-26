package org.example.modules.admin.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.admin.order.entity.OrderEntity;
import org.example.modules.admin.order.entity.dto.OrderDto;
import org.example.modules.admin.order.entity.vo.OrderVo;

import java.io.Serializable;

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
    boolean save(OrderDto order);

    /**
     * 修改数据
     *
     * @param order 实体对象
     * @return 修改结果
     */
    boolean updateById(OrderDto order);

    /**
     * 获取订单信息
     *
     * @param id 订单id
     * @return 订单信息
     */
    OrderVo getOrderById(Serializable id);
}
