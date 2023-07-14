package org.example.modules.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.product.entity.ProductEntity;
import org.example.modules.product.entity.dto.ProductDto;


/**
 * Created by PanShiFu 2023-07-14 13:05:47
 *
 * @author PanShiFu
 * @date 2023-07-14 13:05:47
 * @Description 商品信息(Product)表服务接口
 */
public interface ProductService extends IService<ProductEntity> {
    /**
     * 新增数据
     *
     * @param product 实体对象
     * @return 新增结果
     */
    boolean save(ProductDto product);

    /**
     * 修改数据
     *
     * @param product 实体对象
     * @return 修改结果
     */
    boolean updateById(ProductDto product);
}
