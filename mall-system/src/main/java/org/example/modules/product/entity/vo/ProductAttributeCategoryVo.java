package org.example.modules.product.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by PanShiFu 2023-07-14 11:03:43
 *
 * @author PanShiFu
 * @date 2023-07-14 11:03:43
 * @Description 产品属性分类表(ProductAttributeCategory)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttributeCategoryVo {
    /**
     * ID
     */
    private Long id;

    /**
     * 产品属性分类名字
     */
    private String name;
    /**
     * 属性数量
     */
    private Integer attributeCount;
    /**
     * 参数数量
     */
    private Integer paramCount;


}

