package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.order.entity.OrderReturnReasonEntity;
import org.example.modules.order.entity.dto.OrderReturnReasonDto;
import org.example.modules.order.mapper.OrderReturnReasonMapper;
import org.example.modules.order.service.OrderReturnReasonService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:30
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:30
 * @Description 退货原因表(OrderReturnReason)表服务实现类
 */
@Service("orderReturnReasonService")
public class OrderReturnReasonServiceImpl extends ServiceImpl<OrderReturnReasonMapper, OrderReturnReasonEntity> implements OrderReturnReasonService {
    @Override
    public Boolean save(OrderReturnReasonDto orderReturnReason) {
        OrderReturnReasonEntity orderReturnReasonEntity = BeanCopy.convert(orderReturnReason, OrderReturnReasonEntity.class);
        return orderReturnReasonEntity.insert();
    }

    @Override
    public Boolean updateById(OrderReturnReasonDto orderReturnReason) {
        OrderReturnReasonEntity orderReturnReasonEntity = BeanCopy.convert(orderReturnReason, OrderReturnReasonEntity.class);
        return orderReturnReasonEntity.updateById();
    }

    @Override
    public Boolean updateStatus(Long id, Boolean status) {
        if (Objects.isNull(id) || Objects.isNull(status)) {
            return false;
        }
        OrderReturnReasonEntity orderReturnReasonEntity = new OrderReturnReasonEntity();
        orderReturnReasonEntity.setStatus(status);
        orderReturnReasonEntity.setId(id);
        return orderReturnReasonEntity.updateById();
    }
}

