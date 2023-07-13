package org.example.modules.product.entity.dto;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by PanShiFu 2023-07-13 15:36:00
 *
 * @author PanShiFu
 * @date 2023-07-13 15:36:00
 * @Description sku的库存(SkuStock)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuStockDto {
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
    private Double price;
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
     * 单品促销价格
     */
    private Double promotionPrice;
    /**
     * 锁定库存
     */
    private Integer lockStock;
    /**
     * 商品销售属性，json格式
     */
    private String spData;


}

