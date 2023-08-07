package org.example.modules.product.serveice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.product.entity.ProductCategoryEntity;
import org.example.modules.product.entity.vo.ProductCategoryVo;

import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-27 14:08:15
 *
 * @author Dou-Fu-10
 * @date 2023-07-27 14:08:15
 * @Description 产品分类(ProductCategory)表服务接口
 */
public interface ProductCategoryService extends IService<ProductCategoryEntity> {
    /**
     * 分页查询所有数据
     *
     * @param page            分页对象
     * @param productCategory 查询实体
     * @return 所有数据
     */
    Page<ProductCategoryVo> page(Page<ProductCategoryEntity> page, ProductCategoryEntity productCategory);

    /**
     * 查询分类
     *
     * @return /
     */
    List<ProductCategoryVo> selectProductCategory();
}
