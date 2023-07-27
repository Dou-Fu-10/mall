package org.example.modules.portal.cartItem.serveice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.portal.cartItem.entity.CartItemEntity;
import org.example.modules.portal.cartItem.entity.dto.CartItemDto;
import org.example.modules.portal.cartItem.mapper.CartItemMapper;
import org.example.modules.portal.cartItem.serveice.CartItemService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-14 14:35:11
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:35:11
 * @Description 购物车表(CartItem)表服务实现类
 */
@Service("cartItemService")
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItemEntity> implements CartItemService {
    @Override
    public boolean save(CartItemDto CartItem) {
        return false;
    }

    @Override
    public boolean updateById(CartItemDto CartItem) {
        return false;
    }
}

