package org.example.modules.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.order.entity.OrderSettingEntity;
import org.example.modules.order.entity.dto.OrderSettingDto;
import org.example.modules.order.entity.vo.OrderSettingVo;

/**
 * Created by Dou-Fu-10 2023-08-03 23:22:56
 *
 * @author Dou-Fu-10
 * @date 2023-08-03 23:22:56
 * @Description 订单设置表(OrderSetting)表服务接口
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {
    /**
     * 新增数据
     *
     * @param orderSettingDto 实体对象
     * @return 新增结果
     */
    Boolean save(OrderSettingDto orderSettingDto);

    /**
     * 修改数据
     *
     * @param orderSettingDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(OrderSettingDto orderSettingDto);

    /**
     * 获取第一条数据
     *
     * @return 第一条数据
     */
    OrderSettingVo getOne();

    /**
     * 分页查询所有数据
     *
     * @param page            分页对象
     * @param orderSettingDto 查询实体
     * @return 所有数据
     */
    Page<OrderSettingVo> page(Page<OrderSettingEntity> page, OrderSettingDto orderSettingDto);
}
