package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.product.entity.ProductAttributeCategoryEntity;
import org.example.modules.product.entity.ProductAttributeEntity;
import org.example.modules.product.entity.dto.ProductAttributeDto;
import org.example.modules.product.entity.vo.ProductAttributeVo;
import org.example.modules.product.mapper.ProductAttributeMapper;
import org.example.modules.product.service.ProductAttributeCategoryService;
import org.example.modules.product.service.ProductAttributeService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-07-14 12:49:52
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 12:49:52
 * @Description 商品属性参数表(ProductAttribute)表服务实现类
 */
@Service("productAttributeService")
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttributeEntity> implements ProductAttributeService {
    @Resource
    @Lazy
    private ProductAttributeCategoryService productAttributeCategoryService;

    @Override
    public Boolean save(ProductAttributeDto productAttribute) {
        ProductAttributeCategoryEntity productAttributeCategoryEntity = productAttributeCategoryService.getByProductAttributeCategoryId(productAttribute.getProductAttributeCategoryId());
        if (Objects.isNull(productAttributeCategoryEntity)) {
            throw new BaseRequestException("商品属性分类错误");
        }
        ProductAttributeEntity convert = BeanCopy.convert(productAttribute, ProductAttributeEntity.class);
        return save(convert);
    }

    @Override
    public Boolean updateById(ProductAttributeDto productAttribute) {
        ProductAttributeCategoryEntity productAttributeCategoryEntity = productAttributeCategoryService.getByProductAttributeCategoryId(productAttribute.getProductAttributeCategoryId());
        if (Objects.isNull(productAttributeCategoryEntity)) {
            throw new BaseRequestException("商品属性分类错误");
        }
        ProductAttributeEntity convert = BeanCopy.convert(productAttribute, ProductAttributeEntity.class);
        return updateById(convert);
    }

    @Override
    public Page<ProductAttributeVo> page(Page<ProductAttributeEntity> page, ProductAttributeDto productAttribute) {
        ProductAttributeEntity convert = BeanCopy.convert(productAttribute, ProductAttributeEntity.class);
        Page<ProductAttributeEntity> productAttributeEntityPage = page(page, new QueryWrapper<>(convert));
        return (Page<ProductAttributeVo>) productAttributeEntityPage.convert(productAttributeEntity -> BeanCopy.convert(productAttributeEntity, ProductAttributeVo.class));
    }

    @Override
    public Page<ProductAttributeVo> getProductAttributeByProductAttributeCategoryId(Page<ProductAttributeEntity> page, ProductAttributeDto productAttributeDto) {

        LambdaQueryWrapper<ProductAttributeEntity> productAttributeEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 通过商品属性分类id 查询商品属性
        productAttributeEntityLambdaQueryWrapper.eq(Objects.nonNull(productAttributeDto.getProductAttributeCategoryId()), ProductAttributeEntity::getProductAttributeCategoryId, productAttributeDto.getProductAttributeCategoryId());
        // 属性的类型；0->规格；1->参数 查询商品属性
        productAttributeEntityLambdaQueryWrapper.eq(Objects.nonNull(productAttributeDto.getType()), ProductAttributeEntity::getType, productAttributeDto.getType());
        // 排序
        productAttributeEntityLambdaQueryWrapper.orderByDesc(ProductAttributeEntity::getSort);
        Page<ProductAttributeEntity> productAttributeEntityPage = page(page, productAttributeEntityLambdaQueryWrapper);
        return (Page<ProductAttributeVo>) productAttributeEntityPage.convert(productAttribute -> BeanCopy.convert(productAttribute, ProductAttributeVo.class));
    }

    @Override
    public List<ProductAttributeVo> findListByIds(Set<Long> productAttributeIds) {
        List<ProductAttributeEntity> productAttributeEntityList = listByIds(productAttributeIds);
        return BeanCopy.copytList(productAttributeEntityList, ProductAttributeVo.class);
    }

    @Override
    public List<ProductAttributeVo> getByProductAttributeCategoryIds(Set<Long> productAttributeCategoryId) {
        List<ProductAttributeEntity> productAttributeEntityList = lambdaQuery().in(ProductAttributeEntity::getProductAttributeCategoryId, productAttributeCategoryId).list();
        return BeanCopy.copytList(productAttributeEntityList, ProductAttributeVo.class);
    }

    @Override
    public ProductAttributeVo getByProductAttributeId(Serializable id) {
        if (Objects.isNull(id)) {
            throw new BaseRequestException("参数错误");
        }
        ProductAttributeEntity productAttributeEntity = getById(id);
        return BeanCopy.convert(productAttributeEntity, ProductAttributeVo.class);
    }

    @Override
    public List<ProductAttributeVo> getByIds(Set<Long> productAttributeIds) {
        if (CollectionUtils.isEmpty(productAttributeIds)) {
            throw new BaseRequestException("参数有误");
        }
        List<ProductAttributeEntity> productAttributeEntityList = listByIds(productAttributeIds);
        return BeanCopy.copytList(productAttributeEntityList, ProductAttributeVo.class);

    }
}

