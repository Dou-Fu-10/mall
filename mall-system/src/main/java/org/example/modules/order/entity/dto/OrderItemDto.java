package org.example.modules.order.entity.dto;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by PanShiFu 2023-07-13 14:31:57
 *
 * @author PanShiFu
 * @date 2023-07-13 14:31:57
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
     * 订单编号
     */
    private String orderSn;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 商品图片
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
    private Double productPrice;
    /**
     * 购买数量
     */
    private Integer productQuantity;
    /**
     * 商品sku编号
     */
    private Long productSkuId;
    /**
     * 商品sku条码
     */
    private String productSkuCode;
    /**
     * 商品分类id
     */
    private Long productCategoryId;
    /**
     * 商品促销名称
     */
    private String promotionName;
    /**
     * 商品促销分解金额
     */
    private Double promotionAmount;
    /**
     * 积分优惠分解金额
     */
    private Double integrationAmount;
    /**
     * 该商品经过优惠后的分解金额
     */
    private Double realAmount;
    private Integer giftIntegration;
    private Integer giftGrowth;
    /**
     * 商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
     */
    private String productAttr;


}

