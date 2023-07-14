package org.example.modules.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.order.entity.OrderEntity;

/**
 * Created by PanShiFu 2023-07-14 14:34:28
 *
 * @author PanShiFu
 * @date 2023-07-14 14:34:28
 * @Description 订单表(Order)表数据库访问层
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {

}

