package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.common.core.utils.StringUtils;
import org.example.modules.product.entity.ProductAttributeCategoryEntity;
import org.example.modules.product.entity.dto.ProductAttributeCategoryDto;
import org.example.modules.product.entity.vo.ProductAttributeCategoryVo;
import org.example.modules.product.entity.vo.ProductAttributeVo;
import org.example.modules.product.mapper.ProductAttributeCategoryMapper;
import org.example.modules.product.service.ProductAttributeCategoryService;
import org.example.modules.product.service.ProductAttributeService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-14 11:03:43
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 11:03:43
 * @Description 产品属性分类表(ProductAttributeCategory)表服务实现类
 */
@Service("productAttributeCategoryService")
public class ProductAttributeCategoryServiceImpl extends ServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategoryEntity> implements ProductAttributeCategoryService {

    @Resource
    ProductAttributeService productAttributeService;

    @Override
    public Page<ProductAttributeCategoryVo> page(Page<ProductAttributeCategoryEntity> page, ProductAttributeCategoryDto productAttributeCategory) {
        ProductAttributeCategoryEntity convert = BeanCopy.convert(productAttributeCategory, ProductAttributeCategoryEntity.class);
        // 获取全部的属性分类
        Page<ProductAttributeCategoryEntity> productAttributeCategoryEntityPage = page(page, new QueryWrapper<>(convert));
        IPage<ProductAttributeCategoryVo> productAttributeCategoryVoIPage = productAttributeCategoryEntityPage.convert(productAttributeCategoryEntity -> BeanCopy.convert(productAttributeCategoryEntity, ProductAttributeCategoryVo.class));
        return (Page<ProductAttributeCategoryVo>) productAttributeCategoryVoIPage;
    }

    @Override
    public ProductAttributeCategoryEntity getByProductAttributeCategoryName(String productAttributeCategory) {
        if (StringUtils.isBlank(productAttributeCategory)) {
            throw new BaseRequestException("参数有误");
        }
        return lambdaQuery().eq(ProductAttributeCategoryEntity::getName, productAttributeCategory).one();
    }

    @Override
    public ProductAttributeCategoryEntity getByProductAttributeCategoryId(Long id) {
        if (Objects.isNull(id)) {
            throw new BaseRequestException("参数有误");
        }
        return lambdaQuery().eq(ProductAttributeCategoryEntity::getId, id).one();
    }

    @Override
    public ProductAttributeCategoryVo getByProductAttributeCategoryId(Serializable id) {
        if (Objects.isNull(id)) {
            throw new BaseRequestException("参数有误");
        }
        ProductAttributeCategoryEntity productAttributeCategoryEntity = getById(id);
        return BeanCopy.convert(productAttributeCategoryEntity, ProductAttributeCategoryVo.class);
    }

    @Override
    public Boolean save(ProductAttributeCategoryDto productAttributeCategory) {
        ProductAttributeCategoryEntity productAttributeCategoryEntity = BeanCopy.convert(productAttributeCategory, ProductAttributeCategoryEntity.class);
        // 校验商品分类名字是否唯一
        if (Objects.nonNull(getByProductAttributeCategoryName(productAttributeCategoryEntity.getName()))) {
            throw new BaseRequestException("属性分类名不唯一");
        }

        return productAttributeCategoryEntity.insert();
    }

    @Override
    public List<ProductAttributeCategoryVo> getListWithAttr() {
        // 获取商品属性分类
        List<ProductAttributeCategoryEntity> productAttributeCategoryEntityList = list();
        List<ProductAttributeCategoryVo> productAttributeCategoryVoList = BeanCopy.copytList(productAttributeCategoryEntityList, ProductAttributeCategoryVo.class);
        // 属性分类为空即返回
        if (CollectionUtils.isEmpty(productAttributeCategoryVoList)) {
            return new ArrayList<>();
        }
        Set<Long> productAttributeCategoryId = productAttributeCategoryVoList.stream().map(ProductAttributeCategoryVo::getId).collect(Collectors.toSet());
        // 通过属性分类id 查询属性
        List<ProductAttributeVo> productAttributeVoList = productAttributeService.getByProductAttributeCategoryIds(productAttributeCategoryId);
        Map<Long, List<ProductAttributeVo>> longListMap = longListMap(productAttributeVoList);
        // 将属性分配到属性分类下
        productAttributeCategoryVoList.forEach(productAttributeCategoryVo -> {
            Long productAttributeCategoryVoId = productAttributeCategoryVo.getId();
            if (longListMap.containsKey(productAttributeCategoryVoId)) {
                productAttributeCategoryVo.setProductAttributeVoList(longListMap.get(productAttributeCategoryVoId));
            }
        });
        return productAttributeCategoryVoList;
    }

    private Map<Long, List<ProductAttributeVo>> longListMap(List<ProductAttributeVo> productAttributeVoList) {
        if (CollectionUtils.isEmpty(productAttributeVoList)) {
            return new HashMap<>();
        }
        Map<Long, List<ProductAttributeVo>> longListMap = new HashMap<>();
        productAttributeVoList.forEach(productAttributeVo -> {
            Long productAttributeCategoryId = productAttributeVo.getProductAttributeCategoryId();
            longListMap.computeIfAbsent(productAttributeCategoryId, k -> new ArrayList<>()).add(productAttributeVo);
        });
        return longListMap;
    }

    @Override
    public Boolean updateById(ProductAttributeCategoryDto productAttributeCategory) {
        ProductAttributeCategoryEntity convert = BeanCopy.convert(productAttributeCategory, ProductAttributeCategoryEntity.class);
        return convert.updateById();
    }
}

