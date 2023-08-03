package org.example.modules.order.service;

import org.example.modules.order.entity.OrderEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.modules.order.entity.dto.OrderDto;
import org.example.modules.order.entity.vo.OrderVo;

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
}
