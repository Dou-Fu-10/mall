package org.example.modules.admin.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.admin.order.entity.OrderReturnReasonEntity;
import org.example.modules.admin.order.entity.dto.OrderReturnReasonDto;

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
    boolean save(OrderReturnReasonDto orderReturnReason);

    /**
     * 修改数据
     *
     * @param orderReturnReason 实体对象
     * @return 修改结果
     */
    boolean updateById(OrderReturnReasonDto orderReturnReason);
}
