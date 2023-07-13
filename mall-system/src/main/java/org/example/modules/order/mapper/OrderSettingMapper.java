package org.example.modules.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.order.entity.OrderSettingEntity;

/**
 * Created by PanShiFu 2023-07-13 15:31:37
 *
 * @author PanShiFu
 * @date 2023-07-13 15:31:37
 * @Description 订单设置表(OrderSetting)表数据库访问层
 */
@Mapper
public interface OrderSettingMapper extends BaseMapper<OrderSettingEntity> {

}

