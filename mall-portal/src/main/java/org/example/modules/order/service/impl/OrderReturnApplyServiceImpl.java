package org.example.modules.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.example.common.core.entity.MemberEntity;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.server.MinioServer;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.order.entity.OrderReturnApplyEntity;
import org.example.modules.order.entity.dto.OrderReturnApplyDto;
import org.example.modules.order.entity.vo.OrderReturnApplyVo;
import org.example.modules.order.entity.vo.OrderReturnReasonVo;
import org.example.modules.order.entity.vo.OrderVo;
import org.example.modules.order.mapper.OrderReturnApplyMapper;
import org.example.modules.order.service.OrderReturnApplyService;
import org.example.modules.order.service.OrderReturnReasonService;
import org.example.modules.order.service.OrderService;
import org.example.security.entity.JwtMember;
import org.example.security.utils.SecurityUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

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
    @Resource
    private OrderReturnReasonService orderReturnReasonService;
    @Resource
    private MinioServer minioServer;

    @Override
    public Boolean save(@NotNull OrderReturnApplyDto orderReturnApplyDto, HttpServletRequest request) {

        Set<String> proofPics = orderReturnApplyDto.getProofPics();
        orderReturnApplyDto.setProofPics(minioServer.checkObjectIsExist(proofPics));

        OrderReturnApplyEntity orderReturnApplyEntity = new OrderReturnApplyEntity();
        // 获取 退货者信息
        JwtMember jwtMember = (JwtMember) SecurityUtils.getCurrentUser();
        MemberEntity memberEntity = jwtMember.getUser();
        // 获取退货订单
        Long orderId = orderReturnApplyDto.getOrderId();
        // 获取订单
        OrderVo orderVo = orderService.getByOrderIdAndMemberId(orderId, memberEntity.getId());
        // 校验订单不能为空
        if (Objects.isNull(orderVo)) {
            throw new BaseRequestException("退单失败");
        }
        // 只能退已完成的订单
        if (orderVo.getStatus() != 3) {
            throw new BaseRequestException("只能退已完成的订单");
        }
        OrderReturnReasonVo orderReturnReasonVo = orderReturnReasonService.getByOrderReturnApplyId(orderReturnApplyDto.getReasonId());
        if (Objects.isNull(orderReturnReasonVo)) {
            throw new BaseRequestException("请填写退货原因");
        }
        // 退货订单id
        orderReturnApplyEntity.setOrderId(orderVo.getId());
        // 会员id
        orderReturnApplyEntity.setMemberId(memberEntity.getId());
        // 订单编号
        orderReturnApplyEntity.setOrderSn(orderVo.getOrderSn());
        // 申请时间
        orderReturnApplyEntity.setCreateTime(new Date());
        // 会员昵称
        orderReturnApplyEntity.setMemberNickname(memberEntity.getNickname());
        // 退货人（会员）姓名  等申请退货通过了在填写信息
        orderReturnApplyEntity.setReturnName(null);
        // 退货人（会员）电话  等申请退货通过了在填写信息
        orderReturnApplyEntity.setReturnPhone(null);
        // 公司给客户的退款金额
        orderReturnApplyEntity.setReturnAmount(null);
        // 申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝
        orderReturnApplyEntity.setStatus(0);
        // 原因
        orderReturnApplyEntity.setReason(orderReturnReasonVo.getName());
        // 退货原因id
        orderReturnApplyEntity.setReasonId(orderReturnReasonVo.getId());
        // 用户退货问题描述
        orderReturnApplyEntity.setDescription(orderReturnApplyDto.getDescription());

        return save(orderReturnApplyEntity);
    }

    @Override
    public Boolean updateById(OrderReturnApplyDto orderReturnApplyDto) {
        OrderReturnApplyEntity orderReturnApplyEntity = BeanCopy.convert(orderReturnApplyDto, OrderReturnApplyEntity.class);
        return updateById(orderReturnApplyEntity);
    }

    @Override
    public Page<OrderReturnApplyVo> page(Page<OrderReturnApplyEntity> page, OrderReturnApplyDto orderReturnApplyDto) {
        orderReturnApplyDto.setMemberId(SecurityUtils.getCurrentUserId());
        OrderReturnApplyEntity orderReturnApplyEntity = BeanCopy.convert(orderReturnApplyDto, OrderReturnApplyEntity.class);
        LambdaQueryWrapper<OrderReturnApplyEntity> orderReturnApplyEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(orderReturnApplyEntity);
        Page<OrderReturnApplyEntity> orderReturnApplyEntityPage = page(page, orderReturnApplyEntityLambdaQueryWrapper);
        IPage<OrderReturnApplyVo> orderReturnApplyEntityPageVoIPage = orderReturnApplyEntityPage.convert(orderReturnApply -> BeanCopy.convert(orderReturnApply, OrderReturnApplyVo.class));
        return (Page<OrderReturnApplyVo>) orderReturnApplyEntityPageVoIPage;
    }
}

