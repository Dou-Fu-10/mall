package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.order.entity.OrderSettingEntity;
import org.example.modules.order.entity.dto.OrderSettingDto;
import org.example.modules.order.mapper.OrderSettingMapper;
import org.example.modules.order.service.OrderSettingService;
import org.springframework.stereotype.Service;

/**
 * Created by PanShiFu 2023-07-14 14:34:31
 *
 * @author PanShiFu
 * @date 2023-07-14 14:34:31
 * @Description 订单设置表(OrderSetting)表服务实现类
 */
@Service("orderSettingService")
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSettingEntity> implements OrderSettingService {
    @Override
    public boolean save(OrderSettingDto OrderSetting) {
        return false;
    }

    @Override
    public boolean updateById(OrderSettingDto OrderSetting) {
        return false;
    }
}

