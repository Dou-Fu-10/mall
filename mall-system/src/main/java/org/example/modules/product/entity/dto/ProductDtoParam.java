package org.example.modules.product.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.modules.member.entity.dto.MemberPriceDto;

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
    @Schema(name = "memberPriceList", description = "商品会员价格设置")
    private List<MemberPriceDto> memberPriceList;
    @Schema(name = "skuStockList", description = "商品的sku库存信息")
    private List<SkuStockDto> skuStockList;
    @Schema(name = "productAttributeValueList", description = "商品参数及自定义规格属性")
    private List<ProductAttributeValueDto> productAttributeValueList;
}
