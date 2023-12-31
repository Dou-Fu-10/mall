package org.example.modules.order.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:29
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:29
 * @Description 订单中所包含的商品(OrderItem)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    /**
     * ID
     */
    private Long id;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 商品sku编号
     */
    private Long productSkuId;
    /**
     * 商品分类id
     */
    private Long productCategoryId;

    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 商品主图
     */
    private String productPic;
    /**
     * 商品名字
     */
    private String productName;
    /**
     * 商品编号
     */
    private String productSn;
    /**
     * 销售价格
     */
    private BigDecimal productPrice;
    /**
     * 购买数量
     */
    private Integer productQuantity;
    /**
     * 商品sku条码
     */
    private String productSkuCode;
    /**
     * 商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
     */
    private String productAttr;


}

