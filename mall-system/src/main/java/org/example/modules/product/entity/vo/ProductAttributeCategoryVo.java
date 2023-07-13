package org.example.modules.product.entity.vo;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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

