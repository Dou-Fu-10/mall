package org.example.modules.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.order.entity.OrderReturnReasonEntity;
import org.example.modules.order.entity.dto.OrderReturnReasonDto;

/**
 * Created by PanShiFu 2023-07-14 14:34:30
 *
 * @author PanShiFu
 * @date 2023-07-14 14:34:30
 * @Description 退货原因表(OrderReturnReason)表服务接口
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {
    /**
     * 新增数据
     *
     * @param OrderReturnReason 实体对象
     * @return 新增结果
     */
    boolean save(OrderReturnReasonDto OrderReturnReason);

    /**
     * 修改数据
     *
     * @param OrderReturnReason 实体对象
     * @return 修改结果
     */
    boolean updateById(OrderReturnReasonDto OrderReturnReason);
}
