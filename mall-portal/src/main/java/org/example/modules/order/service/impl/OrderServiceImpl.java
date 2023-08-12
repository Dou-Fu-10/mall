package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.common.core.utils.OrderUtils;
import org.example.modules.cartItem.entity.vo.CartItemVo;
import org.example.modules.cartItem.serveice.CartItemService;
import org.example.modules.member.entity.vo.MemberReceiveAddressVo;
import org.example.modules.member.service.MemberReceiveAddressService;
import org.example.modules.order.entity.OrderEntity;
import org.example.modules.order.entity.OrderItemEntity;
import org.example.modules.order.entity.dto.GenerateOrderDto;
import org.example.modules.order.entity.dto.OrderDto;
import org.example.modules.order.entity.vo.*;
import org.example.modules.order.mapper.OrderMapper;
import org.example.modules.order.service.OrderItemService;
import org.example.modules.order.service.OrderService;
import org.example.modules.order.service.OrderSettingService;
import org.example.modules.product.entity.SkuStockEntity;
import org.example.modules.product.entity.vo.ProductVo;
import org.example.modules.product.entity.vo.SkuStockVo;
import org.example.modules.product.service.SkuStockService;
import org.example.security.utils.SecurityUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-08-03 14:28:08
 *
 * @author Dou-Fu-10
 * @date 2023-08-03 14:28:08
 * @Description 订单表(Order)表服务实现类
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {
    @Resource
    private MemberReceiveAddressService memberReceiveAddressService;
    @Resource
    private CartItemService cartItemService;
    @Resource
    private OrderSettingService orderSettingService;
    @Resource
    private SkuStockService skuStockService;
    @Resource
    private OrderItemService orderItemService;


    @NotNull
    private static List<OrderItemEntity> getOrderItemEntities(@NotNull List<CartItemVo> cartItemVoList) {
        List<OrderItemEntity> orderItemEntityList = new ArrayList<>();
        for (CartItemVo cartItemVo : cartItemVoList) {
            // 获取 sku
            SkuStockVo skuStock = cartItemVo.getSkuStock();
            // 获取商品
            ProductVo product = cartItemVo.getProduct();
            // 生成下单商品信息
            OrderItemEntity orderItem = new OrderItemEntity();
            // 商品id
            orderItem.setProductId(cartItemVo.getProductId());
            // 商品sku编号
            orderItem.setProductSkuId(cartItemVo.getProductSkuId());
            // 商品分类id
            orderItem.setProductCategoryId(cartItemVo.getProductCategoryId());
            // 商品主图
            orderItem.setProductPic(skuStock.getPic());
            // 商品名字
            orderItem.setProductName(product.getName());
            // 商品编号
            orderItem.setProductSn(cartItemVo.getProductSn());
            // 使用下单时商品的实时价格
            orderItem.setProductPrice(skuStock.getPrice());
            // 购买数量
            orderItem.setProductQuantity(cartItemVo.getQuantity());
            // 商品sku条码
            orderItem.setProductSkuCode(skuStock.getSkuCode());
            // 商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
            orderItem.setProductAttr(skuStock.getSpData());
            orderItemEntityList.add(orderItem);
        }
        return orderItemEntityList;
    }

    @Override
    public Boolean save(OrderDto orderDto) {
        OrderEntity orderEntity = BeanCopy.convert(orderDto, OrderEntity.class);
        return save(orderEntity);
    }

    @Override
    public Boolean updateById(OrderDto orderDto) {
        OrderEntity orderEntity = BeanCopy.convert(orderDto, OrderEntity.class);
        return updateById(orderEntity);
    }

    @Override
    public Page<OrderVo> page(Page<OrderEntity> page, OrderDto orderDto) {
        OrderEntity orderEntity = BeanCopy.convert(orderDto, OrderEntity.class);
        // 获取登录者id
        Long currentUserId = SecurityUtils.getCurrentUserId();
        // 只获取登录者的订单信息
        orderEntity.setMemberId(currentUserId);
        // 获取 会员订单订单
        LambdaQueryWrapper<OrderEntity> orderEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(orderEntity);
        orderEntityLambdaQueryWrapper.orderByDesc(OrderEntity::getId);
        Page<OrderEntity> orderEntityPage = page(page, orderEntityLambdaQueryWrapper);
        // 对订单进行转换
        IPage<OrderVo> orderEntityPageVoIPage = orderEntityPage.convert(order -> BeanCopy.convert(order, OrderVo.class));
        List<OrderVo> orderVoList = orderEntityPageVoIPage.getRecords();
        Set<Long> orderIds = orderVoList.stream().map(OrderVo::getId).collect(Collectors.toSet());
        // 订单中所包含的商品
        List<OrderItemVo> orderItemVo = orderItemService.getByOrderIds(orderIds);
        // 将订单中所包含的商品 商品的id为key 以map 的形式进行存储
        Map<Long, List<OrderItemVo>> longOrderItemVoMap = longOrderItemVoMap(orderItemVo);
        orderVoList.forEach(orderVo -> {
            // 商品的id
            Long id = orderVo.getId();
            if (longOrderItemVoMap.containsKey(id)) {
                // 将订单中所包含的商品 存储到 orderVo里面
                orderVo.setOrderItemList(longOrderItemVoMap.get(id));
            }
        });
        return (Page<OrderVo>) orderEntityPageVoIPage;
    }


    @NotNull
    private Map<Long, List<OrderItemVo>> longOrderItemVoMap(@NotNull List<OrderItemVo> orderItemVo) {
        // 将 订单中所包含的商品   以map 的形式进行存储    键为 订单的id   值为 订单中所包含的商品
        Map<Long, List<OrderItemVo>> longListHashMap = new HashMap<>();
        for (OrderItemVo orderItem : orderItemVo) {
            Long orderId = orderItem.getOrderId();
            // 使用computeIfAbsent方法来避免重复的get和put操作
            longListHashMap.computeIfAbsent(orderId, k -> new ArrayList<>()).add(orderItem);
        }
        return longListHashMap;
    }


    @Override
    public ConfirmOrderVo generateConfirmOrder(Set<Long> cartIds) {
        // 获取会员id
        Long memberId = SecurityUtils.getCurrentUserId();
        ConfirmOrderVo confirmOrderVo = new ConfirmOrderVo();
        // 获取会员地址
        List<MemberReceiveAddressVo> memberReceiveAddressVoList = memberReceiveAddressService.getReceiveAddressByMemberId(memberId);
        confirmOrderVo.setMemberReceiveAddressList(memberReceiveAddressVoList);
        // 获取用户购物车信息
        List<CartItemVo> cartItemVoList = cartItemService.getCartItemByMemberIdAndCartIds(memberId, cartIds);
        confirmOrderVo.setCartItemVoList(cartItemVoList);
        confirmOrderVo.setCalculateAmount(calcTotalAmount(cartItemVoList));
        return confirmOrderVo;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean generateOrder(@NotNull GenerateOrderDto generateOrderDto) {
        // 获取下单用户
        Long memberId = SecurityUtils.getCurrentUserId();
        Long memberReceiveAddressId = generateOrderDto.getMemberReceiveAddressId();
        // 获取用户 下单地址
        MemberReceiveAddressVo address = memberReceiveAddressService.getReceiveAddressByMemberIdAndMemberReceiveAddressId(memberReceiveAddressId, memberId);
        if (Objects.isNull(address)) {
            throw new BaseRequestException("请正确的填写地址");
        }
        // 获取用户购物车信息
        List<CartItemVo> cartItemVoList = cartItemService.getCartItemByMemberIdAndCartIds(memberId, generateOrderDto.getCartIds());
        // 判断购物车中商品是否都有库存
        if (!hasInventory(cartItemVoList)) {
            throw new BaseRequestException("库存不足");
        }
        // 获取订单项
        List<OrderItemEntity> orderItemEntityList = getOrderItemEntities(cartItemVoList);

        // 获取 平台制定信息
        OrderSettingVo orderSettingVo = orderSettingService.getOne();
        // TODO 进行库存锁定
        lockStock(cartItemVoList);

        OrderEntity orderEntity = new OrderEntity();
        // 会员id
        orderEntity.setMemberId(memberId);
        // 订单编号
        orderEntity.setOrderSn(OrderUtils.getOrderCode(memberId));
        // 计算金额
        CalculateAmountVo calculateAmountVo = calcTotalAmount(cartItemVoList);
        // totalAmount
        orderEntity.setTotalAmount(calculateAmountVo.getTotalAmount());
        // 应付金额（实际支付金额）
        orderEntity.setPayAmount(calculateAmountVo.getPayAmount());
        // 运费金额
        orderEntity.setFreightAmount(calculateAmountVo.getFreightAmount());
        // 0->未支付；1->支付宝；2->微信；3->本地钱包支付
        orderEntity.setPayType(generateOrderDto.getPayType());
        // 订单来源：0->PC订单；1->app订单
        orderEntity.setSourceType(1);
        // 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
        orderEntity.setStatus(0);
        // 自动确认时间（天）
        orderEntity.setAutoConfirmDay(orderSettingVo.getConfirmOvertime());
        // 收货人姓名
        orderEntity.setReceiverName(address.getName());
        // 收货人电话
        orderEntity.setReceiverPhone(address.getPhone());
        // 收货人邮编
        orderEntity.setReceiverPostCode(address.getPostCode());
        // 省份/直辖市
        orderEntity.setReceiverProvince(address.getProvince());
        // 城市
        orderEntity.setReceiverCity(address.getCity());
        // 区
        orderEntity.setReceiverRegion(address.getRegion());
        // 详细地址
        orderEntity.setReceiverDetailAddress(address.getDetailAddress());
        // 确认收货状态：1=true->已确认：0=false->未确认
        orderEntity.setConfirmStatus(false);
        if (!orderEntity.insert()) {
            throw new BaseRequestException("下单失败");
        }
        for (OrderItemEntity orderItem : orderItemEntityList) {
            orderItem.setOrderId(orderEntity.getId());
            orderItem.setOrderSn(orderEntity.getOrderSn());
        }
        if (!orderItemService.saveBatch(orderItemEntityList)) {
            throw new BaseRequestException("下单失败");
        }
        //删除购物车中的下单商品
        if (!deleteCartItemList(cartItemVoList, memberId)) {
            throw new BaseRequestException("下单失败");
        }
        return true;
    }

    @Override
    public Boolean confirmReceiveOrder(Long orderId) {
        Long memberId = SecurityUtils.getCurrentUserId();
        // 订单
        OrderEntity orderEntity = getById(orderId);

        if (Objects.isNull(orderEntity) || !memberId.equals(orderEntity.getMemberId())) {
            throw new BaseRequestException("不能确认他人订单！");
        }
        if (orderEntity.getStatus() != 2) {
            throw new BaseRequestException("该订单还未发货！");
        }
        orderEntity.setStatus(3);
        orderEntity.setConfirmStatus(true);
        orderEntity.setReceiveTime(new Date());
        return updateById(orderEntity);
    }

    @Override
    public Boolean deleteOrder(Long orderId) {
        Long memberId = SecurityUtils.getCurrentUserId();
        OrderEntity orderEntity = getById(orderId);
        if (Objects.isNull(orderEntity) || !memberId.equals(orderEntity.getMemberId())) {
            return false;
        }
        if (orderEntity.getStatus() == 3 || orderEntity.getStatus() == 4) {
            return removeById(orderEntity);
        } else {
            throw new BaseRequestException("只能删除已完成或已关闭的订单！");
        }
    }

    @Override
    public Boolean cancelOrder(Long orderId) {
        // 查询未付款的取消订单
        Long memberId = SecurityUtils.getCurrentUserId();
        OrderEntity orderEntity = getById(orderId);
        if (Objects.isNull(orderEntity) || !memberId.equals(orderEntity.getMemberId())) {
            return false;
        }
        if (orderEntity.getStatus() != 0) {
            throw new BaseRequestException("只能取消未付款的订单！");
        }
        //修改订单状态为取消
        orderEntity.setStatus(4);
        if (!updateById(orderEntity)) {
            return false;
        }
        List<OrderItemVo> orderItemVoList = orderItemService.getByOrderIds(Set.of(orderEntity.getId()));
        if (CollectionUtils.isNotEmpty(orderItemVoList)) {
            return skuStockService.releaseSkuStockLock(orderItemVoList);
        }
        return false;

    }

    @Override
    public Boolean paySuccess(Long orderId, Integer payType) {
        // 修改订单支付状态
        OrderEntity order = getById(orderId);
        if (Objects.isNull(order)) {
            return false;
        }
        if (order.getStatus() != 0) {
            throw new BaseRequestException("只能支付未付款订单");
        }

        order.setStatus(1);
        order.setPaymentTime(new Date());
        order.setPayType(payType);
        // TODO 恢复所有下单商品的锁定库存，扣减真实库存
        return updateById(order);
    }

    @Override
    public OrderVo getByOrderIdAndMemberId(Serializable id, Long memberId) {
        if (Objects.isNull(id) || Objects.isNull(memberId)) {
            throw new BaseRequestException("参数错误");
        }
        OrderEntity orderEntity = getById(id);
        if (Objects.isNull(orderEntity)) {
            return null;
        }
        if (!orderEntity.getMemberId().equals(memberId)) {
            throw new BaseRequestException("获取订单失败");
        }

        OrderVo orderVo = BeanCopy.convert(orderEntity, OrderVo.class);

        // 订单中所包含的商品
        List<OrderItemVo> orderItemVo = orderItemService.getByOrderIds(Set.of(orderEntity.getId()));
        // 将订单中所包含的商品 商品的id为key 以map 的形式进行存储
        Map<Long, List<OrderItemVo>> longOrderItemVoMap = longOrderItemVoMap(orderItemVo);
        if (longOrderItemVoMap.containsKey(orderVo.getId())) {
            // 将订单中所包含的商品 存储到 orderVo里面
            orderVo.setOrderItemList(longOrderItemVoMap.get(orderVo.getId()));
        }
        return orderVo;
    }

    @Override
    public OrderVo getCompletedOrderByOrderIdAndMemberId(Long orderId, Long memberId) {
        if (Objects.isNull(orderId) || Objects.isNull(memberId)) {
            throw new BaseRequestException("参数错误");
        }
        OrderEntity orderEntity = getById(orderId);
        if (Objects.isNull(orderEntity)) {
            return null;
        }
        if (orderEntity.getStatus() != 3) {
            throw new BaseRequestException("订单未完成");
        }
        if (!orderEntity.getMemberId().equals(memberId)) {
            throw new BaseRequestException("获取订单失败");
        }
        OrderVo orderVo = BeanCopy.convert(orderEntity, OrderVo.class);

        // 订单中所包含的商品
        List<OrderItemVo> orderItemVo = orderItemService.getByOrderIds(Set.of(orderEntity.getId()));
        // 将订单中所包含的商品 商品的id为key 以map 的形式进行存储
        Map<Long, List<OrderItemVo>> longOrderItemVoMap = longOrderItemVoMap(orderItemVo);
        if (longOrderItemVoMap.containsKey(orderVo.getId())) {
            // 将订单中所包含的商品 存储到 orderVo里面
            orderVo.setOrderItemList(longOrderItemVoMap.get(orderVo.getId()));
        }
        return orderVo;
    }

    /**
     * 删除下单商品的购物车信息
     */
    private Boolean deleteCartItemList(@NotNull List<CartItemVo> cartItemVoList, Long memberId) {
        // 获取购物车id 列表
        Set<Long> cartItemIds = cartItemVoList.stream().map(CartItemVo::getId).collect(Collectors.toSet());

        return cartItemService.removeBatchByIdsAndMemberId(cartItemIds, memberId);
    }

    /**
     * 锁定下单商品的所有库存
     */
    private void lockStock(@NotNull List<CartItemVo> cartItemVoList) {
        for (CartItemVo cartItemVo : cartItemVoList) {
            SkuStockEntity skuStockEntity = skuStockService.getById(cartItemVo.getProductSkuId());
            skuStockEntity.setLockStock(skuStockEntity.getLockStock() + cartItemVo.getQuantity());
            skuStockService.updateById(skuStockEntity);
        }
    }

    /**
     * 判断下单商品是否都有库存
     */
    @NotNull
    private Boolean hasInventory(@NotNull List<CartItemVo> cartItemVoList) {
        for (CartItemVo cartItemVo : cartItemVoList) {
            //判断真实库存是否为空  判断真实库存是否小于0  判断真实库存是否小于下单的数量
            if (cartItemVo.getRealStock() == null || cartItemVo.getRealStock() <= 0 || cartItemVo.getRealStock() < cartItemVo.getQuantity()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算购物车金额 + 运费
     *
     * @param cartItemVoList 购物车列表
     * @return CalculateAmountVo
     */
    @NotNull
    private CalculateAmountVo calcTotalAmount(List<CartItemVo> cartItemVoList) {
        // 创建 运费
        BigDecimal freightAmount = new BigDecimal(BigInteger.ZERO);
        // 计算订单
        CalculateAmountVo calculateAmountVo = calculateAmount(cartItemVoList);
        // TODO 设置运费
        calculateAmountVo.setFreightAmount(freightAmount);
        // 获取订单总金额
        BigDecimal totalAmount = calculateAmountVo.getTotalAmount();
        // 总金额加上运费 = 实际付款金额
        calculateAmountVo.setPayAmount(totalAmount.add(freightAmount));
        return calculateAmountVo;

    }

    /**
     * 计算购物车金额
     *
     * @param cartItemVoList 购物车列表
     * @return CalculateAmountVo
     */
    @NotNull
    private CalculateAmountVo calculateAmount(List<CartItemVo> cartItemVoList) {
        // 创建订单总金额
        BigDecimal totalAmount = new BigDecimal(BigInteger.ZERO);
        // 计算金额
        CalculateAmountVo calculateAmountVo = new CalculateAmountVo();
        // 判断购物车是否为空
        if (CollectionUtils.isNotEmpty(cartItemVoList)) {
            // 获取购物车的商品金额列表
            for (CartItemVo cartItemVo : cartItemVoList) {
                // 购物车里的下单数量
                BigDecimal price = cartItemVo.getPrice();
                // 购买商品数量数量
                Integer quantity = cartItemVo.getQuantity();
                if (Objects.nonNull(price) && quantity >= 1) {
                    // 商品单价 乘以商品数量
                    BigDecimal totalPrice = price.multiply(new BigDecimal(quantity));
                    // 累加到 订单总金额里面
                    totalAmount = totalAmount.add(totalPrice);
                } else {
                    throw new BaseRequestException("商品信息有误");
                }
            }
            calculateAmountVo.setTotalAmount(totalAmount);
        } else {
            calculateAmountVo.setTotalAmount(totalAmount);
        }
        return calculateAmountVo;
    }
}

