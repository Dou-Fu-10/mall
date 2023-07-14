package org.example.modules.product.service;

import org.example.modules.product.entity.ProductAttributeEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.product.entity.dto.ProductAttributeDto;


/**
 * Created by PanShiFu 2023-07-14 12:49:52
 *
 * @author PanShiFu
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
    boolean updateById(ProductAttributeDto productAttribute);
}
