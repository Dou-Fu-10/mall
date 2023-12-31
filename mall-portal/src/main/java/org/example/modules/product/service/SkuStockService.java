package org.example.modules.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.order.entity.vo.OrderItemVo;
import org.example.modules.product.entity.SkuStockEntity;
import org.example.modules.product.entity.dto.SkuStockDto;
import org.example.modules.product.entity.vo.SkuStockVo;

import java.util.List;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-08-01 22:28:59
 *
 * @author Dou-Fu-10
 * @date 2023-08-01 22:28:59
 * @Description sku的库存(SkuStock)表服务接口
 */
public interface SkuStockService extends IService<SkuStockEntity> {
    /**
     * 新增数据
     *
     * @param skuStockDto 实体对象
     * @return 新增结果
     */
    Boolean save(SkuStockDto skuStockDto);

    /**
     * 修改数据
     *
     * @param skuStockDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(SkuStockDto skuStockDto);

    /**
     * 分页查询所有数据
     *
     * @param page        分页对象
     * @param skuStockDto 查询实体
     * @return 所有数据
     */
    Page<SkuStockVo> page(Page<SkuStockEntity> page, SkuStockDto skuStockDto);

    /**
     * 通过商品id 获取 sku列表
     *
     * @param productId 商品id
     * @return sku列表
     */
    List<SkuStockVo> getSkuStockByProductId(Long productId);

    /**
     * 通过skuId 和 商品列表获取 sku信息
     *
     * @param productSkuId sku Id
     * @param productId    商品id
     * @return SkuStockVo
     */
    SkuStockVo getByIdAndProductId(Long productSkuId, Long productId);

    /**
     * 解除取消订单的库存锁定
     *
     * @param orderItemVoList 订单
     * @return /
     */
    Boolean releaseSkuStockLock(List<OrderItemVo> orderItemVoList);

    /**
     * 解除取消订单的库存锁定
     *
     * @param productIds 商品id
     * @return /
     */
    Boolean releaseSkuStockLock(Set<Long> productIds);

    /**
     * 通过商品id列表 获取 sku列表
     *
     * @param productIds 商品id
     * @return sku列表
     */
    List<SkuStockVo> getSkuStockByProductIds(Set<Long> productIds);

    /**
     * 通过id列表 获取 sku列表
     *
     * @param productSkuIds id列表
     * @return sku列表
     */
    List<SkuStockVo> getByIds(Set<Long> productSkuIds);

    /**
     * 通过id 获取 sku
     *
     * @param productSkuId id
     * @return sku
     */
    SkuStockVo getBySkuStockId(Long productSkuId);
}
