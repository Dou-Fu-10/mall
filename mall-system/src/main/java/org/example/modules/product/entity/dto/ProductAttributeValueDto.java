package org.example.modules.product.entity.dto;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by PanShiFu 2023-07-14 13:54:16
 *
 * @author PanShiFu
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
    private String value;


}

