package org.example.modules.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-07-27 13:05:47
 *
 * @author Dou-Fu-10
 * @date 2023-07-27 13:05:47
 * @Description 商品信息(Product)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_product")
@Schema(name = "pms_product", description = "商品信息(Product)表实体类")
public class ProductEntity extends CommonEntity<ProductEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
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
     * 商品属性分类ID
     */
    @Schema(name = "productAttributeCategoryId", description = "商品属性分类ID")
    private Long productAttributeCategoryId;
    /**
     * 商品名称
     */
    @Schema(name = "name", description = "商品名称")
    private String name;
    /**
     * 商品主图
     */
    @Schema(name = "pic", description = "商品主图")
    private String pic;
    /**
     * 货号
     */
    @Schema(name = "productSn", description = "货号")
    private String productSn;
    /**
     * 上架状态：0->下架；1->上架 (0=false，1=true)
     */
    @Schema(name = "publishStatus", description = "上架状态：0->下架；1->上架 (0=false，1=true)")
    private Boolean publishStatus;
    /**
     * 新品状态:0->不是新品；1->新品 (0=false，1=true)
     */
    @Schema(name = "newStatus", description = "新品状态:0->不是新品；1->新品 (0=false，1=true)")
    private Boolean newStatus;
    /**
     * 推荐状态；0->不推荐；1->推荐 (0=false，1=true)
     */
    @Schema(name = "recommendedStatus", description = "推荐状态；0->不推荐；1->推荐 (0=false，1=true)")
    private Boolean recommendedStatus;
    /**
     * 审核状态：0->未审核；1->审核通过 (0=false，1=true)
     */
    @Schema(name = "verifyStatus", description = "审核状态：0->未审核；1->审核通过 (0=false，1=true)")
    private Boolean verifyStatus;
    /**
     * 排序
     */
    @Schema(name = "sort", description = "排序")
    private Integer sort;
    /**
     * 销量
     */
    @Schema(name = "sale", description = "销量")
    private Integer sale;
    /**
     * 价格
     */
    @Schema(name = "price", description = "价格")
    private BigDecimal price;
    /**
     * 副标题
     */
    @Schema(name = "subTitle", description = "副标题")
    private String subTitle;
    /**
     * 商品描述
     */
    @Schema(name = "description", description = "商品描述")
    private String description;
    /**
     * 库存
     */
    @Schema(name = "stock", description = "库存")
    private Integer stock;
    /**
     * 库存预警值
     */
    @Schema(name = "lowStock", description = "库存预警值")
    private Integer lowStock;
    /**
     * 单位
     */
    @Schema(name = "unit", description = "单位")
    private String unit;
    /**
     * 产品服务：1->无忧退货；2->快速退款；3->免费包邮
     */
    @Schema(name = "serviceIds", description = "产品服务：1->无忧退货；2->快速退款；3->免费包邮")
    private String serviceIds;
    /**
     * 关键字
     */
    @Schema(name = "keywords", description = "关键字")
    private String keywords;
    /**
     * 注意事项
     */
    @Schema(name = "note", description = "注意事项")
    private String note;
    /**
     * 画册图片，连产品图片限制为5张，以逗号分割
     */
    @Schema(name = "albumPics", description = "画册图片，连产品图片限制为5张，以逗号分割")
    private String albumPics;
    /**
     * 详细标题
     */
    @Schema(name = "detailTitle", description = "详细标题")
    private String detailTitle;
    /**
     * 详细描述
     */
    @Schema(name = "detailDesc", description = "详细描述")
    private String detailDesc;
    /**
     * 产品详情网页内容
     */
    @Schema(name = "detailHtml", description = "产品详情网页内容")
    private String detailHtml;
    /**
     * 移动端网页详情
     */
    @Schema(name = "detailMobileHtml", description = "移动端网页详情")
    private String detailMobileHtml;
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

