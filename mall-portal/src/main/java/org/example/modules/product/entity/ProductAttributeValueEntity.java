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
 * Created by Dou-Fu-10 2023-08-01 22:28:59
 *
 * @author Dou-Fu-10
 * @date 2023-08-01 22:28:59
 * @Description 存储产品参数信息的表(ProductAttributeValue)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_product_attribute_value")
@Schema(name = "pms_product_attribute_value", description = "存储产品参数信息的表(ProductAttributeValue)表实体类")
public class ProductAttributeValueEntity extends CommonEntity<ProductAttributeValueEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
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
    @Schema(name = "value", description = "手动添加规格或参数的值，参数单值，规格有多个时以逗号隔开")
    private String value;


}

