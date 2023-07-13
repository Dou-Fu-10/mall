package org.example.modules.product.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:55
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:55
 * @Description 产品满减表(只针对同商品)(ProductFullReduction)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_product_full_reduction")
@Schema(name = "pms_product_full_reduction", description = "产品满减表(只针对同商品)(ProductFullReduction)表实体类")
public class ProductFullReductionEntity extends CommonEntity<ProductFullReductionEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 商品价格
     */
    @Schema(name = "fullPrice", description = "商品价格")
    private Double fullPrice;
    /**
     * 降价价格
     */
    @Schema(name = "reducePrice", description = "降价价格")
    private Double reducePrice;


}

