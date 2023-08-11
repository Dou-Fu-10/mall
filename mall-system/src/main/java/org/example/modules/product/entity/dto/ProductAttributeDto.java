package org.example.modules.product.entity.dto;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-07-14 12:49:52
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 12:49:52
 * @Description 商品属性参数表(ProductAttribute)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttributeDto {
    /**
     * ID
     */
    private Long id;
    /**
     * 商品属性分类id
     */
    private Long productAttributeCategoryId;

    /**
     * 商品属性名字
     */
    private String name;
    /**
     * 属性选择类型：0->唯一；1->单选；2->多选
     */
    @DecimalMin(value = "0", message = "输入必须大于等于 0")
    @DecimalMax(value = "2", message = "输入必须小于等于 2")
    private Integer selectType;
    /**
     * 属性录入方式：0->手工录入；1->从列表中选取
     */
    @DecimalMin(value = "0", message = "输入必须大于等于 0")
    @DecimalMax(value = "1", message = "输入必须小于等于 1")
    private Integer inputType;
    /**
     * 可选值列表，以逗号隔开
     */

    private Set<String> inputList;
    /**
     * 排序字段
     */
    private Integer sort;
    /**
     * 属性的类型；0->规格；1->参数
     */
    @DecimalMin(value = "0", message = "输入必须大于等于 0")
    @DecimalMax(value = "1", message = "输入必须小于等于 1")
    private Integer type;


}

