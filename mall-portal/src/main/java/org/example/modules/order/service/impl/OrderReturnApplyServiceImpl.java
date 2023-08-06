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

import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-08-05 17:04:10
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 17:04:10
 * @Description 订单退货申请(OrderReturnApply)表服务实现类
 */
@Service("orderReturnApplyService")
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyMapper, OrderReturnApplyEntity> implements OrderReturnApplyService {
    @Override
    public Boolean save(OrderReturnApplyDto orderReturnApplyDto) {
        OrderReturnApplyEntity orderReturnApplyEntity = BeanCopy.convert(orderReturnApplyDto, OrderReturnApplyEntity.class);
        orderReturnApplyEntity.setCreateTime(new Date());
        orderReturnApplyEntity.setStatus(0);
        return save(orderReturnApplyEntity);
    }

    @Override
    public Boolean updateById(OrderReturnApplyDto orderReturnApplyDto) {
        OrderReturnApplyEntity orderReturnApplyEntity = BeanCopy.convert(orderReturnApplyDto, OrderReturnApplyEntity.class);
        return updateById(orderReturnApplyEntity);
    }

    @Override
    public Page<OrderReturnApplyVo> page(Page<OrderReturnApplyEntity> page, OrderReturnApplyDto orderReturnApplyDto) {
        OrderReturnApplyEntity orderReturnApplyEntity = BeanCopy.convert(orderReturnApplyDto, OrderReturnApplyEntity.class);
        LambdaQueryWrapper<OrderReturnApplyEntity> orderReturnApplyEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(orderReturnApplyEntity);
        Page<OrderReturnApplyEntity> orderReturnApplyEntityPage = page(page, orderReturnApplyEntityLambdaQueryWrapper);
        IPage<OrderReturnApplyVo> orderReturnApplyEntityPageVoIpage = orderReturnApplyEntityPage.convert(orderReturnApply -> BeanCopy.convert(orderReturnApply, OrderReturnApplyVo.class));
        return (Page) orderReturnApplyEntityPageVoIpage;
    }
}

