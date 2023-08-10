package org.example.modules.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.entity.MemberEntity;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.comment.entity.CommentEntity;
import org.example.modules.comment.entity.vo.CommentVo;
import org.example.modules.comment.service.CommentService;
import org.example.security.entity.JwtMember;
import org.example.security.utils.SecurityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.comment.entity.CommentReplayEntity;
import org.example.modules.comment.entity.dto.CommentReplayDto;
import org.example.modules.comment.entity.vo.CommentReplayVo;
import org.example.modules.comment.mapper.CommentReplayMapper;
import org.example.modules.comment.service.CommentReplayService;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-08-10 15:24:17
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 15:24:17
 * @Description 产品评价回复表(CommentReplay)表服务实现类
 */
@Service("commentReplayService")
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayMapper, CommentReplayEntity> implements CommentReplayService {
    @Resource
    private CommentService commentService;

    @Override
    public Boolean save(CommentReplayDto commentReplayDto) {
        CommentReplayEntity commentReplayEntity = BeanCopy.convert(commentReplayDto, CommentReplayEntity.class);
        // 获取评论id
        Long commentId = commentReplayEntity.getCommentId();
        // 获取评论
        CommentVo commentVo = commentService.getCommentById(commentId);
        if (Objects.isNull(commentVo)) {
            throw new BaseRequestException("不能追评不存在或者已删除的评论");
        }
        JwtMember jwtMember = (JwtMember) SecurityUtils.getCurrentUser();
        MemberEntity memberEntity = jwtMember.getUser();
        // 会员昵称
        commentReplayEntity.setMemberNickName(memberEntity.getNickname());
        // 会员头像
        commentReplayEntity.setMemberIcon(memberEntity.getIcon());
        // 评论人员类型；0->管理员；1->会员
        commentReplayEntity.setIsMember(true);

        return save(commentReplayEntity);
    }

    @Override
    public Boolean updateById(CommentReplayDto commentReplayDto) {
        CommentReplayEntity commentReplayEntity = BeanCopy.convert(commentReplayDto, CommentReplayEntity.class);
        return updateById(commentReplayEntity);
    }

    @Override
    public Page<CommentReplayVo> page(Page<CommentReplayEntity> page, CommentReplayDto commentReplayDto) {
        CommentReplayEntity commentReplayEntity = BeanCopy.convert(commentReplayDto, CommentReplayEntity.class);
        LambdaQueryWrapper<CommentReplayEntity> commentReplayEntityLambdaQueryWrapper = new LambdaQueryWrapper<>(commentReplayEntity);
        Page<CommentReplayEntity> commentReplayEntityPage = page(page, commentReplayEntityLambdaQueryWrapper);
        IPage<CommentReplayVo> commentReplayEntityPageVoIpage = commentReplayEntityPage.convert(commentReplay -> BeanCopy.convert(commentReplay, CommentReplayVo.class));
        return (Page<CommentReplayVo>) commentReplayEntityPageVoIpage;
    }

    @Override
    public List<CommentReplayVo> getByCommentIds(Set<Long> commentIds) {

        List<CommentReplayEntity> commentReplayEntities = lambdaQuery().in(CommentReplayEntity::getCommentId, commentIds).list();
        return BeanCopy.copytList(commentReplayEntities, CommentReplayVo.class);
    }
}

