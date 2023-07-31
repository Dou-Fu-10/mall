package org.example.modules.admin.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.admin.product.entity.ProductEntity;
import org.example.modules.admin.product.entity.dto.ProductAttributeValueDto;
import org.example.modules.admin.product.entity.dto.ProductDto;
import org.example.modules.admin.product.entity.dto.ProductDtoParam;
import org.example.modules.admin.product.entity.dto.SkuStockDto;
import org.example.modules.admin.product.entity.vo.ProductAttributeValueVo;
import org.example.modules.admin.product.entity.vo.ProductVo;
import org.example.modules.admin.product.entity.vo.SkuStockVo;
import org.example.modules.admin.product.mapper.ProductMapper;
import org.example.modules.admin.product.service.ProductAttributeValueService;
import org.example.modules.admin.product.service.ProductService;
import org.example.modules.admin.product.service.SkuStockService;
import org.example.modules.admin.storage.service.MinioServer;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-07-14 13:05:47
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:05:47
 * @Description 商品信息(Product)表服务实现类
 */
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductEntity> implements ProductService {

    @Resource
    private SkuStockService skuStockService;
    @Resource
    private MinioServer minioServer;
    @Resource
    private ProductAttributeValueService productAttributeValueService;

    @Override
    public Page<ProductVo> page(Page<ProductEntity> page, ProductDto product) {
        ProductEntity convert = BeanCopy.convert(product, ProductEntity.class);
        Page<ProductEntity> productEntityPage = page(page, new QueryWrapper<>(convert));
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

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean save(ProductDtoParam product) {
        // TODO 数据校验
        ProductEntity convert = BeanCopy.convert(product, ProductEntity.class);
        Set<String> albumPics = product.getAlbumPics();
        if (Objects.nonNull(albumPics)) {
            String albumPicStr = checkAlbumPics(albumPics);
            if (Objects.nonNull(albumPicStr)) {
                convert.setAlbumPics(albumPicStr);
            }
        }


        if (!convert.insert()) {
            throw new BaseRequestException("添加失败");
        }
        List<SkuStockDto> skuStockList = product.getSkuStockList();
        if (CollectionUtils.isNotEmpty(skuStockList)) {
            skuStockList.forEach(skuStockDto -> skuStockDto.setProductId(convert.getId()));
            if (!skuStockService.save(skuStockList)) {
                throw new BaseRequestException("添加失败");
            }
        }

        List<ProductAttributeValueDto> productAttributeValueList = product.getProductAttributeValueList();
        if (CollectionUtils.isNotEmpty(productAttributeValueList)) {
            productAttributeValueList.forEach(productAttributeValueDto -> productAttributeValueDto.setProductId(convert.getId()));
            if (!productAttributeValueService.save(productAttributeValueList)) {
                throw new BaseRequestException("添加失败");
            }

        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean updateById(ProductDtoParam product) {
        // TODO 数据校验
        ProductEntity convert = BeanCopy.convert(product, ProductEntity.class);
        Set<String> albumPics = product.getAlbumPics();
        if (Objects.nonNull(albumPics)) {
            String albumPicStr = checkAlbumPics(albumPics);
            if (Objects.nonNull(albumPicStr)) {
                convert.setAlbumPics(albumPicStr);
            }
        }

        if (!convert.updateById()) {
            throw new BaseRequestException("添加失败");
        }
        List<SkuStockDto> skuStockList = product.getSkuStockList();
        if (CollectionUtils.isNotEmpty(skuStockList)) {
            skuStockList.forEach(skuStockDto -> skuStockDto.setProductId(convert.getId()));
            if (!skuStockService.saveOrUpdate(skuStockList)) {
                throw new BaseRequestException("修改失败");
            }
        }

        List<ProductAttributeValueDto> productAttributeValueList = product.getProductAttributeValueList();
        if (CollectionUtils.isNotEmpty(productAttributeValueList)) {
            productAttributeValueList.forEach(productAttributeValueDto -> productAttributeValueDto.setProductId(convert.getId()));
            if (!productAttributeValueService.saveOrUpdate(productAttributeValueList)) {
                throw new BaseRequestException("修改失败");
            }

        }
        return true;
    }

    private String checkAlbumPics(Set<String> albumPics) {
        Set<String> existObject = minioServer.checkObjectIsExist(albumPics);
        if (existObject.isEmpty()) {
            return null;
        }
        return String.join(",", existObject);
    }

    @Override
    public ProductVo findById(Serializable id) {
        ProductEntity productEntity = getById(id);
        return BeanCopy.convert(productEntity, ProductVo.class);
    }

    @Override
    public Boolean updateStatus(@NotNull Long id, @NotNull Boolean status) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setPublishStatus(status);
        productEntity.setId(id);
        // TODO 不允许修改上级的或者同级的
        return productEntity.updateById();
    }
}

