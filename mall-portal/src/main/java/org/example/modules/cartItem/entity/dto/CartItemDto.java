package org.example.modules.cartItem.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

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
public class CartItemDto {
    /**
     * ID
     */
    private Long id;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 商品SKUid
     */
    private Long productSkuId;
    /**
     * 商品分类
     */
    private Long productCategoryId;
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 商品编码
     */
    private String productSn;
    /**
     * 购买数量
     */
    private Integer quantity;
    /**
     * 添加到购物车的商品单价
     */
    private BigDecimal price;
    /**
     * 会员昵称
     */
    private String memberNickname;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    private Integer deleteFlag;


}

