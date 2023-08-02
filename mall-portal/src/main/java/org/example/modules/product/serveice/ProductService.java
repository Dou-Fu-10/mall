package org.example.modules.product.serveice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.product.entity.ProductEntity;
import org.example.modules.product.entity.dto.ProductDto;
import org.example.modules.product.entity.vo.ProductDetail;
import org.example.modules.product.entity.vo.ProductVo;

import java.io.Serializable;


/**
 * Created by Dou-Fu-10 2023-07-27 13:05:47
 *
 * @author Dou-Fu-10
 * @date 2023-07-27 13:05:47
 * @Description 商品信息(Product)表服务接口
 */
public interface ProductService extends IService<ProductEntity> {
    /**
     * 按Id查找
     *
     * @param id 商品id
     * @return 结果
     */
    ProductVo findById(Serializable id);

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param product 查询实体
     * @return 所有数据
     */
    Page<ProductVo> page(Page<ProductEntity> page, ProductDto product);

    ProductDetail detail(Serializable id);

    Page<ProductVo> search(Page<ProductEntity> page, ProductDto product);
}
