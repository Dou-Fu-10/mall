package org.example.modules.product.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Dou-Fu-10 2023-07-15 11:35:49
 *
 * @author Dou-Fu-10
 * @date 2023-07-15 11:35:49
 * @Description sku的库存(SkuStock)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_sku_stock")
@Schema(name = "pms_sku_stock", description = "sku的库存(SkuStock)表实体类")
public class SkuStockEntity extends CommonEntity<SkuStockEntity> implements Serializable {
    @TableId
    private Long id;
    /**
     * 商品id
     */
    private Long productId;

    /**
     * sku编码
     */
    @Schema(name = "skuCode", description = "sku编码")
    private String skuCode;
    /**
     * 价格
     */
    @Schema(name = "price", description = "价格")
    private BigDecimal price;
    /**
     * 库存
     */
    @Schema(name = "stock", description = "库存")
    private Integer stock;
    /**
     * 预警库存
     */
    @Schema(name = "lowStock", description = "预警库存")
    private Integer lowStock;
    /**
     * 展示图片
     */
    @Schema(name = "pic", description = "展示图片")
    private String pic;
    /**
     * 销量
     */
    @Schema(name = "sale", description = "销量")
    private Integer sale;
    /**
     * 锁定库存
     */
    @Schema(name = "lockStock", description = "锁定库存")
    private Integer lockStock;
    /**
     * 商品销售属性，json格式
     */
    @Schema(name = "spData", description = "商品销售属性，json格式")
    private String spData;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @Schema(name = "deleteFlag", description = "逻辑删除（1 代表已删除），（0 代表未删除）")
    private Integer deleteFlag;

}

