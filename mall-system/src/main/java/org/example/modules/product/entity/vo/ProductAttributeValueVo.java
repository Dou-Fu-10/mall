package org.example.modules.product.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Dou-Fu-10 2023-07-14 13:54:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:54:17
 * @Description 存储产品参数信息的表(ProductAttributeValue)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttributeValueVo {
    /**
     * ID
     */
    private Long id;
    /**
     * 产品分类ID
     */
    private Long productId;
    /**
     * 产品属性ID
     */
    private Long productAttributeId;

    /**
     * 手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开
     */
    private String value;


}

