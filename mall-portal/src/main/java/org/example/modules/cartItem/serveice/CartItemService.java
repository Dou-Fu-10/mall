package org.example.modules.cartItem.serveice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.cartItem.entity.CartItemEntity;
import org.example.modules.cartItem.entity.dto.AddCartItemDto;
import org.example.modules.cartItem.entity.dto.CartItemDto;
import org.example.modules.cartItem.entity.vo.CartItemVo;

import java.util.List;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-07-14 14:35:11
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:35:11
 * @Description 购物车表(CartItem)表服务接口
 */
public interface CartItemService extends IService<CartItemEntity> {
    /**
     * 新增数据
     *
     * @param cartItem 实体对象
     * @return 新增结果
     */
    Boolean save(AddCartItemDto cartItem);

    /**
     * 修改数据
     *
     * @param cartItem 实体对象
     * @return 修改结果
     */
    Boolean updateById(CartItemDto cartItem);

    /**
     * 通过会员id和购物车id获取购物车信息
     *
     * @param memberId 会员id
     * @param cartIds  购物车id列表
     * @return 购物车信息
     */
    List<CartItemVo> getCartItemByMemberIdAndCartIds(Long memberId, Set<Long> cartIds);

    /**
     * 删除购物车信息
     *
     * @param cartItemIds 购物车id
     * @param memberId    会员id
     * @return Boolean
     */
    Boolean removeBatchByIdsAndMemberId(Set<Long> cartItemIds, Long memberId);

    /**
     * 按传入的月份查找月份已完成的订单
     *
     * @param page 分页对象
     * @return 已完成的订单
     */
    Page<CartItemVo> page(Page<CartItemEntity> page);

    /**
     * 修改数量
     *
     * @param cartItemId 修改数量id
     * @param quantity   修改数量
     * @return 修改结果
     */
    Boolean modifiedQuantity(Long cartItemId, Integer quantity);
}
