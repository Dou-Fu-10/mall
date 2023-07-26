package org.example.modules.admin.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.admin.order.service.OrderReturnApplyService;
import org.example.modules.admin.order.entity.OrderReturnApplyEntity;
import org.example.modules.admin.order.entity.dto.OrderReturnApplyDto;
import org.example.modules.admin.order.mapper.OrderReturnApplyMapper;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:30
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:30
 * @Description 订单退货申请(OrderReturnApply)表服务实现类
 */
@Service("orderReturnApplyService")
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyMapper, OrderReturnApplyEntity> implements OrderReturnApplyService {
    @Override
    public boolean save(OrderReturnApplyDto OrderReturnApply) {
        return false;
    }

    @Override
    public boolean updateById(OrderReturnApplyDto OrderReturnApply) {
        return false;
    }
}

