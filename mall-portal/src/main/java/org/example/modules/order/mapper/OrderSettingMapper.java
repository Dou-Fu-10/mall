package org.example.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.order.entity.OrderSettingEntity;

/**
 * Created by Dou-Fu-10 2023-08-03 23:22:56
 *
 * @author Dou-Fu-10
 * @date 2023-08-03 23:22:56
 * @Description 订单设置表(OrderSetting)表数据库访问层
 */
@Mapper
public interface OrderSettingMapper extends BaseMapper<OrderSettingEntity> {

}

