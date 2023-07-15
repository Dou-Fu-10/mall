package org.example.modules.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.product.entity.ProductAttributeCategoryEntity;
import org.example.modules.product.entity.dto.ProductAttributeCategoryDto;
import org.example.modules.product.entity.vo.ProductAttributeCategoryVo;

import java.util.List;


/**
 * Created by Dou-Fu-10 2023-07-14 11:03:43
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 11:03:43
 * @Description 产品属性分类表(ProductAttributeCategory)表服务接口
 */
public interface ProductAttributeCategoryService extends IService<ProductAttributeCategoryEntity> {
    /**
     * 分页查询所有数据
     *
     * @param page                     分页对象
     * @param productAttributeCategory 查询实体
     * @return 所有数据
     */
    Page<List<ProductAttributeCategoryVo>> page(Page<ProductAttributeCategoryEntity> page, ProductAttributeCategoryDto productAttributeCategory);

    /**
     * 通过分类名字查询分类信息
     *
     * @param productAttributeCategory 分类名字
     * @return 分类信息
     */
    ProductAttributeCategoryEntity getByProductAttributeCategoryName(String productAttributeCategory);

    /**
     * 通过分类id查询分类信息
     *
     * @param id 分类名字
     * @return 分类信息
     */
    ProductAttributeCategoryEntity getByProductAttributeCategoryId(Long id);

    /**
     * 新增数据
     *
     * @param productAttributeCategory 实体对象
     * @return 新增结果
     */
    Boolean save(ProductAttributeCategoryDto productAttributeCategory);

    /***
     * 获取包含属性的属性分类
     * @return 属性
     */
    List<ProductAttributeCategoryVo> getListWithAttr();
    /**
     * 修改数据
     *
     * @param productAttributeCategory 实体对象
     * @return 修改结果
     */
    Boolean updateById(ProductAttributeCategoryDto productAttributeCategory);
}
