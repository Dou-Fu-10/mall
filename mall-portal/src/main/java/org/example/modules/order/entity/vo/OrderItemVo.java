package org.example.modules.order.entity.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by Dou-Fu-10 2023-08-04 11:32:58
 *
 * @author Dou-Fu-10
 * @date 2023-08-04 11:32:58
 * @Description 订单中所包含的商品(OrderItem)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemVo {
    /**
     * ID
     */
    @Schema(name = "id", description = "ID")
    private Long id;
    /**
     * 订单id
     */
    @Schema(name = "orderId", description = "订单id")
    private Long orderId;
    /**
     * 商品id
     */
    @Schema(name = "productId", description = "商品id")
    private Long productId;
    /**
     * 商品sku编号
     */
    @Schema(name = "productSkuId", description = "商品sku编号")
    private Long productSkuId;
    /**
     * 商品分类id
     */
    @Schema(name = "productCategoryId", description = "商品分类id")
    private Long productCategoryId;

    /**
     * 订单编号
     */
    @Schema(name = "orderSn", description = "订单编号")
    private String orderSn;
    /**
     * 商品图片
     */
    @Schema(name = "productPic", description = "商品图片")
    private String productPic;
    /**
     * 商品名字
     */
    @Schema(name = "productName", description = "商品名字")
    private String productName;
    /**
     * 商品编号
     */
    @Schema(name = "productSn", description = "商品编号")
    private String productSn;
    /**
     * 销售价格
     */
    @Schema(name = "productPrice", description = "销售价格")
    private BigDecimal productPrice;
    /**
     * 购买数量
     */
    @Schema(name = "productQuantity", description = "购买数量")
    private Integer productQuantity;
    /**
     * 商品sku条码
     */
    @Schema(name = "productSkuCode", description = "商品sku条码")
    private String productSkuCode;
    /**
     * 商品促销名称
     */
    @Schema(name = "promotionName", description = "商品促销名称")
    private String promotionName;
    /**
     * 商品促销分解金额
     */
    @Schema(name = "promotionAmount", description = "商品促销分解金额")
    private BigDecimal promotionAmount;
    /**
     * 积分优惠分解金额
     */
    @Schema(name = "integrationAmount", description = "积分优惠分解金额")
    private BigDecimal integrationAmount;
    /**
     * 该商品经过优惠后的分解金额
     */
    @Schema(name = "realAmount", description = "该商品经过优惠后的分解金额")
    private BigDecimal realAmount;
    /**
     * 商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
     */
    @Schema(name = "productAttr", description = "")
    private String productAttr;


}

