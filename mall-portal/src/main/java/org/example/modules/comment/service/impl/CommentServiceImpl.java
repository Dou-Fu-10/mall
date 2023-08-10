package org.example.modules.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.entity.MemberEntity;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.comment.entity.vo.CommentReplayVo;
import org.example.modules.comment.service.CommentReplayService;
import org.example.modules.order.entity.OrderItemEntity;
import org.example.modules.order.entity.vo.OrderItemVo;
import org.example.modules.order.entity.vo.OrderVo;
import org.example.modules.order.service.OrderItemService;
import org.example.modules.order.service.OrderService;
import org.example.modules.product.entity.vo.ProductVo;
import org.example.modules.product.serveice.ProductService;
import org.example.security.entity.JwtMember;
import org.example.security.utils.SecurityUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.comment.entity.CommentEntity;
import org.example.modules.comment.entity.dto.CommentDto;
import org.example.modules.comment.entity.vo.CommentVo;
import org.example.modules.comment.mapper.CommentMapper;
import org.example.modules.comment.service.CommentService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-08-10 15:24:17
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 15:24:17
 * @Description 商品评价表(Comment)表服务实现类
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentEntity> implements CommentService {
    @Resource
    private ProductService productService;
    @Resource
    private OrderService orderService;
    @Resource
    @Lazy
    private CommentReplayService commentReplayService;

    @Override
    public Boolean save(CommentDto commentDto) {
        CommentEntity commentEntity = BeanCopy.convert(commentDto, CommentEntity.class);
        Long productId = commentEntity.getProductId();
        ProductVo productVo = productService.getByProductId(productId);
        if (Objects.isNull(productVo)) {
            throw new BaseRequestException("商品不存在或已下架");
        }


        JwtMember jwtMember = (JwtMember) SecurityUtils.getCurrentUser();
        MemberEntity memberEntity = jwtMember.getUser();
        // 会员昵称
        commentEntity.setMemberNickName(memberEntity.getNickname());
        // 会员id
        commentEntity.setMemberId(memberEntity.getId());
        // 商品名称
        commentEntity.setProductName(productVo.getName());
        // 评价的ip
        commentEntity.setMemberIp("127.0.0.1");
        // 显示状态  0->不显示：1->显示
        commentEntity.setShowStatus(true);
        // 评论用户头像
        commentEntity.setMemberIcon(memberEntity.getIcon());
        // 获取订单id
        Long orderId = commentDto.getOrderId();
        // 获取订单所包含的商品id
        Long orderItemId = commentDto.getOrderItemId();
        // 校验两者都不为空
        if (Objects.nonNull(orderId) && Objects.nonNull(orderItemId)) {
            // 获取 订单 和 获取订单所包含的商品
            OrderVo orderVo = orderService.getCompletedOrderByOrderIdAndMemberId(orderId, memberEntity.getId());
            // 获取订单所包含的商品
            List<OrderItemVo> orderItemList = orderVo.getOrderItemList();
            for (OrderItemVo orderItemVo : orderItemList) {
                if (orderItemId.equals(orderItemVo.getId())) {
                    commentEntity.setProductAttribute(orderItemVo.getProductAttr());
                }
            }
            if (Objects.isNull(commentEntity.getProductAttribute())) {
                throw new BaseRequestException("评论失败");
            }

        }

        return save(commentEntity);
    }

    @Override
    public CommentVo getCommentById(Long commentId) {
        CommentEntity commentEntity = lambdaQuery().eq(CommentEntity::getShowStatus, true).eq(CommentEntity::getId, commentId).one();
        return BeanCopy.convert(commentEntity, CommentVo.class);
    }

    @Override
    public Boolean updateById(CommentDto commentDto) {
        CommentEntity commentEntity = BeanCopy.convert(commentDto, CommentEntity.class);
        return updateById(commentEntity);
    }

    @Override
    public Page<CommentVo> page(Page<CommentEntity> page, CommentDto commentDto) {
        CommentEntity commentEntity = BeanCopy.convert(commentDto, CommentEntity.class);
        LambdaQueryWrapper<CommentEntity> commentEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(commentEntity);
        Page<CommentEntity> commentEntityPage = page(page, commentEntityLambdaQueryWrapper);
        IPage<CommentVo> commentEntityPageVoIpage = commentEntityPage.convert(comment -> BeanCopy.convert(comment, CommentVo.class));
        // 获取评价列表
        List<CommentVo> commentVoList = commentEntityPageVoIpage.getRecords();
        if (CollectionUtils.isEmpty(commentVoList)) {
            return (Page<CommentVo>) commentEntityPageVoIpage;
        }
        Set<Long> commentVoIds = commentVoList.stream().map(CommentVo::getId).collect(Collectors.toSet());
        List<CommentReplayVo> commentReplayVos = commentReplayService.getByCommentIds(commentVoIds);
        Map<Long, List<CommentReplayVo>> longListMap = longListMap(commentReplayVos);
        commentVoList.forEach(commentVo -> commentVo.setCommentReplayList(longListMap.get(commentVo.getId())));
        return (Page<CommentVo>) commentEntityPageVoIpage;
    }

    @NotNull
    private Map<Long, List<CommentReplayVo>> longListMap(@NotNull List<CommentReplayVo> commentReplayVoList) {
        Map<Long, List<CommentReplayVo>> longListHashMap = new HashMap<>();
        for (CommentReplayVo commentReplayVo : commentReplayVoList) {
            Long commentId = commentReplayVo.getCommentId();
            // 使用computeIfAbsent方法来避免重复的get和put操作
            longListHashMap.computeIfAbsent(commentId, k -> new ArrayList<>()).add(commentReplayVo);
        }
        return longListHashMap;
    }
}

