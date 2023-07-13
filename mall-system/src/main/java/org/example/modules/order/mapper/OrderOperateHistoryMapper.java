package org.example.modules.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.order.entity.OrderOperateHistoryEntity;

/**
 * Created by PanShiFu 2023-07-13 14:31:57
 *
 * @author PanShiFu
 * @date 2023-07-13 14:31:57
 * @Description 订单操作历史记录(OrderOperateHistory)表数据库访问层
 */
@Mapper
public interface OrderOperateHistoryMapper extends BaseMapper<OrderOperateHistoryEntity> {

}

