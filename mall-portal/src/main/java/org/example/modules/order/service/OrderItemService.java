package org.example.modules.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.order.entity.OrderItemEntity;
import org.example.modules.order.entity.dto.OrderItemDto;
import org.example.modules.order.entity.vo.OrderItemVo;

import java.util.List;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-08-04 11:32:58
 *
 * @author Dou-Fu-10
 * @date 2023-08-04 11:32:58
 * @Description 订单中所包含的商品(OrderItem)表服务接口
 */
public interface OrderItemService extends IService<OrderItemEntity> {
    /**
     * 新增数据
     *
     * @param orderItemDto 实体对象
     * @return 新增结果
     */
    Boolean save(OrderItemDto orderItemDto);

    /**
     * 修改数据
     *
     * @param orderItemDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(OrderItemDto orderItemDto);

    /**
     * 分页查询所有数据
     *
     * @param page         分页对象
     * @param orderItemDto 查询实体
     * @return 所有数据
     */
    Page<OrderItemVo> page(Page<OrderItemEntity> page, OrderItemDto orderItemDto);

    /**
     * 通过订单 id列表 查询 订单中所包含的商品
     *
     * @param orderIds 订单id列表
     * @return 订单中所包含的商品
     */
    List<OrderItemVo> getByOrderIds(Set<Long> orderIds);

}
