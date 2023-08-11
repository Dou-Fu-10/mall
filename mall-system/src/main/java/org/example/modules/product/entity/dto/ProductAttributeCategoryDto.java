package org.example.modules.product.entity.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Dou-Fu-10 2023-07-14 11:03:43
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 11:03:43
 * @Description 产品属性分类表(ProductAttributeCategory)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttributeCategoryDto {
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
    @JsonIgnore
    private Integer attributeCount;
    /**
     * 参数数量
     */
    @JsonIgnore
    private Integer paramCount;


}

