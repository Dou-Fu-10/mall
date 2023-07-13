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
 * @Description 产品分类(ProductCategory)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_product_category")
@Schema(name = "pms_product_category", description = "产品分类(ProductCategory)表实体类")
public class ProductCategoryEntity extends CommonEntity<ProductCategoryEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 上级分类的编号：0表示一级分类
     */
    @Schema(name = "parentId", description = "上级分类的编号：0表示一级分类")
    private Long parentId;
    /**
     * 分类名字
     */
    @Schema(name = "name", description = "分类名字")
    private String name;
    /**
     * 分类级别：0->1级；1->2级
     */
    @Schema(name = "level", description = "分类级别：0->1级；1->2级")
    private Integer level;
    /**
     * 产品数量
     */
    @Schema(name = "productCount", description = "产品数量")
    private Integer productCount;
    /**
     * 产品数量单位
     */
    @Schema(name = "productUnit", description = "产品数量单位")
    private String productUnit;
    /**
     * 是否显示在导航栏：0->不显示；1->显示
     */
    @Schema(name = "navStatus", description = "是否显示在导航栏：0->不显示；1->显示")
    private Integer navStatus;
    /**
     * 显示状态：0->不显示；1->显示
     */
    @Schema(name = "showStatus", description = "显示状态：0->不显示；1->显示")
    private Integer showStatus;
    /**
     * 排序
     */
    @Schema(name = "sort", description = "排序")
    private Integer sort;
    /**
     * 分类图标
     */
    @Schema(name = "icon", description = "分类图标")
    private String icon;
    /**
     * 关键词
     */
    @Schema(name = "keywords", description = "关键词")
    private String keywords;
    /**
     * 分类描述
     */
    @Schema(name = "description", description = "分类描述")
    private String description;


}

