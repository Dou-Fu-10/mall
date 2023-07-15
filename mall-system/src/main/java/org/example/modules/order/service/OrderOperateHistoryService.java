package org.example.modules.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.order.entity.OrderOperateHistoryEntity;
import org.example.modules.order.entity.dto.OrderOperateHistoryDto;

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
     * @param OrderOperateHistory 实体对象
     * @return 新增结果
     */
    boolean save(OrderOperateHistoryDto OrderOperateHistory);

    /**
     * 修改数据
     *
     * @param OrderOperateHistory 实体对象
     * @return 修改结果
     */
    boolean updateById(OrderOperateHistoryDto OrderOperateHistory);
}
