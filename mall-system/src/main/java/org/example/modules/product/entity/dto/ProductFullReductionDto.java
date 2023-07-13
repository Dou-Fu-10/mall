package org.example.modules.product.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:56
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:56
 * @Description 产品满减表(只针对同商品)(ProductFullReduction)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFullReductionDto {
    /**
     * ID
     */
    private Long id;
    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 商品价格
     */
    private Double fullPrice;
    /**
     * 降价价格
     */
    private Double reducePrice;


}

