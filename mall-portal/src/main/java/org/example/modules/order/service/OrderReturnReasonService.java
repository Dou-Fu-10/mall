package org.example.modules.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.order.entity.OrderReturnReasonEntity;
import org.example.modules.order.entity.dto.OrderReturnReasonDto;
import org.example.modules.order.entity.vo.OrderReturnReasonVo;

/**
 * 退货原因表(OrderReturnReason)表服务接口
 * Created by Dou-Fu-10 2023-08-11 20:20:01
 *
 * @author Dou-Fu-10
 * @date 2023-08-11 20:20:01
 * @Description 退货原因表(OrderReturnReason)表服务接口
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {
    /**
     * 新增数据
     *
     * @param orderReturnReasonDto 实体对象
     * @return 新增结果
     */
    Boolean save(OrderReturnReasonDto orderReturnReasonDto);

    /**
     * 修改数据
     *
     * @param orderReturnReasonDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(OrderReturnReasonDto orderReturnReasonDto);

    /**
     * 分页查询所有数据
     *
     * @param page                 分页对象
     * @param orderReturnReasonDto 查询实体
     * @return 所有数据
     */
    Page<OrderReturnReasonVo> page(Page<OrderReturnReasonEntity> page, OrderReturnReasonDto orderReturnReasonDto);

    /**
     * 获取退货原因
     * @param orderReturnReasonId 退货原因 ID
     * @return /
     */
    OrderReturnReasonVo getByOrderReturnApplyId(Long orderReturnReasonId);
}
