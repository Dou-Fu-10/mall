package org.example.modules.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.order.entity.OrderReturnApplyEntity;
import org.example.modules.order.entity.dto.OrderReturnApplyDto;
import org.example.modules.order.entity.vo.OrderReturnApplyVo;

/**
 * Created by Dou-Fu-10 2023-08-05 17:04:10
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 17:04:10
 * @Description 订单退货申请(OrderReturnApply)表服务接口
 */
public interface OrderReturnApplyService extends IService<OrderReturnApplyEntity> {
    /**
     * 新增数据
     *
     * @param orderReturnApplyDto 实体对象
     * @return 新增结果
     */
    Boolean save(OrderReturnApplyDto orderReturnApplyDto);

    /**
     * 修改数据
     *
     * @param orderReturnApplyDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(OrderReturnApplyDto orderReturnApplyDto);

    /**
     * 分页查询所有数据
     *
     * @param page                分页对象
     * @param orderReturnApplyDto 查询实体
     * @return 所有数据
     */
    Page<OrderReturnApplyVo> page(Page<OrderReturnApplyEntity> page, OrderReturnApplyDto orderReturnApplyDto);
}
