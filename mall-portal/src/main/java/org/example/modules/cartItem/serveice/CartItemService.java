package org.example.modules.cartItem.serveice;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.cartItem.entity.CartItemEntity;
import org.example.modules.cartItem.entity.dto.CartItemDto;

/**
 * Created by Dou-Fu-10 2023-07-14 14:35:11
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:35:11
 * @Description 购物车表(CartItem)表服务接口
 */
public interface CartItemService extends IService<CartItemEntity> {
    /**
     * 新增数据
     *
     * @param cartItem 实体对象
     * @return 新增结果
     */
    boolean save(CartItemDto cartItem);

    /**
     * 修改数据
     *
     * @param cartItem 实体对象
     * @return 修改结果
     */
    boolean updateById(CartItemDto cartItem);
}
