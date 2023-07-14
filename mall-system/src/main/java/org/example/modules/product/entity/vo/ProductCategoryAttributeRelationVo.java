package org.example.modules.product.entity.vo;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by PanShiFu 2023-07-14 13:54:18
 *
 * @author PanShiFu
 * @date 2023-07-14 13:54:18
 * @Description 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）(ProductCategoryAttributeRelation)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryAttributeRelationVo {
    /**
     * ID
     */
    private Long id;
    /**
     * 商品分类id
     */
    private Long productCategoryId;
    /**
     * 商品属性id
     */
    private Long productAttributeId;


}
