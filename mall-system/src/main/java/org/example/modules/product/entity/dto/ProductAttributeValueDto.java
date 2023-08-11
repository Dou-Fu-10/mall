package org.example.modules.product.entity.dto;


import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.ValidationDto;

import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-07-14 13:54:16
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:54:16
 * @Description 存储产品参数信息的表(ProductAttributeValue)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttributeValueDto {
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
    @Null(groups = ValidationDto.SelectPage.class)
    private Set<String> value;


}

