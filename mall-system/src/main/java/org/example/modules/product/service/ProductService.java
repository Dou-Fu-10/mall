package org.example.modules.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.product.entity.ProductEntity;
import org.example.modules.product.entity.dto.ProductDto;
import org.example.modules.product.entity.dto.ProductDtoParam;
import org.example.modules.product.entity.vo.ProductVo;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Dou-Fu-10 2023-07-14 13:05:47
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:05:47
 * @Description 商品信息(Product)表服务接口
 */
public interface ProductService extends IService<ProductEntity> {
    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param product 查询实体
     * @return 所有数据
     */
    Page<ProductVo> page(Page<ProductEntity> page, ProductDto product);
    /**
     * 新增数据
     *
     * @param product 实体对象
     * @return 新增结果
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    boolean save(ProductDtoParam product);

    /**
     * 修改数据
     *
     * @param product 实体对象
     * @return 修改结果
     */
    boolean updateById(ProductDtoParam product);
}
