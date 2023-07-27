package org.example.modules.portal.product.serveice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.admin.product.entity.ProductEntity;
import org.example.modules.admin.product.entity.vo.ProductAttributeValueVo;
import org.example.modules.admin.product.entity.vo.ProductAttributeVo;
import org.example.modules.admin.product.entity.vo.ProductVo;
import org.example.modules.admin.product.entity.vo.SkuStockVo;
import org.example.modules.admin.product.service.ProductAttributeService;
import org.example.modules.admin.product.service.ProductAttributeValueService;
import org.example.modules.admin.product.service.ProductService;
import org.example.modules.admin.product.service.SkuStockService;
import org.example.modules.portal.product.entity.dto.PublicProductDto;
import org.example.modules.portal.product.entity.vo.PublicProductDetail;
import org.example.modules.portal.product.entity.vo.PublicProductVo;
import org.example.modules.portal.product.serveice.PublicProductService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-27 13:05:47
 *
 * @author Dou-Fu-10
 * @date 2023-07-27 13:05:47
 * @Description 商品信息(Product)表服务实现类
 */
@Service("publicProductService")
public class PublicProductServiceImpl1 implements PublicProductService {

    @Resource
    private ProductService productService;
    @Resource
    private SkuStockService skuStockService;
    @Resource
    private ProductAttributeValueService productAttributeValueService;
    @Resource
    private ProductAttributeService productAttributeService;

    @Override
    public PublicProductDetail detail(Serializable id) {
        ProductVo productVo = productService.findById(id);
        if (Objects.isNull(productVo)) {
            return new PublicProductDetail();
        }
        List<SkuStockVo> skuStockList = skuStockService.getSkuStockByProductId(productVo.getId());
        List<ProductAttributeValueVo> productAttributeValueList = productAttributeValueService.getProductAttributeValueByProductId(productVo.getId());
        Set<Long> productAttributeIds = productAttributeValueList.stream().map(ProductAttributeValueVo::getProductAttributeId).collect(Collectors.toSet());
        List<ProductAttributeVo> productAttributeList = productAttributeService.findListByIds(productAttributeIds);

        return new PublicProductDetail(productVo, productAttributeList, productAttributeValueList, skuStockList);
    }

    @Override
    public Page<PublicProductVo> search(Page<ProductEntity> page, PublicProductDto product) {
        Page<ProductEntity> productVoPage = productService.page(page, new QueryWrapper<>(BeanCopy.convert(product, ProductEntity.class)));
        IPage<PublicProductVo> publicProductVoPage = productVoPage.convert(productVo -> BeanCopy.convert(productVo, PublicProductVo.class));
        return (Page) publicProductVoPage;
    }
}

