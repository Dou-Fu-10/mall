package org.example.modules.cartItem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.cartItem.entity.CartItemEntity;

/**
 * Created by Dou-Fu-10 2023-07-14 14:35:11
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:35:11
 * @Description 购物车表(CartItem)表数据库访问层
 */
@Mapper
public interface CartItemMapper extends BaseMapper<CartItemEntity> {

}

