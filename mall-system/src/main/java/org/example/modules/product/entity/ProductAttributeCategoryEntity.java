package org.example.modules.product.entity;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.example.common.core.base.CommonEntity;

/**
 * Created by PanShiFu 2023-07-13 15:35:54
 *
 * @author PanShiFu
 * @date 2023-07-13 15:35:54
 * @Description 产品属性分类表(ProductAttributeCategory)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_product_attribute_category")
@Schema(name = "pms_product_attribute_category", description = "产品属性分类表(ProductAttributeCategory)表实体类")
public class ProductAttributeCategoryEntity extends CommonEntity<ProductAttributeCategoryEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 产品属性分类名字
     */
    @Schema(name = "name", description = "产品属性分类名字")
    private String name;
    /**
     * 属性数量
     */
    @Schema(name = "attributeCount", description = "属性数量")
    private Integer attributeCount;
    /**
     * 参数数量
     */
    @Schema(name = "paramCount", description = "参数数量")
    private Integer paramCount;


}

