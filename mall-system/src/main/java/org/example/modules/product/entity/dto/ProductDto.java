package org.example.modules.product.entity.dto;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.ValidationDto;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-07-14 13:05:48
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:05:48
 * @Description 商品信息(Product)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    /**
     * ID
     */
    private Long id;
    /**
     * 商品分类ID
     */
    private Long productCategoryId;
    /**
     * 运费模板id
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private Long freightTemplateId;
    /**
     * 商品属性分类ID
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private Long productAttributeCategoryId;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品主图
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String pic;
    /**
     * 货号
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String productSn;
    /**
     * 上架状态：0->下架；1->上架 (0=false，1=true)
     */
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
    private Boolean verifyStatus;
    /**
     * 排序
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private Integer sort;
    /**
     * 销量
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private Integer sale;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 副标题
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String subTitle;
    /**
     * 商品描述
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String description;
    /**
     * 库存
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private Integer stock;
    /**
     * 库存预警值
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private Integer lowStock;
    /**
     * 单位
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String unit;
    /**
     * 产品服务：1->无忧退货；2->快速退款；3->免费包邮
     */
    private Set<String> serviceIds;
    /**
     * 关键字
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String keywords;
    /**
     * 注意事项
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String note;
    /**
     * 画册图片，连产品图片限制为5张，以逗号分割
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private Set<String> albumPics;
    /**
     * 详细标题
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String detailTitle;
    /**
     * 详细描述
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String detailDesc;
    /**
     * 产品详情网页内容
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String detailHtml;
    /**
     * 移动端网页详情
     */
    @Null(groups = ValidationDto.SelectPage.class)
    private String detailMobileHtml;

}

