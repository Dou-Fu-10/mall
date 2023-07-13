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
 * Created by PanShiFu 2023-07-13 22:08:15
 *
 * @author PanShiFu
 * @date 2023-07-13 22:08:15
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
    @TableId
    private Long parentId;

    /**
     * 分类名字
     */
    @Schema(name = "name", description = "分类名字")
    private String name;
    /**
     * 产品计量单位
     */
    @Schema(name = "productUnit", description = "产品计量单位")
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

