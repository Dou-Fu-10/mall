package org.example.modules.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.comment.entity.CommentEntity;
import org.example.modules.comment.entity.CommentReplayEntity;
import org.example.modules.comment.entity.dto.CommentReplayDto;
import org.example.modules.comment.entity.vo.CommentReplayVo;
import org.example.modules.comment.mapper.CommentReplayMapper;
import org.example.modules.comment.service.CommentReplayService;
import org.example.modules.comment.service.CommentService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Dou-Fu-10 2023-07-13 21:35:11
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 21:35:11
 * @Description 产品评价回复表(CommentReplay)表服务实现类
 */
@Service("commentReplayService")
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayMapper, CommentReplayEntity> implements CommentReplayService {

    @Resource
    private CommentService service;

    @Override
    public Page<CommentReplayVo> page(Page<CommentReplayEntity> page, CommentReplayDto commentReplayDto) {
        CommentReplayEntity commentReplayEntity = BeanCopy.convert(commentReplayDto, CommentReplayEntity.class);
        Page<CommentReplayEntity> commentReplayEntityPage = page(page, new QueryWrapper<>(commentReplayEntity));
        IPage<CommentReplayVo> commentReplayVoIPage = commentReplayEntityPage.convert(commentReplay -> BeanCopy.convert(commentReplay, CommentReplayVo.class));
        return (Page<CommentReplayVo>) commentReplayVoIPage;
    }

    @Override
    public CommentReplayVo getByCommentReplayId(Serializable id) {
        CommentReplayEntity commentReplayEntity = getById(id);
        return BeanCopy.convert(commentReplayEntity, CommentReplayVo.class);
    }

    @Override
    public Boolean save(@NotNull CommentReplayDto commentReplayDto) {
        CommentEntity commentEntity = service.getById(commentReplayDto.getCommentId());
        if (Objects.isNull(commentEntity)) {
            throw new BaseRequestException("请正确的填写要回复的信息");
        }
        commentReplayDto.setIsMember(false);
        CommentReplayEntity commentReplayEntity = BeanCopy.convert(commentReplayDto, CommentReplayEntity.class);
        return commentReplayEntity.insert();
    }

    @Override
    public Boolean updateById(@NotNull CommentReplayDto commentReplayDto) {
        CommentEntity commentEntity = service.getById(commentReplayDto.getCommentId());
        if (Objects.isNull(commentEntity)) {
            throw new BaseRequestException("请正确的填写要回复的信息");
        }
        commentReplayDto.setIsMember(false);
        CommentReplayEntity commentReplayEntity = BeanCopy.convert(commentReplayDto, CommentReplayEntity.class);
        return commentReplayEntity.updateById();
    }
}

