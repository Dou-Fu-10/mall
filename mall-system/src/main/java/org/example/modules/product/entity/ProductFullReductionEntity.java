package org.example.modules.product.entity;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.example.common.core.base.CommonEntity;

/**
 * Created by PanShiFu 2023-07-13 15:35:55
 *
 * @author PanShiFu
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
    @TableId
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

