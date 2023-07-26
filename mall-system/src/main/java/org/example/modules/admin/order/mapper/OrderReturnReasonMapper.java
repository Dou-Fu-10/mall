package org.example.modules.admin.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.admin.order.entity.OrderReturnReasonEntity;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:30
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:30
 * @Description 退货原因表(OrderReturnReason)表数据库访问层
 */
@Mapper
public interface OrderReturnReasonMapper extends BaseMapper<OrderReturnReasonEntity> {

}

