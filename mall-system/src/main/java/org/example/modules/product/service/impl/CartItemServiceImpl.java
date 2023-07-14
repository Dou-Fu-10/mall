package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.product.entity.CartItemEntity;
import org.example.modules.product.entity.dto.CartItemDto;
import org.example.modules.product.mapper.CartItemMapper;
import org.example.modules.product.service.CartItemService;
import org.springframework.stereotype.Service;

/**
 * Created by PanShiFu 2023-07-14 14:35:11
 *
 * @author PanShiFu
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

