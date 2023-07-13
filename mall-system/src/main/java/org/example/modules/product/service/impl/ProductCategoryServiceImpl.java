package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.product.entity.ProductCategoryEntity;
import org.example.modules.product.mapper.ProductCategoryMapper;
import org.example.modules.product.service.ProductCategoryService;

/**
 * Created by PanShiFu 2023-07-13 11:45:00
 *
 * @author PanShiFu
 * @date 2023-07-13 11:45:00
 * @Description 产品分类(ProductCategory)表服务实现类
 */
@Service("productCategoryService")
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategoryEntity> implements ProductCategoryService {

}

