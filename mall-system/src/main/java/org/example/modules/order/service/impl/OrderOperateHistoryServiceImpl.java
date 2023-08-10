package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.order.entity.OrderOperateHistoryEntity;
import org.example.modules.order.entity.dto.OrderOperateHistoryDto;
import org.example.modules.order.entity.vo.OrderOperateHistoryVo;
import org.example.modules.order.mapper.OrderOperateHistoryMapper;
import org.example.modules.order.service.OrderOperateHistoryService;
import org.example.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
        if (Objects.isNull(orderId)) {
            return new ArrayList<>();
        }
        List<OrderOperateHistoryEntity> orderOperateHistoryEntityList = lambdaQuery().eq(OrderOperateHistoryEntity::getOrderId, orderId).list();
        return BeanCopy.copytList(orderOperateHistoryEntityList, OrderOperateHistoryVo.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void addOperationRecord(Set<Long> orderEntityIds) {
        if (CollectionUtils.isEmpty(orderEntityIds)) {
            throw new BaseRequestException("参数传输失败");
        }
        //添加操作记录
        Set<OrderOperateHistoryEntity> orderOperateHistoryEntitySet = orderEntityIds.stream().map(orderId -> {
            OrderOperateHistoryEntity history = new OrderOperateHistoryEntity();
            history.setOrderId(orderId);
            history.setCreateTime(new Date());
            history.setOperateMan(SecurityUtils.getCurrentUsername());
            history.setOrderStatus(2);
            history.setNote("完成发货");
            return history;
        }).collect(Collectors.toSet());
        if (!saveBatch(orderOperateHistoryEntitySet)) {
            throw new BaseRequestException("添加操作记录失败");
        }
    }
}

