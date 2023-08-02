package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.order.entity.OrderReturnApplyEntity;
import org.example.modules.order.entity.dto.OrderReturnApplyDto;
import org.example.modules.order.entity.vo.OrderReturnApplyVo;
import org.example.modules.order.mapper.OrderReturnApplyMapper;
import org.example.modules.order.service.OrderReturnApplyService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:30
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:30
 * @Description 订单退货申请(OrderReturnApply)表服务实现类
 */
@Service("orderReturnApplyService")
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyMapper, OrderReturnApplyEntity> implements OrderReturnApplyService {
    @Override
    public boolean save(OrderReturnApplyDto orderReturnApply) {
        OrderReturnApplyEntity orderReturnApplyEntity = BeanCopy.convert(orderReturnApply, OrderReturnApplyEntity.class);
        return orderReturnApplyEntity.insert();
    }

    @Override
    public boolean updateById(OrderReturnApplyDto orderReturnApply) {
        OrderReturnApplyEntity orderReturnApplyEntity = BeanCopy.convert(orderReturnApply, OrderReturnApplyEntity.class);
        return orderReturnApplyEntity.insert();
    }

    @Override
    public Page<OrderReturnApplyVo> page(Page<OrderReturnApplyEntity> page, OrderReturnApplyDto orderReturnApply) {
        OrderReturnApplyEntity orderReturnApplyEntity = BeanCopy.convert(orderReturnApply, OrderReturnApplyEntity.class);
        LambdaQueryWrapper<OrderReturnApplyEntity> orderEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(orderReturnApplyEntity);
        orderEntityLambdaQueryWrapper.orderByAsc(OrderReturnApplyEntity::getId);
        Page<OrderReturnApplyEntity> orderReturnApplyEntityPage = page(page, orderEntityLambdaQueryWrapper);
        IPage<OrderReturnApplyVo> orderReturnApplyVoIpage = orderReturnApplyEntityPage.convert(order -> BeanCopy.convert(order, OrderReturnApplyVo.class));
        return (Page) orderReturnApplyVoIpage;
    }
}

