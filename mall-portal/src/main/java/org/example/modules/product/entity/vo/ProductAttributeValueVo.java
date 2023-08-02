package org.example.modules.product.entity.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Dou-Fu-10 2023-08-01 22:28:59
 *
 * @author Dou-Fu-10
 * @date 2023-08-01 22:28:59
 * @Description 存储产品参数信息的表(ProductAttributeValue)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttributeValueVo {
    /**
     * ID
     */
    @Schema(name = "id", description = "ID")
    private Long id;
    /**
     * 产品分类ID
     */
    @Schema(name = "productId", description = "产品分类ID")
    private Long productId;
    /**
     * 产品属性ID
     */
    @Schema(name = "productAttributeId", description = "产品属性ID")
    private Long productAttributeId;

    /**
     * 手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开
     */
    @Schema(name = "value", description = "手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开")
    private String value;


}

