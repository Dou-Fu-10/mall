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
 * Created by Dou-Fu-10 2023-07-14 12:49:51
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 12:49:51
 * @Description 商品属性参数表(ProductAttribute)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_product_attribute")
@Schema(name = "pms_product_attribute", description = "商品属性参数表(ProductAttribute)表实体类")
public class ProductAttributeEntity extends CommonEntity<ProductAttributeEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 商品属性分类id
     */
    private Long productAttributeCategoryId;

    /**
     * 商品属性名字
     */
    @Schema(name = "name", description = "商品属性名字")
    private String name;
    /**
     * 属性选择类型：0->唯一；1->单选；2->多选
     */
    @Schema(name = "selectType", description = "属性选择类型：0->唯一；1->单选；2->多选")
    private Integer selectType;
    /**
     * 属性录入方式：0->手工录入；1->从列表中选取
     */
    @Schema(name = "inputType", description = "属性录入方式：0->手工录入；1->从列表中选取")
    private Integer inputType;
    /**
     * 可选值列表，以逗号隔开
     */
    @Schema(name = "inputList", description = "可选值列表，以逗号隔开")
    private String inputList;
    /**
     * 排序字段
     */
    @Schema(name = "sort", description = "排序字段")
    private Integer sort;
    /**
     * 属性的类型；0->规格；1->参数
     */
    @Schema(name = "type", description = "属性的类型；0->规格；1->参数")
    private Integer type;


}

