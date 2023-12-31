package org.example.modules.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.order.entity.OrderOperateHistoryEntity;
import org.example.modules.order.entity.dto.OrderOperateHistoryDto;
import org.example.modules.order.entity.vo.OrderOperateHistoryVo;

import java.util.List;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:29
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:29
 * @Description 订单操作历史记录(OrderOperateHistory)表服务接口
 */
public interface OrderOperateHistoryService extends IService<OrderOperateHistoryEntity> {
    /**
     * 新增数据
     *
     * @param orderOperateHistory 实体对象
     * @return 新增结果
     */
    boolean save(OrderOperateHistoryDto orderOperateHistory);

    /**
     * 修改数据
     *
     * @param orderOperateHistory 实体对象
     * @return 修改结果
     */
    boolean updateById(OrderOperateHistoryDto orderOperateHistory);

    /**
     * 通过订单Id获取订单操作历史记录
     *
     * @param orderId 订单id
     * @return 订单操作历史记录
     */
    List<OrderOperateHistoryVo> getOrderOperateHistoryByOrderId(Long orderId);

    void addOperationRecord(Set<Long> orderEntityIds);
}
