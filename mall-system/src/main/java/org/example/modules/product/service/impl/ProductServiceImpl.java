package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.server.MinioServer;
import org.example.common.core.utils.BeanCopy;
import org.example.common.core.utils.StringUtils;
import org.example.modules.product.entity.ProductEntity;
import org.example.modules.product.entity.dto.ProductAttributeValueDto;
import org.example.modules.product.entity.dto.ProductDto;
import org.example.modules.product.entity.dto.ProductDtoParam;
import org.example.modules.product.entity.dto.SkuStockDto;
import org.example.modules.product.entity.vo.ProductAttributeValueVo;
import org.example.modules.product.entity.vo.ProductVo;
import org.example.modules.product.entity.vo.SkuStockVo;
import org.example.modules.product.mapper.ProductMapper;
import org.example.modules.product.service.ProductAttributeValueService;
import org.example.modules.product.service.ProductService;
import org.example.modules.product.service.SkuStockService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

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
    @Lazy
    private ProductAttributeValueService productAttributeValueService;

    @Override
    public Page<ProductVo> page(Page<ProductEntity> page, ProductDto productDto) {
        ProductEntity productEntity = BeanCopy.convert(productDto, ProductEntity.class);
        Page<ProductEntity> productEntityPage = page(page, new QueryWrapper<>(productEntity));
        IPage<ProductVo> productVoIpage = productEntityPage.convert(product -> BeanCopy.convert(product, ProductVo.class));
        List<ProductVo> productVoList = productVoIpage.getRecords();

        if (CollectionUtils.isEmpty(productVoList)) {
            return (Page<ProductVo>) productVoIpage;
        }
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

        productVoIpage.setRecords(productVoList);
        return (Page<ProductVo>) productVoIpage;
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

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean save(ProductDtoParam productDtoParam) {
        // TODO 数据校验
        ProductEntity productEntity = BeanCopy.convert(productDtoParam, ProductEntity.class);
        // 校验 画册图片，连产品图片限制为5张，以逗号分割
        Set<String> albumPics = productDtoParam.getAlbumPics();
        String albumPicStr = checkAlbumPics(albumPics);

        if (Objects.nonNull(albumPicStr)) {
            // 设置 画册
            productEntity.setAlbumPics(albumPicStr);
        }
        // 设置  以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮
        String checkServiceIds = checkServiceIds(productDtoParam.getServiceIds());
        if (Objects.nonNull(checkServiceIds)) {
            productEntity.setServiceIds(checkServiceIds);
        }
        if (!productEntity.insert()) {
            throw new BaseRequestException("添加失败");
        }
        List<SkuStockDto> skuStockList = productDtoParam.getSkuStockList();
        if (CollectionUtils.isNotEmpty(skuStockList)) {
            skuStockList.forEach(skuStockDto -> skuStockDto.setProductId(productEntity.getId()));
            // 在 skuStockService.save 进行数据校验当数据校验为通过即回滚保存 的商品
            if (!skuStockService.save(skuStockList)) {
                throw new BaseRequestException("添加失败");
            }
        }

        List<ProductAttributeValueDto> productAttributeValueList = productDtoParam.getProductAttributeValueList();
        if (CollectionUtils.isNotEmpty(productAttributeValueList)) {
            productAttributeValueList.forEach(productAttributeValueDto -> productAttributeValueDto.setProductId(productEntity.getId()));
            // 在 productAttributeValueService.save 进行数据校验当数据校验为通过即回滚保存 的商品
            if (!productAttributeValueService.save(productAttributeValueList)) {
                throw new BaseRequestException("添加失败");
            }

        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean updateById(ProductDtoParam productDtoParam) {
        // TODO 数据校验
        ProductEntity productEntity = BeanCopy.convert(productDtoParam, ProductEntity.class);
        Set<String> albumPics = productDtoParam.getAlbumPics();
        // 校验 画册图片，连产品图片限制为5张，以逗号分割
        String albumPicStr = checkAlbumPics(albumPics);
        if (StringUtils.isNoneBlank(albumPicStr)) {
            // 设置 画册
            productEntity.setAlbumPics(albumPicStr);
        }
        // 设置  以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮
        String checkServiceIds = checkServiceIds(productDtoParam.getServiceIds());
        if (StringUtils.isNoneBlank(checkServiceIds)) {
            productEntity.setServiceIds(checkServiceIds);
        }
        // 更新
        if (!productEntity.updateById()) {
            throw new BaseRequestException("添加失败");
        }
        List<SkuStockDto> skuStockList = productDtoParam.getSkuStockList();
        if (CollectionUtils.isNotEmpty(skuStockList)) {
            skuStockList.forEach(skuStockDto -> skuStockDto.setProductId(productEntity.getId()));
            // 删除 商品的 skuStock 重新对其进行添加
            if (!skuStockService.removeByProductId(productEntity.getId())) {
                throw new BaseRequestException("修改失败");
            }
            // 在 skuStockService.save 进行数据校验当数据校验为通过即回滚保存 的商品
            if (!skuStockService.save(skuStockList)) {
                throw new BaseRequestException("修改失败");
            }
        }

        List<ProductAttributeValueDto> productAttributeValueList = productDtoParam.getProductAttributeValueList();
        if (CollectionUtils.isNotEmpty(productAttributeValueList)) {
            // 删除 商品的 productAttributeValue 重新对其进行添加
            if (!productAttributeValueService.removeByProductId(productEntity.getId())) {
                throw new BaseRequestException("修改失败");
            }
            productAttributeValueList.forEach(productAttributeValueDto -> productAttributeValueDto.setProductId(productEntity.getId()));
            // 在 productAttributeValueService.save 进行数据校验当数据校验为通过即回滚保存 的商品
            if (!productAttributeValueService.save(productAttributeValueList)) {
                throw new BaseRequestException("修改失败");
            }

        }
        return true;
    }

    private String checkAlbumPics(Set<String> albumPics) {
        if (Objects.nonNull(albumPics) && CollectionUtils.isNotEmpty(albumPics)) {
            Set<String> existObject = minioServer.checkObjectIsExist(albumPics);
            if (!existObject.isEmpty()) {
                // 将 set 转换为 List
                List<String> list = new ArrayList<>(existObject);
                // 截取前五个元素
                // 确保 只有五张图片
                List<String> sublist = list.subList(0, Math.min(list.size(), 5));
                return String.join(",", sublist);
            }
        }
        return null;
    }

    private String checkServiceIds(Set<String> serviceIds) {
        Set<String> filteredSet = new HashSet<>();
        if (Objects.nonNull(serviceIds) && CollectionUtils.isNotEmpty(serviceIds)) {

            for (String element : serviceIds) {
                if ("1".equals(element) || "2".equals(element) || "3".equals(element)) {
                    filteredSet.add(element);
                }
            }
            if (!filteredSet.isEmpty()) {
                return String.join(",", filteredSet);
            }
        }
        return null;
    }

    @Override
    public ProductVo getByProductId(Serializable id) {
        // 数据校验
        if (Objects.isNull(id)) {
            throw new BaseRequestException("参数有误");
        }
        ProductEntity productEntity = getById(id);
        return BeanCopy.convert(productEntity, ProductVo.class);
    }

    @Override
    public Boolean updateStatus(Long id, Boolean status) {
        // 数据校验
        if (Objects.isNull(id) || Objects.isNull(status)) {
            throw new BaseRequestException("参数有误");
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setPublishStatus(status);
        productEntity.setId(id);
        return productEntity.updateById();
    }

    @Override
    public Boolean updateAudit(Long id, Boolean audit) {
        // 数据校验
        if (Objects.isNull(id) || Objects.isNull(audit)) {
            throw new BaseRequestException("参数有误");
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setVerifyStatus(audit);
        productEntity.setId(id);
        return productEntity.updateById();
    }

    @Override
    public List<ProductVo> getByIds(Set<Long> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            throw new BaseRequestException("参数有误");
        }
        List<ProductEntity> productEntityList = listByIds(productIds);
        return BeanCopy.copytList(productEntityList, ProductVo.class);
    }
}

