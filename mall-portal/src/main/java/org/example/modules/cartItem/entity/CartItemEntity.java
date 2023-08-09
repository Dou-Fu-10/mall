package org.example.modules.cartItem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;
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
@TableName("oms_cart_item")
@Schema(name = "oms_cart_item", description = "购物车表(CartItem)表实体类")
public class CartItemEntity extends CommonEntity<CartItemEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
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
     * 会员昵称
     */
    @Schema(name = "memberNickname", description = "会员昵称")
    private String memberNickname;
    /**
     * 商品编码
     */
    @Schema(name = "productSn", description = "商品编码")
    private String productSn;
    /**
     * 购买数量
     */
    @Schema(name = "quantity", description = "购买数量")
    private Integer quantity;
    /**
     * 添加到购物车的商品单价
     */
    @Schema(name = "price", description = "添加到购物车的商品单价")
    private BigDecimal price;
    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;
    /**
     * 修改时间
     */
    @Schema(name = "updateTime", description = "修改时间")
    private Date updateTime;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @Schema(name = "deleteFlag", description = "逻辑删除（1 代表已删除），（0 代表未删除）")
    private Integer deleteFlag;
    /**
     * 创建人
     * 创建
     */
    @TableField(exist = false)
    private String createBy;
    /**
     * 更新人
     * 创建、更新
     */
    @TableField(exist = false)
    private String updateBy;

    public CartItemEntity(Long id, Long memberId) {
        this.id = id;
        this.memberId = memberId;
    }

}

