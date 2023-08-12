package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.order.entity.OrderEntity;
import org.example.modules.order.entity.OrderReturnApplyEntity;
import org.example.modules.order.entity.dto.OrderReturnApplyDto;
import org.example.modules.order.entity.vo.OrderItemVo;
import org.example.modules.order.entity.vo.OrderReturnApplyVo;
import org.example.modules.order.mapper.OrderReturnApplyMapper;
import org.example.modules.order.service.OrderItemService;
import org.example.modules.order.service.OrderReturnApplyService;
import org.example.modules.order.service.OrderService;
import org.example.modules.tools.entity.CompanyAddressEntity;
import org.example.modules.tools.entity.vo.CompanyAddressVo;
import org.example.modules.tools.service.CompanyAddressService;
import org.example.security.entity.JwtAdmin;
import org.example.security.utils.SecurityUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:30
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:30
 * @Description 订单退货申请(OrderReturnApply)表服务实现类
 */
@Service("orderReturnApplyService")
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyMapper, OrderReturnApplyEntity> implements OrderReturnApplyService {
    @Resource
    private CompanyAddressService companyAddressService;
    @Resource
    private OrderItemService orderItemService;
    @Resource
    private OrderService orderService;

    @Override
    public Boolean save(OrderReturnApplyDto orderReturnApply) {
        OrderReturnApplyEntity orderReturnApplyEntity = BeanCopy.convert(orderReturnApply, OrderReturnApplyEntity.class);
        return orderReturnApplyEntity.insert();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Boolean updateById(@NotNull OrderReturnApplyDto orderReturnApplyDto) {
        // 获取退货申请id
        Long id = orderReturnApplyDto.getId();
        // 获取退货状态  只能拒绝或者同意
        // 申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝
        Integer status = orderReturnApplyDto.getStatus();
        if (status.equals(1) || status.equals(3)) {
            throw new BaseRequestException("只能拒绝或者同意");
        }
        // 获取退货申请
        OrderReturnApplyEntity orderReturnApply = getById(id);
        if (Objects.isNull(orderReturnApply)) {
            return false;
        }

        Long orderId = orderReturnApply.getOrderId();
        // 获取退货订单
        OrderEntity order = orderService.getById(orderId);
        // 只能退已完成与已收货的订单
        if (order.getStatus() != 3 && !order.getConfirmStatus()) {
            throw new BaseRequestException("只能退已完成与已收货的订单");
        }
        // 获取公司退货地址
        Long companyAddressId = orderReturnApplyDto.getCompanyAddressId();
        // 获取公司退货地址
        CompanyAddressEntity companyAddressEntity = companyAddressService.getById(companyAddressId);
        if (Objects.isNull(companyAddressEntity)) {
            throw new BaseRequestException("请输入正确的退货地址");
        }
        // 获取当前操作人员信息
        JwtAdmin jwtAdmin = (JwtAdmin) SecurityUtils.getCurrentUser();
        // 获取操作人员昵称
        String nickName = jwtAdmin.getUser().getNickName();
        // 操作人员
        orderReturnApplyDto.setHandleMan(nickName);
        // 操作时间
        orderReturnApplyDto.setHandleTime(new Date());
        OrderReturnApplyEntity orderReturnApplyEntity = BeanCopy.convert(orderReturnApplyDto, OrderReturnApplyEntity.class);
        // 更新状态
        if (orderReturnApplyEntity.updateById()) {
            // 判断是否是同意退货
            if (status.equals(1)) {
                // 如果同意退货就将订单作废
                // 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
                order.setStatus(5);
                // 修改订单
                if (order.updateById()) {
                    return true;
                } else {
                    throw new BaseRequestException("退货失败");
                }

            }
            return true;
        }
        return false;
    }

    @Override
    public Page<OrderReturnApplyVo> page(Page<OrderReturnApplyEntity> page, OrderReturnApplyDto orderReturnApply) {
        OrderReturnApplyEntity orderReturnApplyEntity = BeanCopy.convert(orderReturnApply, OrderReturnApplyEntity.class);
        LambdaQueryWrapper<OrderReturnApplyEntity> orderEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(orderReturnApplyEntity);
        // 以创建时间进行排序
        orderEntityLambdaQueryWrapper.orderByAsc(OrderReturnApplyEntity::getCreateTime);
        Page<OrderReturnApplyEntity> orderReturnApplyEntityPage = page(page, orderEntityLambdaQueryWrapper);
        IPage<OrderReturnApplyVo> orderReturnApplyVoIPage = orderReturnApplyEntityPage.convert(order -> BeanCopy.convert(order, OrderReturnApplyVo.class));
        return (Page<OrderReturnApplyVo>) orderReturnApplyVoIPage;
    }

    @Override
    public OrderReturnApplyVo getByOrderReturnApplyId(Serializable id) {
        if (Objects.isNull(id)) {
            throw new BaseRequestException("参数错误");
        }
        // 获取退货申请
        OrderReturnApplyEntity orderReturnApplyEntity = getById(id);
        if (Objects.isNull(orderReturnApplyEntity)) {
            throw new BaseRequestException("未获取到相应的订单退货");
        }
        OrderReturnApplyVo orderReturnApplyVo = BeanCopy.convert(orderReturnApplyEntity, OrderReturnApplyVo.class);
        // 获取 公司收货地址id
        Long companyAddressId = orderReturnApplyVo.getCompanyAddressId();
        // 公司收货地址
        if (Objects.nonNull(companyAddressId)) {
            CompanyAddressVo companyAddressVo = companyAddressService.getByCompanyAddressIdId(companyAddressId);
            // 退货地址
            orderReturnApplyVo.setCompanyAddress(companyAddressVo);
        }
        Long orderId = orderReturnApplyVo.getOrderId();
        List<OrderItemVo> orderItemByOrderId = orderItemService.getOrderItemByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderItemByOrderId)) {
            throw new BaseRequestException("获取订单失败");
        }
        orderReturnApplyVo.setOrderItem(orderItemByOrderId);
        return orderReturnApplyVo;

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
}

