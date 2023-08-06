package org.example.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.order.entity.OrderReturnApplyEntity;

/**
 * Created by Dou-Fu-10 2023-08-05 17:04:10
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 17:04:10
 * @Description 订单退货申请(OrderReturnApply)表数据库访问层
 */
@Mapper
public interface OrderReturnApplyMapper extends BaseMapper<OrderReturnApplyEntity> {

}

