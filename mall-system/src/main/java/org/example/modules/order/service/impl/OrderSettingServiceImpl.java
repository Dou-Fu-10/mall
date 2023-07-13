package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.order.entity.OrderSettingEntity;
import org.example.modules.order.mapper.OrderSettingMapper;
import org.example.modules.order.service.OrderSettingService;

/**
 * Created by PanShiFu 2023-07-13 14:31:59
 *
 * @author PanShiFu
 * @date 2023-07-13 14:31:59
 * @Description 订单设置表(OrderSetting)表服务实现类
 */
@Service("orderSettingService")
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSettingEntity> implements OrderSettingService {

}
