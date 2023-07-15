package org.example.modules.product.service;

import org.example.modules.product.entity.SkuStockEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.product.entity.dto.SkuStockDto;

import java.util.List;

/**
 * Created by Dou-Fu-10 2023-07-15 11:35:50
 *
 * @author Dou-Fu-10
 * @date 2023-07-15 11:35:50
 * @Description sku的库存(SkuStock)表服务接口
 */
public interface SkuStockService extends IService<SkuStockEntity> {
    /**
     * 新增数据
     *
     * @param skuStock 实体对象
     * @return 新增结果
     */
    boolean save(SkuStockDto skuStock);

    /**
     * 新增数据
     *
     * @param skuStock 实体对象
     * @return 新增结果
     */
    boolean save(List<SkuStockDto> skuStock);

    /**
     * 修改数据
     *
     * @param skuStock 实体对象
     * @return 修改结果
     */
    boolean updateById(SkuStockDto skuStock);

    List<SkuStockDto> getSkuStockByProductId(Long productId);
}
