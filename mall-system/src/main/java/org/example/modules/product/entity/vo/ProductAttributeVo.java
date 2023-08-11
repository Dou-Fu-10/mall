package org.example.modules.product.entity.vo;


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
public class ProductAttributeVo {
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
    private Integer selectType;
    /**
     * 属性录入方式：0->手工录入；1->从列表中选取
     */
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
    private Integer type;


}

