package org.example.modules.product.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
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
    private Long freightTemplateId;

    /**
     * 商品分类名称
     */
    private String productCategoryName;
    /**
     * 商品属性参数ID
     */
    private Long productAttributeCategoryId;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品图片
     */
    private String pic;
    /**
     * 货号
     */
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
    private Boolean recommandStatus;
    /**
     * 审核状态：0->未审核；1->审核通过 (0=false，1=true)
     */
    private Boolean verifyStatus;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 销量
     */
    private Integer sale;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 副标题
     */
    private String subTitle;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 市场价
     */
    private BigDecimal originalPrice;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 库存预警值
     */
    private Integer lowStock;
    /**
     * 单位
     */
    private String unit;
    /**
     * 商品重量，默认为克
     */
    private BigDecimal weight;
    /**
     * 以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮
     */
    private Set<String> serviceIds;
    /**
     * 关键字
     */
    private String keywords;
    /**
     * 注意事项
     */
    private String note;
    /**
     * 画册图片，连产品图片限制为5张，以逗号分割
     */
    private Set<String> albumPics;
    /**
     * 详细标题
     */
    private String detailTitle;
    /**
     * 详细描述
     */
    private String detailDesc;
    /**
     * 产品详情网页内容
     */
    private String detailHtml;
    /**
     * 移动端网页详情
     */
    private String detailMobileHtml;
    /**
     * 促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购
     */
    private Integer promotionType;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 逻辑删除（1 代表已删除），（0 代表未删除）
     */
    private Integer deleteFlag;


}

