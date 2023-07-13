package org.example.modules.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.order.entity.OrderOperateHistoryEntity;

/**
 * Created by Dou-Fu-10 2023-07-13 15:31:35
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:31:35
 * @Description 订单操作历史记录(OrderOperateHistory)表数据库访问层
 */
@Mapper
public interface OrderOperateHistoryMapper extends BaseMapper<OrderOperateHistoryEntity> {

}

