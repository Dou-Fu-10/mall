package org.example.modules.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.comment.entity.CommentEntity;
import org.example.modules.comment.entity.dto.CommentDto;
import org.example.modules.comment.entity.vo.CommentVo;
import org.example.modules.comment.mapper.CommentMapper;
import org.example.modules.comment.service.CommentService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by Dou-Fu-10 2023-07-13 21:35:11
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 21:35:11
 * @Description 商品评价表(Comment)表服务实现类
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentEntity> implements CommentService {

    @Override
    public Page<CommentVo> page(Page<CommentEntity> page, CommentDto commentDto) {
        CommentEntity commentEntity = BeanCopy.convert(commentDto, CommentEntity.class);
        Page<CommentEntity> commentEntityPage = page(page, new QueryWrapper<>(commentEntity));
        IPage<CommentVo> commentVoIPage = commentEntityPage.convert(comment -> BeanCopy.convert(comment, CommentVo.class));
        return (Page<CommentVo>) commentVoIPage;
    }

    @Override
    public CommentVo getByCommentId(Serializable id) {
        CommentEntity commentEntity = getById(id);
        return BeanCopy.convert(commentEntity, CommentVo.class);
    }
}

