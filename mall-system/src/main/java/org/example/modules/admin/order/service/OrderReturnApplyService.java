package org.example.modules.admin.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.admin.order.entity.OrderReturnApplyEntity;
import org.example.modules.admin.order.entity.dto.OrderReturnApplyDto;
import org.example.modules.admin.order.entity.vo.OrderReturnApplyVo;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:30
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:30
 * @Description 订单退货申请(OrderReturnApply)表服务接口
 */
public interface OrderReturnApplyService extends IService<OrderReturnApplyEntity> {
    /**
     * 新增数据
     *
     * @param orderReturnApply 实体对象
     * @return 新增结果
     */
    boolean save(OrderReturnApplyDto orderReturnApply);

    /**
     * 修改数据
     *
     * @param orderReturnApply 实体对象
     * @return 修改结果
     */
    boolean updateById(OrderReturnApplyDto orderReturnApply);
    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param orderReturnApply 查询实体
     * @return 所有数据
     */
    Page<OrderReturnApplyVo> page(Page<OrderReturnApplyEntity> page, OrderReturnApplyDto orderReturnApply);
}
