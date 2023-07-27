package org.example.modules.portal.product.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.modules.admin.product.entity.vo.ProductAttributeValueVo;
import org.example.modules.admin.product.entity.vo.ProductAttributeVo;
import org.example.modules.admin.product.entity.vo.ProductVo;
import org.example.modules.admin.product.entity.vo.SkuStockVo;

import java.util.List;

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
public class PublicProductDetail {
    /**
     * 商品信息
     */
    private ProductVo product;
    /**
     * 商品属性参数表
     */
    private List<ProductAttributeVo> productAttributeList;
    /**
     * 存储产品参数信息的表
     */
    private List<ProductAttributeValueVo> productAttributeValueList;
    /**
     * sku的库存
     */
    private List<SkuStockVo> skuStockList;
}
