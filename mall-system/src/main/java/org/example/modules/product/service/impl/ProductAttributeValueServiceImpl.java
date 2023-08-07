package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.SetUtils;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.product.entity.ProductAttributeEntity;
import org.example.modules.product.entity.ProductAttributeValueEntity;
import org.example.modules.product.entity.ProductEntity;
import org.example.modules.product.entity.dto.ProductAttributeValueDto;
import org.example.modules.product.entity.vo.ProductAttributeValueVo;
import org.example.modules.product.entity.vo.ProductAttributeVo;
import org.example.modules.product.entity.vo.ProductVo;
import org.example.modules.product.mapper.ProductAttributeValueMapper;
import org.example.modules.product.service.ProductAttributeService;
import org.example.modules.product.service.ProductAttributeValueService;
import org.example.modules.product.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-14 13:54:16
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:54:16
 * @Description 存储产品参数信息的表(ProductAttributeValue)表服务实现类
 */
@Service("productAttributeValueService")
public class ProductAttributeValueServiceImpl extends ServiceImpl<ProductAttributeValueMapper, ProductAttributeValueEntity> implements ProductAttributeValueService {
    @Resource
    private ProductAttributeService productAttributeService;
    @Resource
    private ProductService productService;

    @Override
    public Boolean save(ProductAttributeValueDto productAttributeValue) {
        Long productId = productAttributeValue.getProductId();
        Long productAttributeId = productAttributeValue.getProductAttributeId();
        // 校验 商品属性是否存在
        ProductAttributeEntity productAttributeEntity = productAttributeService.getById(productAttributeId);
        if (Objects.isNull(productAttributeEntity)) {
            throw new BaseRequestException("参数输入有误");
        }
        // 校验商品是否存在
        ProductEntity productEntity = productService.getById(productId);
        if (Objects.isNull(productEntity)) {
            throw new BaseRequestException("参数输入有误");
        }
        ProductAttributeValueEntity productAttributeValueEntity = BeanCopy.convert(productAttributeValue, ProductAttributeValueEntity.class);
        return productAttributeValueEntity.insert();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean save(List<ProductAttributeValueDto> productAttributeValue) {
        List<ProductAttributeValueEntity> productAttributeValueEntityList = BeanCopy.copytList(productAttributeValue, ProductAttributeValueEntity.class);

        Set<Long> productAttributeIds = productAttributeValueEntityList.stream().map(ProductAttributeValueEntity::getProductAttributeId).collect(Collectors.toSet());
        Set<Long> productIds = productAttributeValueEntityList.stream().map(ProductAttributeValueEntity::getProductId).collect(Collectors.toSet());

        if (CollectionUtils.isEmpty(productAttributeIds)) {
            throw new BaseRequestException("参数输入有误");
        }
        if (CollectionUtils.isEmpty(productIds)) {
            throw new BaseRequestException("参数输入有误");
        }

        // 校验 商品属性是否存在
        List<ProductAttributeVo> productAttributeVoList = productAttributeService.getByIds(productAttributeIds);
        Set<Long> productAttributeIdList = productAttributeVoList.stream().map(ProductAttributeVo::getId).collect(Collectors.toSet());
        if (!SetUtils.isEqualSet(productAttributeIdList, productAttributeIds)) {
            throw new BaseRequestException("参数输入有误");
        }
        // 校验商品是否存在
        List<ProductVo> productVoList = productService.getByIds(productIds);
        Set<Long> productIdList = productVoList.stream().map(ProductVo::getId).collect(Collectors.toSet());
        if (!SetUtils.isEqualSet(productIdList, productIds)) {
            throw new BaseRequestException("参数输入有误");
        }
        // 保存失败即回滚
        return saveBatch(productAttributeValueEntityList);
    }

    @Override
    public Boolean updateById(ProductAttributeValueDto productAttributeValue) {
        Long productId = productAttributeValue.getProductId();
        Long productAttributeId = productAttributeValue.getProductAttributeId();
        // 校验 商品属性是否存在
        ProductAttributeEntity productAttributeEntity = productAttributeService.getById(productAttributeId);
        if (Objects.isNull(productAttributeEntity)) {
            throw new BaseRequestException("参数输入有误");
        }
        // 校验商品是否存在
        ProductEntity productEntity = productService.getById(productId);
        if (Objects.isNull(productEntity)) {
            throw new BaseRequestException("参数输入有误");
        }
        ProductAttributeValueEntity productAttributeValueEntity = BeanCopy.convert(productAttributeValue, ProductAttributeValueEntity.class);
        return productAttributeValueEntity.updateById();
    }

    @Override
    public List<ProductAttributeValueVo> getProductAttributeValueByProductId(Long productId) {
        List<ProductAttributeValueEntity> productAttributeValueEntityList = lambdaQuery().eq(ProductAttributeValueEntity::getProductId, productId).list();
        return BeanCopy.copytList(productAttributeValueEntityList, ProductAttributeValueVo.class);
    }

    @Override
    public Boolean saveOrUpdate(List<ProductAttributeValueDto> productAttributeValue) {
        List<ProductAttributeValueEntity> productAttributeValueEntityList = BeanCopy.copytList(productAttributeValue, ProductAttributeValueEntity.class);
        // TODO 数据校验
        return saveOrUpdateBatch(productAttributeValueEntityList);
    }

    @Override
    public ProductAttributeValueVo getByProductAttributeId(Serializable id) {
        if (Objects.isNull(id)) {
            throw new BaseRequestException("参数输入错误");
        }
        ProductAttributeValueEntity productAttributeValueEntity = getById(id);
        return BeanCopy.convert(productAttributeValueEntity, ProductAttributeValueVo.class);
    }
}

