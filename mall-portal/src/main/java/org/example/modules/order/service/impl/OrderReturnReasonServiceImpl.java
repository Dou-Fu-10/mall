package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.order.entity.OrderReturnReasonEntity;
import org.example.modules.order.entity.dto.OrderReturnReasonDto;
import org.example.modules.order.entity.vo.OrderReturnReasonVo;
import org.example.modules.order.mapper.OrderReturnReasonMapper;
import org.example.modules.order.service.OrderReturnReasonService;
import org.springframework.stereotype.Service;

/**
 * 退货原因表(OrderReturnReason)表服务实现类
 * Created by Dou-Fu-10 2023-08-11 20:20:01
 *
 * @author Dou-Fu-10
 * @date 2023-08-11 20:20:01
 * @Description 退货原因表(OrderReturnReason)表服务实现类
 */
@Service("orderReturnReasonService")
public class OrderReturnReasonServiceImpl extends ServiceImpl<OrderReturnReasonMapper, OrderReturnReasonEntity> implements OrderReturnReasonService {
    @Override
    public Boolean save(OrderReturnReasonDto orderReturnReasonDto) {
        OrderReturnReasonEntity orderReturnReasonEntity = BeanCopy.convert(orderReturnReasonDto, OrderReturnReasonEntity.class);
        return save(orderReturnReasonEntity);
    }

    @Override
    public Boolean updateById(OrderReturnReasonDto orderReturnReasonDto) {
        OrderReturnReasonEntity orderReturnReasonEntity = BeanCopy.convert(orderReturnReasonDto, OrderReturnReasonEntity.class);
        return updateById(orderReturnReasonEntity);
    }

    @Override
    public Page<OrderReturnReasonVo> page(Page<OrderReturnReasonEntity> page, OrderReturnReasonDto orderReturnReasonDto) {
        OrderReturnReasonEntity orderReturnReasonEntity = BeanCopy.convert(orderReturnReasonDto, OrderReturnReasonEntity.class);
        LambdaQueryWrapper<OrderReturnReasonEntity> orderReturnReasonEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(orderReturnReasonEntity);
        orderReturnReasonEntityLambdaQueryWrapper.eq(OrderReturnReasonEntity::getStatus, true);
        Page<OrderReturnReasonEntity> orderReturnReasonEntityPage = page(page, orderReturnReasonEntityLambdaQueryWrapper);
        IPage<OrderReturnReasonVo> orderReturnReasonEntityPageVoIPage = orderReturnReasonEntityPage.convert(orderReturnReason -> BeanCopy.convert(orderReturnReason, OrderReturnReasonVo.class));
        return (Page<OrderReturnReasonVo>) orderReturnReasonEntityPageVoIPage;
    }

    @Override
    public OrderReturnReasonVo getByOrderReturnApplyId(Long orderReturnReasonId) {
        LambdaQueryWrapper<OrderReturnReasonEntity> orderReturnReasonEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderReturnReasonEntityLambdaQueryWrapper.eq(OrderReturnReasonEntity::getStatus, true);
        orderReturnReasonEntityLambdaQueryWrapper.eq(OrderReturnReasonEntity::getId, orderReturnReasonId);
        return BeanCopy.convert(getOne(orderReturnReasonEntityLambdaQueryWrapper), OrderReturnReasonVo.class);
    }
}

