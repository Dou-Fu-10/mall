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
 * Created by Dou-Fu-10 2023-07-13 15:35:56
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:56
 * @Description 产品阶梯价格表(只针对同商品)(ProductLadder)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_product_ladder")
@Schema(name = "pms_product_ladder", description = "产品阶梯价格表(只针对同商品)(ProductLadder)表实体类")
public class ProductLadderEntity extends CommonEntity<ProductLadderEntity> implements Serializable {
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
     * 满足的商品数量
     */
    @Schema(name = "count", description = "满足的商品数量")
    private Integer count;
    /**
     * 折扣
     */
    @Schema(name = "discount", description = "折扣")
    private Double discount;
    /**
     * 折后价格
     */
    @Schema(name = "price", description = "折后价格")
    private Double price;


}

