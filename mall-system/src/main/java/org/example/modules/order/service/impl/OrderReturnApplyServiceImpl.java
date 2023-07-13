package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.order.entity.OrderReturnApplyEntity;
import org.example.modules.order.mapper.OrderReturnApplyMapper;
import org.example.modules.order.service.OrderReturnApplyService;

/**
 * Created by Dou-Fu-10 2023-07-13 15:31:36
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:31:36
 * @Description 订单退货申请(OrderReturnApply)表服务实现类
 */
@Service("orderReturnApplyService")
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyMapper, OrderReturnApplyEntity> implements OrderReturnApplyService {

}

