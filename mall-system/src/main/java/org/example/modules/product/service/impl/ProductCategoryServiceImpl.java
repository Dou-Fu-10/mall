package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.product.entity.ProductCategoryEntity;
import org.example.modules.product.mapper.ProductCategoryMapper;
import org.example.modules.product.service.ProductCategoryService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:55
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:55
 * @Description 产品分类(ProductCategory)表服务实现类
 */
@Service("productCategoryService")
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategoryEntity> implements ProductCategoryService {

}

