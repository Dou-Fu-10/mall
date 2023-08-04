package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
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
import org.example.modules.order.entity.vo.CalculateAmountVo;
import org.example.modules.order.entity.vo.ConfirmOrderVo;
import org.example.modules.order.entity.vo.OrderSettingVo;
import org.example.modules.order.entity.vo.OrderVo;
import org.example.modules.order.mapper.OrderMapper;
import org.example.modules.order.service.OrderItemService;
import org.example.modules.order.service.OrderService;
import org.example.modules.order.service.OrderSettingService;
import org.example.modules.product.entity.SkuStockEntity;
import org.example.modules.product.serveice.ProductService;
import org.example.modules.product.service.SkuStockService;
import org.example.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    private ProductService productService;
    @Resource
    private SkuStockService skuStockService;
    @Resource
    private OrderItemService orderItemService;

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
        LambdaQueryWrapper<OrderEntity> orderEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(orderEntity);
        Page<OrderEntity> orderEntityPage = page(page, orderEntityLambdaQueryWrapper);
        IPage<OrderVo> orderEntityPageVoIpage = orderEntityPage.convert(order -> BeanCopy.convert(order, OrderVo.class));
        return (Page) orderEntityPageVoIpage;
    }

    @Override
    public ConfirmOrderVo generateConfirmOrder(@NotNull List<Long> cartIds) {
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
    public Map<String, Object> generateOrder(GenerateOrderDto generateOrderDto) {
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
        //进行库存锁定
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
        // 支付方式：0->未支付；1->支付宝；2->微信
        orderEntity.setPayType(generateOrderDto.getPayType());
        // 订单来源：0->PC订单；1->app订单
        orderEntity.setSourceType(1);
        // 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
        orderEntity.setStatus(0);
        // 订单类型：0->正常订单；1->秒杀订单
        orderEntity.setOrderType(0);
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
        // 确认收货状态：0->已确认：1->未确认
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
        Map<String, Object> result = new HashMap<>();
        result.put("order", orderEntity);
        result.put("orderItemList", orderItemEntityList);
        return result;
    }

    @org.jetbrains.annotations.NotNull
    private static List<OrderItemEntity> getOrderItemEntities(List<CartItemVo> cartItemVoList) {
        List<OrderItemEntity> orderItemEntityList = new ArrayList<>();
        for (CartItemVo cartItemVo : cartItemVoList) {
            // 生成下单商品信息
            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setProductId(cartItemVo.getProductId());
            orderItem.setProductName(cartItemVo.getProductName());
            orderItem.setProductPic(cartItemVo.getProductPic());
            orderItem.setProductAttr(cartItemVo.getProductAttr());
            orderItem.setProductSn(cartItemVo.getProductSn());
            orderItem.setProductPrice(cartItemVo.getPrice());
            orderItem.setProductQuantity(cartItemVo.getQuantity());
            orderItem.setProductSkuId(cartItemVo.getProductSkuId());
            orderItem.setProductSkuCode(cartItemVo.getProductSkuCode());
            orderItem.setProductCategoryId(cartItemVo.getProductCategoryId());
            orderItemEntityList.add(orderItem);
        }
        return orderItemEntityList;
    }

    /**
     * 删除下单商品的购物车信息
     */
    private Boolean deleteCartItemList(@NotNull List<CartItemVo> cartItemVoList, @NotNull Long memberId) {
        Set<Long> cartItemIds = cartItemVoList.stream().map(CartItemVo::getId).collect(Collectors.toSet());
        return cartItemService.removeBatchByIdsAndMemberId(cartItemIds, memberId);
    }

    /**
     * 锁定下单商品的所有库存
     */
    private void lockStock(List<CartItemVo> cartItemVoList) {
        for (CartItemVo cartItemVo : cartItemVoList) {
            SkuStockEntity skuStockEntity = skuStockService.getById(cartItemVo.getProductSkuId());
            skuStockEntity.setLockStock(skuStockEntity.getLockStock() + cartItemVo.getQuantity());
            skuStockService.updateById(skuStockEntity);
        }
    }

    /**
     * 判断下单商品是否都有库存
     */
    private Boolean hasInventory(List<CartItemVo> cartItemVoList) {
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
    private CalculateAmountVo calcTotalAmount(List<CartItemVo> cartItemVoList) {
        // 创建 运费
        BigDecimal freightAmount = new BigDecimal(BigInteger.ZERO);
        // 计算订单
        CalculateAmountVo calculateAmountVo = calculateAmount(cartItemVoList);
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
    private CalculateAmountVo calculateAmount(List<CartItemVo> cartItemVoList) {
        // 计算金额
        CalculateAmountVo calculateAmountVo = new CalculateAmountVo();
        // 判断购物车是否为空
        if (CollectionUtils.isNotEmpty(cartItemVoList)) {
            // 创建订单总金额
            BigDecimal totalAmount = new BigDecimal(BigInteger.ZERO);
            // 获取购物车的商品金额列表
            List<BigDecimal> poriceList = cartItemVoList.stream().map(CartItemVo::getPrice).toList();
            for (BigDecimal decimal : poriceList) {
                if (Objects.nonNull(decimal)) {
                    // 累加到 订单总金额里面
                    totalAmount = totalAmount.add(decimal);
                }
            }
            calculateAmountVo.setTotalAmount(totalAmount);
        } else {
            // 创建订单总金额
            BigDecimal totalAmount = new BigDecimal(BigInteger.ZERO);
            calculateAmountVo.setTotalAmount(totalAmount);
        }
        return calculateAmountVo;
    }
}

