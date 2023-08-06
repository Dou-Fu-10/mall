package org.example.modules.order.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@TableName("oms_order_item")
@Schema(name = "oms_order_item", description = "订单中所包含的商品(OrderItem)表实体类")
public class OrderItemEntity extends CommonEntity<OrderItemEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
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
     * 商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
     */
    @Schema(name = "productAttr", description = "商品销售属性")
    private String productAttr;

    /**
     * 创建人
     * 创建
     */
    @JsonIgnore
    @TableField(exist = false)
    private String createBy;
    /**
     * 更新人
     * 创建、更新
     */
    @JsonIgnore
    @TableField(exist = false)
    private String updateBy;
    /**
     * 创建时间
     * 创建
     */
    @JsonIgnore
    @TableField(exist = false)
    private Date createTime;
    /**
     * 更新时间
     * 创建、更新
     */
    @JsonIgnore
    @TableField(exist = false)
    private Date updateTime;
}

