package org.example.modules.product.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-13 22:08:16
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 22:08:16
 * @Description 产品分类(ProductCategory)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryVo {
    /**
     * ID
     */
    private Long id;
    /**
     * 上级分类的编号：0表示一级分类
     */
    private Long parentId;

    /**
     * 分类名字
     */
    private String name;
    /**
     * 产品计量单位
     */
    private String productUnit;
    /**
     * 是否显示在导航栏：0->不显示；1->显示
     */
    private Boolean navStatus;
    /**
     * 显示状态：0->不显示；1->显示
     */
    private Boolean showStatus;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 分类图标
     */
    private String icon;
    /**
     * 关键词
     */
    private String keywords;
    /**
     * 分类描述
     */
    private String description;

    private List<ProductCategoryVo> children;

}

