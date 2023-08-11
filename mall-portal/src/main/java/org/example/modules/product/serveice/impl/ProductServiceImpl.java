package org.example.modules.product.serveice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
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
    public ProductVo getByProductId(Serializable id) {
        ProductEntity productEntity = getById(id);
        productEntity.setVerifyStatus(true);
        productEntity.setPublishStatus(true);
        return BeanCopy.convert(productEntity, ProductVo.class);
    }

    @Override
    public ProductDetail detail(Serializable id) {
        ProductVo productVo = getByProductId(id);
        if (Objects.isNull(productVo)) {
            return new ProductDetail();
        }
        // 获取商品的sku
        List<SkuStockVo> skuStockList = skuStockService.getSkuStockByProductId(productVo.getId());
        // 获取商品的 存储产品参数信息的表
        List<ProductAttributeValueVo> productAttributeValueList = productAttributeValueService.getProductAttributeValueByProductId(productVo.getId());
        // 获取商品的 商品属性参数表
        List<ProductAttributeVo> productAttributeList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(productAttributeValueList)) {
            Set<Long> productAttributeIds = productAttributeValueList.stream().map(ProductAttributeValueVo::getProductAttributeId).collect(Collectors.toSet());
            productAttributeList = productAttributeService.findListByIds(productAttributeIds);
        }


        return new ProductDetail(productVo, productAttributeList, productAttributeValueList, skuStockList);
    }

    @Override
    public Page<ProductVo> search(Page<ProductEntity> page, ProductDto product) {
        // TODO 不可用
        Page<ProductEntity> productVoPage = page(page, new QueryWrapper<>(BeanCopy.convert(product, ProductEntity.class)));
        IPage<ProductVo> publicProductVoPage = productVoPage.convert(productVo -> BeanCopy.convert(productVo, ProductVo.class));
        return (Page<ProductVo>) publicProductVoPage;
    }

    @Override
    public List<ProductVo> getByIdsInVerifyStatusAndPublishStatus(Set<Long> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            return new ArrayList<>();
        }
        ProductEntity productEntity = new ProductEntity();
        // 审核状态：0->未审核；1->审核通过 (0=false，1=true)
        // 确保商品已审核
        productEntity.setVerifyStatus(true);
        // 上架状态：0->下架；1->上架 (0=false，1=true)
        // 确保商品以上架
        productEntity.setPublishStatus(true);
        LambdaQueryWrapper<ProductEntity> productEntityList = new LambdaQueryWrapper<>(productEntity);
        productEntityList.in(ProductEntity::getId, productIds);
        List<ProductEntity> productEntities = list(productEntityList);
        return BeanCopy.copytList(productEntities, ProductVo.class);
    }

    @Override
    public List<ProductVo> getByProductIds(Set<Long> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            throw new BaseRequestException("获取商品失败");
        }
        LambdaQueryWrapper<ProductEntity> productEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 审核状态：0->未审核；1->审核通过 (0=false，1=true)
        // 确保商品已审核
        productEntityLambdaQueryWrapper.eq(ProductEntity::getVerifyStatus, true);
        // 上架状态：0->下架；1->上架 (0=false，1=true)
        // 确保商品以上架
        productEntityLambdaQueryWrapper.eq(ProductEntity::getPublishStatus, true);

        productEntityLambdaQueryWrapper.in(ProductEntity::getId, productIds);
        // 获取已上架的商品并返回
        return BeanCopy.copytList(list(productEntityLambdaQueryWrapper), ProductVo.class);
    }

    @Override
    public Page<ProductVo> page(Page<ProductEntity> page, ProductDto productDto) {
        ProductEntity productEntity = BeanCopy.convert(productDto, ProductEntity.class);
        // 审核状态：0->未审核；1->审核通过 (0=false，1=true)
        // 确保商品已审核
        productEntity.setVerifyStatus(true);
        // 上架状态：0->下架；1->上架 (0=false，1=true)
        // 确保商品以上架
        productEntity.setPublishStatus(true);
        Page<ProductEntity> productEntityPage = this.page(page, new QueryWrapper<>(productEntity));
        IPage<ProductVo> productVoIPage = productEntityPage.convert(product -> BeanCopy.convert(product, ProductVo.class));
        List<ProductVo> productVoList = productVoIPage.getRecords();
        // 获取商品id
        Set<Long> productIds = productVoList.stream().map(ProductVo::getId).collect(Collectors.toSet());
        // 获取商品 sku
        List<SkuStockVo> skuStockVos = skuStockService.getSkuStockByProductIds(productIds);
        Map<Long, List<SkuStockVo>> longListMapSkuStockVo = longListMapSkuStockVo(skuStockVos);
        // 获取商品 属性value
        List<ProductAttributeValueVo> productAttributeValueVoList = productAttributeValueService.getByProductAttributeIds(productIds);
        Map<Long, List<ProductAttributeValueVo>> longListMapProductAttributeValueVo = longListMapProductAttributeValueVo(productAttributeValueVoList);
        productVoList.forEach(productVo -> {
            Long productVoId = productVo.getId();
            // 获取每个商品的sku
            if (longListMapSkuStockVo.containsKey(productVoId)) {
                List<SkuStockVo> stockVos = longListMapSkuStockVo.get(productVoId);
                productVo.setSkuStockList(stockVos);
            }
            // 获取每个商品的 属性value
            if (longListMapProductAttributeValueVo.containsKey(productVoId)) {
                List<ProductAttributeValueVo> productAttributeValueVos = longListMapProductAttributeValueVo.get(productVoId);
                productVo.setProductAttributeValueList(productAttributeValueVos);
            }

        });

        return (Page<ProductVo>) productVoIPage;
    }

    private Map<Long, List<SkuStockVo>> longListMapSkuStockVo(List<SkuStockVo> skuStockVos) {
        if (CollectionUtils.isEmpty(skuStockVos)) {
            return new HashMap<>();
        }
        Map<Long, List<SkuStockVo>> longListMap = new HashMap<>();
        skuStockVos.forEach(skuStockVo -> {
            Long productId = skuStockVo.getProductId();
            longListMap.computeIfAbsent(productId, k -> new ArrayList<>()).add(skuStockVo);
        });
        return longListMap;
    }

    private Map<Long, List<ProductAttributeValueVo>> longListMapProductAttributeValueVo(List<ProductAttributeValueVo> productAttributeValueVoList) {
        if (CollectionUtils.isEmpty(productAttributeValueVoList)) {
            return new HashMap<>();
        }
        Map<Long, List<ProductAttributeValueVo>> longListMap = new HashMap<>();
        productAttributeValueVoList.forEach(productAttributeValueVo -> {
            Long productId = productAttributeValueVo.getProductId();
            longListMap.computeIfAbsent(productId, k -> new ArrayList<>()).add(productAttributeValueVo);
        });
        return longListMap;
    }
}

