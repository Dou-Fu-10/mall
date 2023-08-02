package org.example.modules.product.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-08-01 22:28:58
 *
 * @author Dou-Fu-10
 * @date 2023-08-01 22:28:58
 * @Description 商品属性参数表(ProductAttribute)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttributeDto {
    /**
     * ID
     */
    @Schema(name = "id", description = "ID")
    private Long id;
    /**
     * 商品属性分类id
     */
    @Schema(name = "productAttributeCategoryId", description = "商品属性分类id")
    private Long productAttributeCategoryId;

    /**
     * 商品属性名字
     */
    @Schema(name = "name", description = "商品属性名字")
    private String name;
    /**
     * 属性选择类型：0->唯一；1->单选；2->多选
     */
    @Schema(name = "selectType", description = "属性选择类型：0->唯一；1->单选；2->多选")
    private Integer selectType;
    /**
     * 属性录入方式：0->手工录入；1->从列表中选取
     */
    @Schema(name = "inputType", description = "属性录入方式：0->手工录入；1->从列表中选取")
    private Integer inputType;
    /**
     * 可选值列表，以逗号隔开
     */
    @Schema(name = "inputList", description = "可选值列表，以逗号隔开")
    private String inputList;
    /**
     * 排序字段：最高的可以单独上传图片
     */
    @Schema(name = "sort", description = "排序字段：最高的可以单独上传图片")
    private Integer sort;
    /**
     * 分类筛选样式：1->普通；1->颜色
     */
    @Schema(name = "filterType", description = "分类筛选样式：1->普通；1->颜色")
    private Integer filterType;
    /**
     * 检索类型；0->不需要进行检索；1->关键字检索；2->范围检索
     */
    @Schema(name = "searchType", description = "检索类型；0->不需要进行检索；1->关键字检索；2->范围检索")
    private Integer searchType;
    /**
     * 相同属性产品是否关联；0->不关联；1->关联
     */
    @Schema(name = "relatedStatus", description = "相同属性产品是否关联；0->不关联；1->关联")
    private Integer relatedStatus;
    /**
     * 是否支持手动新增；0->不支持；1->支持
     */
    @Schema(name = "handAddStatus", description = "是否支持手动新增；0->不支持；1->支持")
    private Integer handAddStatus;
    /**
     * 属性的类型；0->规格；1->参数
     */
    @Schema(name = "type", description = "属性的类型；0->规格；1->参数")
    private Integer type;
    /**
     * 创建者
     */
    @Schema(name = "createBy", description = "创建者")
    private String createBy;
    /**
     * 更新者
     */
    @Schema(name = "updateBy", description = "更新者")
    private String updateBy;
    /**
     * 创建日期
     */
    @Schema(name = "createTime", description = "创建日期")
    private Date createTime;
    /**
     * 更新时间
     */
    @Schema(name = "updateTime", description = "更新时间")
    private Date updateTime;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @Schema(name = "deleteFlag", description = "逻辑删除（1 代表已删除），（0 代表未删除）")
    private Integer deleteFlag;


}

