package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.product.entity.ProductAttributeEntity;
import org.example.modules.product.entity.dto.ProductAttributeDto;
import org.example.modules.product.entity.vo.ProductAttributeVo;
import org.example.modules.product.mapper.ProductAttributeMapper;
import org.example.modules.product.service.ProductAttributeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-08-01 22:28:58
 *
 * @author Dou-Fu-10
 * @date 2023-08-01 22:28:58
 * @Description 商品属性参数表(ProductAttribute)表服务实现类
 */
@Service("productAttributeService")
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttributeEntity> implements ProductAttributeService {
    @Override
    public Boolean save(ProductAttributeDto productAttributeDto) {
        ProductAttributeEntity productAttributeEntity = BeanCopy.convert(productAttributeDto, ProductAttributeEntity.class);
        return save(productAttributeEntity);
    }

    @Override
    public Boolean updateById(ProductAttributeDto productAttributeDto) {
        ProductAttributeEntity productAttributeEntity = BeanCopy.convert(productAttributeDto, ProductAttributeEntity.class);
        return updateById(productAttributeEntity);
    }

    @Override
    public Page<ProductAttributeVo> page(Page<ProductAttributeEntity> page, ProductAttributeDto productAttributeDto) {
        ProductAttributeEntity productAttributeEntity = BeanCopy.convert(productAttributeDto, ProductAttributeEntity.class);
        LambdaQueryWrapper<ProductAttributeEntity> productAttributeEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(productAttributeEntity);
        Page<ProductAttributeEntity> productAttributeEntityPage = page(page, productAttributeEntityLambdaQueryWrapper);
        IPage<ProductAttributeVo> productAttributeEntityPageVoIpage = productAttributeEntityPage.convert(productAttribute -> BeanCopy.convert(productAttribute, ProductAttributeVo.class));
        return (Page<ProductAttributeVo>) productAttributeEntityPageVoIpage;
    }

    @Override
    public List<ProductAttributeVo> findListByIds(Set<Long> productAttributeIds) {
        List<ProductAttributeEntity> productAttributeEntityList = listByIds(productAttributeIds);
        return BeanCopy.copytList(productAttributeEntityList, ProductAttributeVo.class);
    }
}

