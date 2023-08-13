package org.example.modules.cartItem.entity.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Long productId;
    /**
     * 商品SKUid
     */
    @NotNull
    private Long productSkuId;
    /**
     * 购买数量
     */
    @DecimalMin(value = "0", message = "请输入正确的购买数量")
    @DecimalMax(value = "100", message = "请输入正确的购买数量")
    @NotNull
    private Integer quantity;
}
