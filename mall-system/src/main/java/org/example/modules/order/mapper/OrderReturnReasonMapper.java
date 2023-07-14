package org.example.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.order.entity.OrderReturnReasonEntity;

/**
 * Created by PanShiFu 2023-07-14 14:34:30
 *
 * @author PanShiFu
 * @date 2023-07-14 14:34:30
 * @Description 退货原因表(OrderReturnReason)表数据库访问层
 */
@Mapper
public interface OrderReturnReasonMapper extends BaseMapper<OrderReturnReasonEntity> {

}

