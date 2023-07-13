package org.example.modules.product.entity.vo;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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

