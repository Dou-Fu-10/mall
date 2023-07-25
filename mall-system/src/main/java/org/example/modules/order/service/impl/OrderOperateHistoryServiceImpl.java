package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.order.entity.OrderItemEntity;
import org.example.modules.order.entity.OrderOperateHistoryEntity;
import org.example.modules.order.entity.dto.OrderOperateHistoryDto;
import org.example.modules.order.entity.vo.OrderItemVo;
import org.example.modules.order.entity.vo.OrderOperateHistoryVo;
import org.example.modules.order.mapper.OrderOperateHistoryMapper;
import org.example.modules.order.service.OrderOperateHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:29
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:29
 * @Description 订单操作历史记录(OrderOperateHistory)表服务实现类
 */
@Service("orderOperateHistoryService")
public class OrderOperateHistoryServiceImpl extends ServiceImpl<OrderOperateHistoryMapper, OrderOperateHistoryEntity> implements OrderOperateHistoryService {
    @Override
    public boolean save(OrderOperateHistoryDto orderOperateHistory) {
        return false;
    }

    @Override
    public boolean updateById(OrderOperateHistoryDto orderOperateHistory) {
        return false;
    }

    @Override
    public List<OrderOperateHistoryVo> getOrderOperateHistoryByOrderId(Long orderId) {
        List<OrderOperateHistoryEntity> list = lambdaQuery().eq(OrderOperateHistoryEntity::getOrderId, orderId).list();
        return BeanCopy.copytList(list, OrderOperateHistoryVo.class);
    }
}

