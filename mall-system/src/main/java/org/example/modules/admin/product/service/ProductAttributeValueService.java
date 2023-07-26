package org.example.modules.admin.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.admin.product.entity.ProductAttributeValueEntity;
import org.example.modules.admin.product.entity.dto.ProductAttributeValueDto;
import org.example.modules.admin.product.entity.vo.ProductAttributeValueVo;

import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-14 13:54:16
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:54:16
 * @Description 存储产品参数信息的表(ProductAttributeValue)表服务接口
 */
public interface ProductAttributeValueService extends IService<ProductAttributeValueEntity> {
    /**
     * 新增数据
     *
     * @param productAttributeValue 实体对象
     * @return 新增结果
     */
    Boolean save(ProductAttributeValueDto productAttributeValue);

    /**
     * 新增数据
     *
     * @param productAttributeValue 实体对象
     * @return 新增结果
     */
    Boolean save(List<ProductAttributeValueDto> productAttributeValue);

    /**
     * 修改数据
     *
     * @param productAttributeValue 实体对象
     * @return 修改结果
     */
    Boolean updateById(ProductAttributeValueDto productAttributeValue);

    /**
     * 通过商品id 获取 商品属性 value
     *
     * @param productId 商品id
     * @return 商品属性 value
     */
    List<ProductAttributeValueVo> getProductAttributeValueByProductId(Long productId);

    /**
     * 新增数据
     *
     * @param productAttributeValue 实体对象
     * @return 新增结果
     */
    Boolean saveOrUpdate(List<ProductAttributeValueDto> productAttributeValue);
}
