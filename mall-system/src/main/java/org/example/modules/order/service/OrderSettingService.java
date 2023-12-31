package org.example.modules.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.order.entity.OrderSettingEntity;
import org.example.modules.order.entity.dto.OrderSettingDto;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:31
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:31
 * @Description 订单设置表(OrderSetting)表服务接口
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {
    /**
     * 新增数据
     *
     * @param orderSetting 实体对象
     * @return 新增结果
     */
    boolean save(OrderSettingDto orderSetting);

    /**
     * 修改数据
     *
     * @param orderSetting 实体对象
     * @return 修改结果
     */
    boolean updateById(OrderSettingDto orderSetting);
}
