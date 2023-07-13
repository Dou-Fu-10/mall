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
 * Created by Dou-Fu-10 2023-07-13 15:35:55
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:55
 * @Description 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）(ProductCategoryAttributeRelation)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_product_category_attribute_relation")
@Schema(name = "pms_product_category_attribute_relation", description = "产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）(ProductCategoryAttributeRelation)表实体类")
public class ProductCategoryAttributeRelationEntity extends CommonEntity<ProductCategoryAttributeRelationEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 商品分类id
     */
    @Schema(name = "productCategoryId", description = "商品分类id")
    private Long productCategoryId;
    /**
     * 商品属性id
     */
    @Schema(name = "productAttributeId", description = "商品属性id")
    private Long productAttributeId;


}

