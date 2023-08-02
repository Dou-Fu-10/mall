package org.example.modules.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.order.entity.OrderReturnReasonEntity;
import org.example.modules.order.entity.dto.OrderReturnReasonDto;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:30
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:30
 * @Description 退货原因表(OrderReturnReason)表服务接口
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {
    /**
     * 新增数据
     *
     * @param orderReturnReason 实体对象
     * @return 新增结果
     */
    Boolean save(OrderReturnReasonDto orderReturnReason);

    /**
     * 修改数据
     *
     * @param orderReturnReason 实体对象
     * @return 修改结果
     */
    Boolean updateById(OrderReturnReasonDto orderReturnReason);

    /**
     * 修改帐号状态
     *
     * @param id     用户id
     * @param status 状态
     * @return String
     */
    Boolean updateStatus(Long id, Boolean status);
}
