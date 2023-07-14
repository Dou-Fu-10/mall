package org.example.modules.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.product.entity.CartItemEntity;
import org.example.modules.product.entity.dto.CartItemDto;

/**
 * Created by PanShiFu 2023-07-14 14:35:11
 *
 * @author PanShiFu
 * @date 2023-07-14 14:35:11
 * @Description 购物车表(CartItem)表服务接口
 */
public interface CartItemService extends IService<CartItemEntity> {
    /**
     * 新增数据
     *
     * @param CartItem 实体对象
     * @return 新增结果
     */
    boolean save(CartItemDto CartItem);

    /**
     * 修改数据
     *
     * @param CartItem 实体对象
     * @return 修改结果
     */
    boolean updateById(CartItemDto CartItem);
}
