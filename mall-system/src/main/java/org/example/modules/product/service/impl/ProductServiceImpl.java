package org.example.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.product.entity.ProductEntity;
import org.example.modules.product.entity.dto.ProductDto;
import org.example.modules.product.mapper.ProductMapper;
import org.example.modules.product.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-14 13:05:47
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:05:47
 * @Description 商品信息(Product)表服务实现类
 */
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductEntity> implements ProductService {

    @Override
    public boolean save(ProductDto product) {
        ProductEntity convert = BeanCopy.convert(product, ProductEntity.class);
        return save(convert);
    }

    @Override
    public boolean updateById(ProductDto product) {
        ProductEntity convert = BeanCopy.convert(product, ProductEntity.class);
        return updateById(convert);
    }
}

