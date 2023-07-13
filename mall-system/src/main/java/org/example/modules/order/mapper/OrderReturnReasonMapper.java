package org.example.modules.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.order.entity.OrderReturnReasonEntity;

/**
 * Created by Dou-Fu-10 2023-07-13 15:31:36
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:31:36
 * @Description 退货原因表(OrderReturnReason)表数据库访问层
 */
@Mapper
public interface OrderReturnReasonMapper extends BaseMapper<OrderReturnReasonEntity> {

}

