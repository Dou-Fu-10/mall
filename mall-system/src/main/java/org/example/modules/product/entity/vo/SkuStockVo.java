package org.example.modules.product.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by Dou-Fu-10 2023-07-15 11:35:50
 *
 * @author Dou-Fu-10
 * @date 2023-07-15 11:35:50
 * @Description sku的库存(SkuStock)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuStockVo {
    private Long id;
    /**
     * 商品id
     */
    private Long productId;

    /**
     * sku编码
     */
    private String skuCode;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 预警库存
     */
    private Integer lowStock;
    /**
     * 展示图片
     */
    private String pic;
    /**
     * 销量
     */
    private Integer sale;
    /**
     * 锁定库存
     */
    private Integer lockStock;
    /**
     * 商品销售属性，json格式
     */
    private String spData;


}

