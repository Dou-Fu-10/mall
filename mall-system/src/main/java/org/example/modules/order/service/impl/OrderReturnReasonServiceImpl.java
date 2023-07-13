package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.order.entity.OrderReturnReasonEntity;
import org.example.modules.order.mapper.OrderReturnReasonMapper;
import org.example.modules.order.service.OrderReturnReasonService;

/**
 * Created by PanShiFu 2023-07-13 15:31:36
 *
 * @author PanShiFu
 * @date 2023-07-13 15:31:36
 * @Description 退货原因表(OrderReturnReason)表服务实现类
 */
@Service("orderReturnReasonService")
public class OrderReturnReasonServiceImpl extends ServiceImpl<OrderReturnReasonMapper, OrderReturnReasonEntity> implements OrderReturnReasonService {

}

