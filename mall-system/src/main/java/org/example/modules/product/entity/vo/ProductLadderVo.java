package org.example.modules.product.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:56
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:56
 * @Description 产品阶梯价格表(只针对同商品)(ProductLadder)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductLadderVo {
    /**
     * ID
     */
    private Long id;
    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 满足的商品数量
     */
    private Integer count;
    /**
     * 折扣
     */
    private Double discount;
    /**
     * 折后价格
     */
    private Double price;


}

