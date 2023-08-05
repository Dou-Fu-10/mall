package org.example.modules.product.serveice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.product.entity.ProductEntity;
import org.example.modules.product.entity.dto.ProductDto;
import org.example.modules.product.entity.vo.*;
import org.example.modules.product.mapper.ProductMapper;
import org.example.modules.product.serveice.ProductService;
import org.example.modules.product.service.ProductAttributeService;
import org.example.modules.product.service.ProductAttributeValueService;
import org.example.modules.product.service.SkuStockService;
import org.example.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
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
@Slf4j
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductEntity> implements ProductService {

    @Resource
    private SkuStockService skuStockService;
    @Resource
    private ProductAttributeValueService productAttributeValueService;
    @Resource
    private ProductAttributeService productAttributeService;

    @Override
    public ProductVo findById(Serializable id) {
        ProductEntity productEntity = getById(id);
        return BeanCopy.convert(productEntity, ProductVo.class);
    }

    @Override
    public ProductDetail detail(Serializable id) {
        ProductVo productVo = findById(id);
        try {
            Long memberId = SecurityUtils.getCurrentUserId();
            log.info("记录浏览日志 id=" + memberId);
        } catch (BaseRequestException e) {
            log.info("测试");
        }
        if (Objects.isNull(productVo)) {
            return new ProductDetail();
        }
        List<SkuStockVo> skuStockList = skuStockService.getSkuStockByProductId(productVo.getId());
        List<ProductAttributeValueVo> productAttributeValueList = productAttributeValueService.getProductAttributeValueByProductId(productVo.getId());
        List<ProductAttributeVo> productAttributeList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(productAttributeValueList)) {
            Set<Long> productAttributeIds = productAttributeValueList.stream().map(ProductAttributeValueVo::getProductAttributeId).collect(Collectors.toSet());
            productAttributeService.findListByIds(productAttributeIds);
        }


        return new ProductDetail(productVo, productAttributeList, productAttributeValueList, skuStockList);
    }

    @Override
    public Page<ProductVo> search(Page<ProductEntity> page, ProductDto product) {
        Page<ProductEntity> productVoPage = page(page, new QueryWrapper<>(BeanCopy.convert(product, ProductEntity.class)));
        IPage<ProductVo> publicProductVoPage = productVoPage.convert(productVo -> BeanCopy.convert(productVo, ProductVo.class));
        return (Page) publicProductVoPage;
    }

    @Override
    public Page<ProductVo> page(Page<ProductEntity> page, ProductDto product) {
        ProductEntity convert = BeanCopy.convert(product, ProductEntity.class);
        Page<ProductEntity> productEntityPage = this.page(page, new QueryWrapper<>(convert));
        IPage<ProductVo> productVoIpage = productEntityPage.convert(productEntity -> BeanCopy.convert(productEntity, ProductVo.class));
        List<ProductVo> productVoList = productVoIpage.getRecords();
        // TODO 优化查询逻辑
        productVoList.forEach(productVo -> {
            Long productId = productVo.getId();
            // 获取 sku
            List<SkuStockVo> skuStockByProductId = skuStockService.getSkuStockByProductId(productId);
            // 获取 存储产品参数信息的表
            List<ProductAttributeValueVo> productAttributeValueByProductId = productAttributeValueService.getProductAttributeValueByProductId(productId);
            if (CollectionUtils.isNotEmpty(skuStockByProductId)) {
                productVo.setSkuStockList(skuStockByProductId);
            }
            if (CollectionUtils.isNotEmpty(productAttributeValueByProductId)) {
                productVo.setProductAttributeValueList(productAttributeValueByProductId);
            }
        });

        return (Page) productVoIpage;
    }
}

