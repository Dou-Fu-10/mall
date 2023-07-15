package org.example.modules.product.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-15
 *
 * @author Dou-Fu-10
 * @date 2023-07-15
 * @Description 
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoParam extends ProductDto {
    @Schema(name = "商品阶梯价格设置")
    private List<PmsProductLadder> productLadderList;
    @Schema(name = "商品会员价格设置")
    private List<PmsMemberPrice> memberPriceList;
    @Schema(name = "商品的sku库存信息")
    private List<PmsSkuStock> skuStockList;
    @Schema(name = "商品参数及自定义规格属性")
    private List<ProductAttributeValueDto> productAttributeValueList;
    @Schema(name = "专题和商品关系")
    private List<CmsSubjectProductRelation> subjectProductRelationList;
    @Schema(name = "优选专区和商品的关系")
    private List<CmsPrefrenceAreaProductRelation> prefrenceAreaProductRelationList;
}
