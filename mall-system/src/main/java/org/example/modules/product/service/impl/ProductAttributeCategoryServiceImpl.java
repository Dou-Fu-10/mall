package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.common.core.utils.StringUtils;
import org.example.modules.product.entity.ProductAttributeCategoryEntity;
import org.example.modules.product.entity.dto.ProductAttributeCategoryDto;
import org.example.modules.product.entity.dto.ProductAttributeDto;
import org.example.modules.product.entity.vo.ProductAttributeCategoryVo;
import org.example.modules.product.entity.vo.ProductAttributeVo;
import org.example.modules.product.mapper.ProductAttributeCategoryMapper;
import org.example.modules.product.service.ProductAttributeCategoryService;
import org.example.modules.product.service.ProductAttributeService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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
    public Page<List<ProductAttributeCategoryVo>> page(Page<ProductAttributeCategoryEntity> page, ProductAttributeCategoryDto productAttributeCategory) {
        ProductAttributeCategoryEntity convert = BeanCopy.convert(productAttributeCategory, ProductAttributeCategoryEntity.class);
        Page<ProductAttributeCategoryEntity> productAttributeCategoryEntityPage = page(page, new QueryWrapper<>(convert));
        return (Page) productAttributeCategoryEntityPage.convert(productAttributeCategoryEntity -> BeanCopy.convert(productAttributeCategoryEntity, ProductAttributeCategoryVo.class));
    }

    @Override
    public ProductAttributeCategoryEntity getByProductAttributeCategoryName(String productAttributeCategory) {
        if (StringUtils.isBlank(productAttributeCategory)){
            throw new BaseRequestException("参数有误");
        }
        return lambdaQuery().eq(ProductAttributeCategoryEntity::getName, productAttributeCategory).one();
    }

    @Override
    public ProductAttributeCategoryEntity getByProductAttributeCategoryId(Long id) {
        if (Objects.isNull(id)){
            throw new BaseRequestException("参数有误");
        }
        return lambdaQuery().eq(ProductAttributeCategoryEntity::getId, id).one();
    }

    @Override
    public ProductAttributeCategoryVo getByProductAttributeCategoryId(Serializable id) {
        if (Objects.isNull(id)){
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
        // 转换成vo
        List<ProductAttributeCategoryVo> productAttributeCategoryVoList = BeanCopy.copytList(productAttributeCategoryEntityList, ProductAttributeCategoryVo.class);
        // 遍历商品属性分类
        for (ProductAttributeCategoryVo productAttributeCategoryVo : productAttributeCategoryVoList) {
            // 查询商品属性分类下的商品属性
            ProductAttributeDto productAttributeDto = new ProductAttributeDto();
            productAttributeDto.setProductAttributeCategoryId(productAttributeCategoryVo.getId());

            Page<ProductAttributeVo> byProductAttributeCategoryId = productAttributeService.getProductAttributeByProductAttributeCategoryId(new Page<>(1, 500), productAttributeDto);
            // 获取到商品属性分类下的 商品属性
            List<ProductAttributeVo> records = byProductAttributeCategoryId.getRecords();
            // 简历父子关系
            productAttributeCategoryVo.setProductAttributeVoList(records);
        }
        return productAttributeCategoryVoList;
    }

    @Override
    public Boolean updateById(ProductAttributeCategoryDto productAttributeCategory) {
        ProductAttributeCategoryEntity convert = BeanCopy.convert(productAttributeCategory, ProductAttributeCategoryEntity.class);
        return convert.updateById();
    }
}

