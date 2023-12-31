package org.example.modules.product.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.modules.product.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-08-01 22:28:59
 *
 * @author Dou-Fu-10
 * @date 2023-08-01 22:28:59
 * @Description sku的库存(SkuStock)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuStockVo {
    /**
     * ID
     */
    @Schema(name = "id", description = "ID")
    private Long id;
    /**
     * 商品id
     */
    @JsonIgnore
    @Schema(name = "productId", description = "商品id")
    private Long productId;

    /**
     * sku编码
     */
    @JsonIgnore
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
    @JsonIgnore
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
    @JsonIgnore
    @Schema(name = "lockStock", description = "锁定库存")
    private Integer lockStock;
    /**
     * 商品销售属性，json格式
     */
    @Schema(name = "spData", description = "商品销售属性，json格式")
    private String spData;
    /**
     * 创建者
     */
    @JsonIgnore
    @Schema(name = "createBy", description = "创建者")
    private String createBy;
    /**
     * 更新者
     */
    @JsonIgnore
    @Schema(name = "updateBy", description = "更新者")
    private String updateBy;
    /**
     * 创建日期
     */
    @JsonIgnore
    @Schema(name = "createTime", description = "创建日期")
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonIgnore
    @Schema(name = "updateTime", description = "更新时间")
    private Date updateTime;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @JsonIgnore
    @Schema(name = "deleteFlag", description = "逻辑删除（1 代表已删除），（0 代表未删除）")
    private Integer deleteFlag;
    @JsonIgnore
    private ProductVo product;
}

