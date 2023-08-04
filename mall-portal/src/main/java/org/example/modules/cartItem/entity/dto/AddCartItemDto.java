package org.example.modules.cartItem.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Dou-Fu-10 2023-07-14 14:35:11
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:35:11
 * @Description 购物车表(CartItem)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCartItemDto {
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 商品SKUid
     */
    private Long productSkuId;
    /**
     * 购买数量
     */
    private Integer quantity;
}
