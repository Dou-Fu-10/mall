package org.example.modules.product.service;

import org.example.modules.member.entity.dto.MemberPriceDto;
import org.example.modules.product.entity.ProductAttributeValueEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.product.entity.dto.ProductAttributeValueDto;

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
    boolean save(ProductAttributeValueDto productAttributeValue);
    /**
     * 新增数据
     *
     * @param productAttributeValue 实体对象
     * @return 新增结果
     */
    boolean save(List<ProductAttributeValueDto> productAttributeValue);
    /**
     * 修改数据
     *
     * @param productAttributeValue 实体对象
     * @return 修改结果
     */
    boolean updateById(ProductAttributeValueDto productAttributeValue);
}
