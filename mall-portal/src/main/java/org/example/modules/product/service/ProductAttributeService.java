package org.example.modules.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.product.entity.ProductAttributeEntity;
import org.example.modules.product.entity.dto.ProductAttributeDto;
import org.example.modules.product.entity.vo.ProductAttributeVo;

import java.util.List;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-08-01 22:28:58
 *
 * @author Dou-Fu-10
 * @date 2023-08-01 22:28:58
 * @Description 商品属性参数表(ProductAttribute)表服务接口
 */
public interface ProductAttributeService extends IService<ProductAttributeEntity> {
    /**
     * 新增数据
     *
     * @param productAttributeDto 实体对象
     * @return 新增结果
     */
    Boolean save(ProductAttributeDto productAttributeDto);

    /**
     * 修改数据
     *
     * @param productAttributeDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(ProductAttributeDto productAttributeDto);

    /**
     * 分页查询所有数据
     *
     * @param page                分页对象
     * @param productAttributeDto 查询实体
     * @return 所有数据
     */
    Page<ProductAttributeVo> page(Page<ProductAttributeEntity> page, ProductAttributeDto productAttributeDto);

    /**
     * 按 id列表 查询对应的属性
     *
     * @param productAttributeIds 属性
     * @return 属性
     */
    List<ProductAttributeVo> findListByIds(Set<Long> productAttributeIds);
}
