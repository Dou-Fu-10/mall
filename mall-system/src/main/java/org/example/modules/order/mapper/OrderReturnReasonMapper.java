package org.example.modules.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.order.entity.OrderReturnReasonEntity;

/**
 * Created by PanShiFu 2023-07-13 14:31:58
 *
 * @author PanShiFu
 * @date 2023-07-13 14:31:58
 * @Description 退货原因表(OrderReturnReason)表数据库访问层
 */
@Mapper
public interface OrderReturnReasonMapper extends BaseMapper<OrderReturnReasonEntity> {

}

