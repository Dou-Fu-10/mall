package org.example.modules.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.product.entity.ProductAttributeValueEntity;
import org.example.modules.product.entity.dto.ProductAttributeValueDto;
import org.example.modules.product.entity.vo.ProductAttributeValueVo;

import java.util.List;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-08-01 22:28:59
 *
 * @author Dou-Fu-10
 * @date 2023-08-01 22:28:59
 * @Description 存储产品参数信息的表(ProductAttributeValue)表服务接口
 */
public interface ProductAttributeValueService extends IService<ProductAttributeValueEntity> {
    /**
     * 新增数据
     *
     * @param productAttributeValueDto 实体对象
     * @return 新增结果
     */
    Boolean save(ProductAttributeValueDto productAttributeValueDto);

    /**
     * 修改数据
     *
     * @param productAttributeValueDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(ProductAttributeValueDto productAttributeValueDto);

    /**
     * 分页查询所有数据
     *
     * @param page                     分页对象
     * @param productAttributeValueDto 查询实体
     * @return 所有数据
     */
    Page<ProductAttributeValueVo> page(Page<ProductAttributeValueEntity> page, ProductAttributeValueDto productAttributeValueDto);

    /**
     * 通过商品id 获取 商品属性 value
     *
     * @param productId 商品id
     * @return 商品属性 value
     */
    List<ProductAttributeValueVo> getProductAttributeValueByProductId(Long productId);

    /**
     * 通过商品id列表获取 商品属性 value
     *
     * @param productIds 商品id列表
     * @return 商品属性 value
     */
    List<ProductAttributeValueVo> getByProductAttributeIds(Set<Long> productIds);
}
