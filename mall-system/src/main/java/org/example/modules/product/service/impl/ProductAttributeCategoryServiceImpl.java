package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.product.entity.ProductAttributeCategoryEntity;
import org.example.modules.product.mapper.ProductAttributeCategoryMapper;
import org.example.modules.product.service.ProductAttributeCategoryService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:54
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:54
 * @Description 产品属性分类表(ProductAttributeCategory)表服务实现类
 */
@Service("productAttributeCategoryService")
public class ProductAttributeCategoryServiceImpl extends ServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategoryEntity> implements ProductAttributeCategoryService {

}
