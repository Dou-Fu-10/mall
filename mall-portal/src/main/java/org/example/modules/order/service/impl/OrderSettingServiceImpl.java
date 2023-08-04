package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.order.entity.OrderSettingEntity;
import org.example.modules.order.entity.dto.OrderSettingDto;
import org.example.modules.order.entity.vo.OrderSettingVo;
import org.example.modules.order.mapper.OrderSettingMapper;
import org.example.modules.order.service.OrderSettingService;

/**
 * Created by Dou-Fu-10 2023-08-03 23:22:56
 *
 * @author Dou-Fu-10
 * @date 2023-08-03 23:22:56
 * @Description 订单设置表(OrderSetting)表服务实现类
 */
@Service("orderSettingService")
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSettingEntity> implements OrderSettingService {
    @Override
    public Boolean save(OrderSettingDto orderSettingDto) {
        OrderSettingEntity orderSettingEntity = BeanCopy.convert(orderSettingDto, OrderSettingEntity.class);
        return save(orderSettingEntity);
    }

    @Override
    public Boolean updateById(OrderSettingDto orderSettingDto) {
        OrderSettingEntity orderSettingEntity = BeanCopy.convert(orderSettingDto, OrderSettingEntity.class);
        return updateById(orderSettingEntity);
    }

    @Override
    public OrderSettingVo getOne() {
        OrderSettingEntity orderSettingEntity = lambdaQuery().isNotNull(OrderSettingEntity::getId).one();
        return BeanCopy.convert(orderSettingEntity, OrderSettingVo.class);
    }

    @Override
    public Page<OrderSettingVo> page(Page<OrderSettingEntity> page, OrderSettingDto orderSettingDto) {
        OrderSettingEntity orderSettingEntity = BeanCopy.convert(orderSettingDto, OrderSettingEntity.class);
        LambdaQueryWrapper<OrderSettingEntity> orderSettingEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(orderSettingEntity);
        Page<OrderSettingEntity> orderSettingEntityPage = page(page, orderSettingEntityLambdaQueryWrapper);
        IPage<OrderSettingVo> orderSettingEntityPageVoIpage = orderSettingEntityPage.convert(orderSetting -> BeanCopy.convert(orderSetting, OrderSettingVo.class));
        return (Page) orderSettingEntityPageVoIpage;
    }
}

