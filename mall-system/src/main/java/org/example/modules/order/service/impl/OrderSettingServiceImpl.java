package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.order.entity.OrderSettingEntity;
import org.example.modules.order.entity.dto.OrderSettingDto;
import org.example.modules.order.mapper.OrderSettingMapper;
import org.example.modules.order.service.OrderSettingService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:31
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:31
 * @Description 订单设置表(OrderSetting)表服务实现类
 */
@Service("orderSettingService")
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSettingEntity> implements OrderSettingService {
    @Override
    public boolean save(OrderSettingDto orderSetting) {
        OrderSettingEntity orderSettingEntity = BeanCopy.convert(orderSetting, OrderSettingEntity.class);
        return orderSettingEntity.insert();
    }

    @Override
    public boolean updateById(OrderSettingDto orderSetting) {
        OrderSettingEntity orderSettingEntity = BeanCopy.convert(orderSetting, OrderSettingEntity.class);
        return orderSettingEntity.updateById();
    }
}

