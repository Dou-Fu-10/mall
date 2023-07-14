package org.example.modules.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.order.entity.OrderReturnApplyEntity;
import org.example.modules.order.entity.dto.OrderReturnApplyDto;

/**
 * Created by PanShiFu 2023-07-14 14:34:30
 *
 * @author PanShiFu
 * @date 2023-07-14 14:34:30
 * @Description 订单退货申请(OrderReturnApply)表服务接口
 */
public interface OrderReturnApplyService extends IService<OrderReturnApplyEntity> {
    /**
     * 新增数据
     *
     * @param OrderReturnApply 实体对象
     * @return 新增结果
     */
    boolean save(OrderReturnApplyDto OrderReturnApply);

    /**
     * 修改数据
     *
     * @param OrderReturnApply 实体对象
     * @return 修改结果
     */
    boolean updateById(OrderReturnApplyDto OrderReturnApply);
}
