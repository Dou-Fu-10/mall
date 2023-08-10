package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.entity.MemberEntity;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.order.entity.OrderReturnApplyEntity;
import org.example.modules.order.entity.dto.OrderReturnApplyDto;
import org.example.modules.order.entity.vo.OrderItemVo;
import org.example.modules.order.entity.vo.OrderReturnApplyVo;
import org.example.modules.order.entity.vo.OrderVo;
import org.example.modules.order.mapper.OrderReturnApplyMapper;
import org.example.modules.order.service.OrderReturnApplyService;
import org.example.modules.order.service.OrderService;
import org.example.security.entity.JwtMember;
import org.example.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Dou-Fu-10 2023-08-05 17:04:10
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 17:04:10
 * @Description 订单退货申请(OrderReturnApply)表服务实现类
 */
@Service("orderReturnApplyService")
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyMapper, OrderReturnApplyEntity> implements OrderReturnApplyService {
    @Resource
    private OrderService orderService;

    @Override
    public Boolean save(OrderReturnApplyDto orderReturnApplyDto) {
        OrderReturnApplyEntity orderReturnApplyEntity = new OrderReturnApplyEntity();
        JwtMember jwtMember = (JwtMember) SecurityUtils.getCurrentUser();
        MemberEntity memberEntity = jwtMember.getUser();
        Long orderId = orderReturnApplyDto.getOrderId();
        // 获取订单
        OrderVo orderVo = orderService.getByOrderIdAndMemberId(orderId, memberEntity.getId());

        if (Objects.isNull(orderVo)) {
            throw new BaseRequestException("退单失败");
        }
        List<OrderItemVo> orderItemList = orderVo.getOrderItemList();

        orderReturnApplyEntity.setOrderId(orderId);
        orderReturnApplyEntity.setProductId(orderItemList.get(0).getProductId());
        // 订单编号
        orderReturnApplyEntity.setOrderSn(orderVo.getOrderSn());
        // 申请时间
        orderReturnApplyEntity.setCreateTime(new Date());
        // 会员昵称
        orderReturnApplyEntity.setMemberNickname(memberEntity.getNickname());
        // 退货人姓名
        orderReturnApplyEntity.setReturnName("张伟");
        // 退货人电话
        orderReturnApplyEntity.setReturnPhone("911");
        // 退款金额
        orderReturnApplyEntity.setReturnAmount(orderVo.getPayAmount());
        // 申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝
        orderReturnApplyEntity.setStatus(0);
        // 商品图片
//        orderReturnApplyEntity.setProductPic(orderVo.ge);
        // 商品名称
//        orderReturnApplyEntity.setProductName(o);
        // 商品销售属性：颜色：红色；尺码：xl
//        orderReturnApplyEntity.setProductAttr(o);
        // 退货数量
//        orderReturnApplyEntity.setProductCount();
        // 商品单价
//        orderReturnApplyEntity.setProductPrice();
        // 商品实际支付单价
        orderReturnApplyEntity.setProductPrice(orderVo.getPayAmount());
        // 原因
        orderReturnApplyEntity.setReason(orderReturnApplyDto.getReason());
        // 描述
        orderReturnApplyEntity.setDescription(orderReturnApplyDto.getDescription());
        // 收货备注
        orderReturnApplyEntity.setHandleNote(orderReturnApplyDto.getHandleNote());

        return save(orderReturnApplyEntity);
    }

    @Override
    public Boolean updateById(OrderReturnApplyDto orderReturnApplyDto) {
        OrderReturnApplyEntity orderReturnApplyEntity = BeanCopy.convert(orderReturnApplyDto, OrderReturnApplyEntity.class);
        return updateById(orderReturnApplyEntity);
    }

    @Override
    public Page<OrderReturnApplyVo> page(Page<OrderReturnApplyEntity> page, OrderReturnApplyDto orderReturnApplyDto) {
        OrderReturnApplyEntity orderReturnApplyEntity = BeanCopy.convert(orderReturnApplyDto, OrderReturnApplyEntity.class);
        LambdaQueryWrapper<OrderReturnApplyEntity> orderReturnApplyEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(orderReturnApplyEntity);
        Page<OrderReturnApplyEntity> orderReturnApplyEntityPage = page(page, orderReturnApplyEntityLambdaQueryWrapper);
        IPage<OrderReturnApplyVo> orderReturnApplyEntityPageVoIpage = orderReturnApplyEntityPage.convert(orderReturnApply -> BeanCopy.convert(orderReturnApply, OrderReturnApplyVo.class));
        return (Page) orderReturnApplyEntityPageVoIpage;
    }
}

