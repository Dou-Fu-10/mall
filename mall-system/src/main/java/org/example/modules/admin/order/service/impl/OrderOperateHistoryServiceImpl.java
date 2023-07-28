package org.example.modules.admin.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.admin.order.entity.OrderOperateHistoryEntity;
import org.example.modules.admin.order.entity.dto.OrderOperateHistoryDto;
import org.example.modules.admin.order.entity.vo.OrderOperateHistoryVo;
import org.example.modules.admin.order.mapper.OrderOperateHistoryMapper;
import org.example.modules.admin.order.service.OrderOperateHistoryService;
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
        OrderOperateHistoryEntity orderOperateHistoryEntity = BeanCopy.convert(orderOperateHistory, OrderOperateHistoryEntity.class);
        return orderOperateHistoryEntity.insert();
    }

    @Override
    public boolean updateById(OrderOperateHistoryDto orderOperateHistory) {
        OrderOperateHistoryEntity orderOperateHistoryEntity = BeanCopy.convert(orderOperateHistory, OrderOperateHistoryEntity.class);
        return orderOperateHistoryEntity.updateById();
    }

    @Override
    public List<OrderOperateHistoryVo> getOrderOperateHistoryByOrderId(Long orderId) {
        List<OrderOperateHistoryEntity> list = lambdaQuery().eq(OrderOperateHistoryEntity::getOrderId, orderId).list();
        return BeanCopy.copytList(list, OrderOperateHistoryVo.class);
    }
}

