package org.example.modules.product.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-07-27 13:05:48
 *
 * @author Dou-Fu-10
 * @date 2023-07-27 13:05:48
 * @Description 商品信息(Product)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    /**
     * ID
     */
    @JsonIgnore
    private Long id;
    /**
     * 商品分类ID
     */
    @JsonIgnore
    private Long productCategoryId;
    /**
     * 运费模板id
     */
    @JsonIgnore
    private Long freightTemplateId;
    /**
     * 商品属性分类ID
     */
    @JsonIgnore
    private Long productAttributeCategoryId;
    /**
     * 商品名称
     */
    @JsonIgnore
    private String name;
    /**
     * 商品主图
     */
    @JsonIgnore
    private String pic;
    /**
     * 货号
     */
    @JsonIgnore
    private String productSn;
    /**
     * 上架状态：0->下架；1->上架 (0=false，1=true)
     */
    @JsonIgnore
    private Boolean publishStatus;
    /**
     * 新品状态:0->不是新品；1->新品 (0=false，1=true)
     */
    private Boolean newStatus;
    /**
     * 推荐状态；0->不推荐；1->推荐 (0=false，1=true)
     */
    private Boolean recommendedStatus;
    /**
     * 审核状态：0->未审核；1->审核通过 (0=false，1=true)
     */
    @JsonIgnore
    private Boolean verifyStatus;
    /**
     * 排序
     */
    @JsonIgnore
    private Integer sort;
    /**
     * 销量
     */
    @JsonIgnore
    private Integer sale;
    /**
     * 价格
     */
    @JsonIgnore
    private BigDecimal price;
    /**
     * 副标题
     */
    @JsonIgnore
    private String subTitle;
    /**
     * 商品描述
     */
    @JsonIgnore
    private String description;
    /**
     * 库存
     */
    @JsonIgnore
    private Integer stock;
    /**
     * 库存预警值
     */
    @JsonIgnore
    private Integer lowStock;
    /**
     * 单位
     */
    @JsonIgnore
    private String unit;
    /**
     * 产品服务：1->无忧退货；2->快速退款；3->免费包邮
     */
    @JsonIgnore
    private String serviceIds;
    /**
     * 关键字
     */
    @JsonIgnore
    private String keywords;
    /**
     * 注意事项
     */
    @JsonIgnore
    private String note;
    /**
     * 画册图片，连产品图片限制为5张，以逗号分割
     */
    @JsonIgnore
    private Set<String> albumPics;
    /**
     * 详细标题
     */
    @JsonIgnore
    private String detailTitle;
    /**
     * 详细描述
     */
    @JsonIgnore
    private String detailDesc;
    /**
     * 产品详情网页内容
     */
    @JsonIgnore
    private String detailHtml;
    /**
     * 移动端网页详情
     */
    @JsonIgnore
    private String detailMobileHtml;
    /**
     * 创建者
     */
    @JsonIgnore
    private String createBy;
    /**
     * 更新者
     */
    @JsonIgnore
    private String updateBy;
    /**
     * 创建日期
     */
    @JsonIgnore
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonIgnore
    private Date updateTime;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    @JsonIgnore
    private Integer deleteFlag;


}

