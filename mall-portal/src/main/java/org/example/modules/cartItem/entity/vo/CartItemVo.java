package org.example.modules.cartItem.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.modules.product.entity.vo.ProductVo;
import org.example.modules.product.entity.vo.SkuStockVo;

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
public class CartItemVo {
    /**
     * ID
     */
    private Long id;
    /**
     * 商品ID
     */
    @JsonIgnore
    private Long productId;
    /**
     * 商品SKUid
     */
    private Long productSkuId;
    /**
     * 商品分类
     */
    @JsonIgnore
    private Long productCategoryId;
    /**
     * 会员id
     */
    @JsonIgnore
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
    @JsonIgnore
    private String memberNickname;
    /**
     * 创建时间
     */
    @JsonIgnore
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonIgnore
    private Date updateTime;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @JsonIgnore
    private Integer deleteFlag;
    /**
     * 剩余库存
     */
    @JsonIgnore
    private Integer realStock;

    private ProductVo product;
    private SkuStockVo skuStock;

}

