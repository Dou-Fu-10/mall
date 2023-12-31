package org.example.modules.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.product.entity.SkuStockEntity;
import org.example.modules.product.entity.dto.SkuStockDto;
import org.example.modules.product.entity.vo.SkuStockVo;

import java.util.List;
import java.util.Set;

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
    Boolean save(SkuStockDto skuStock);

    /**
     * 新增数据
     *
     * @param skuStock 实体对象
     * @return 新增结果
     */
    Boolean save(List<SkuStockDto> skuStock);

    /**
     * 修改数据
     *
     * @param skuStock 实体对象
     * @return 修改结果
     */
    Boolean updateById(SkuStockDto skuStock);

    /**
     * 通过商品id 获取 sku列表
     *
     * @param productId 商品id
     * @return sku列表
     */
    List<SkuStockVo> getSkuStockByProductId(Long productId);

    /**
     * 通过商品id列表 获取 sku列表
     *
     * @param productIds 商品id
     * @return sku列表
     */
    List<SkuStockVo> getSkuStockByProductIds(Set<Long> productIds);

    /**
     * 新增数据
     *
     * @param skuStock 实体对象
     * @return 新增结果
     */
    Boolean saveOrUpdate(List<SkuStockDto> skuStock);

    /**
     * 修改数据
     *
     * @param skuStock 实体对象
     * @return 修改结果
     */
    Boolean updateBatchById(Set<SkuStockDto> skuStock);

    /**
     * 通过商品id 删除sku
     *
     * @param productId 商品id
     * @return /
     */
    Boolean removeByProductId(Long productId);
}
