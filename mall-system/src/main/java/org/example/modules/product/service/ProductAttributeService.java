package org.example.modules.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.product.entity.ProductAttributeEntity;
import org.example.modules.product.entity.dto.ProductAttributeDto;
import org.example.modules.product.entity.vo.ProductAttributeVo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


/**
 * Created by Dou-Fu-10 2023-07-14 12:49:52
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 12:49:52
 * @Description 商品属性参数表(ProductAttribute)表服务接口
 */
public interface ProductAttributeService extends IService<ProductAttributeEntity> {
    /**
     * 新增数据
     *
     * @param productAttribute 实体对象
     * @return 新增结果
     */
    Boolean save(ProductAttributeDto productAttribute);

    /**
     * 修改数据
     *
     * @param productAttribute 实体对象
     * @return 修改结果
     */
    Boolean updateById(ProductAttributeDto productAttribute);

    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param productAttribute 查询实体
     * @return 所有数据
     */
    Page<ProductAttributeVo> page(Page<ProductAttributeEntity> page, ProductAttributeDto productAttribute);

    /**
     * 通过商品属性分类id 查询商品属性 分页后所有数据
     *
     * @param page                分页对象
     * @param productAttributeDto 通过商品属性分类id
     * @return 所有数据
     */
    Page<ProductAttributeVo> getProductAttributeByProductAttributeCategoryId(Page<ProductAttributeEntity> page, ProductAttributeDto productAttributeDto);

    /**
     * 按 id列表 查询对应的属性
     *
     * @param productAttributeIds 属性
     * @return 属性
     */
    List<ProductAttributeVo> findListByIds(Set<Long> productAttributeIds);

    /**
     * 通过属性分类id列表查询 属性
     *
     * @param productAttributeCategoryId 属性分类id
     * @return 属性
     */
    List<ProductAttributeVo> getByProductAttributeCategoryIds(Set<Long> productAttributeCategoryId);

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    ProductAttributeVo getByProductAttributeId(Serializable id);
    /**
     * 通过商品id属性列表 获取商品属性信息
     * @param productAttributeIds 商品id属性列表
     * @return 获取商品属性信息
     */
    List<ProductAttributeVo> getByIds(Set<Long> productAttributeIds);
}
