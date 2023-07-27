package org.example.modules.admin.comment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.admin.comment.entity.CommentEntity;
import org.example.modules.admin.comment.mapper.CommentMapper;
import org.example.modules.admin.comment.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * Created by Dou-Fu-10 2023-07-13 21:35:11
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 21:35:11
 * @Description 商品评价表(Comment)表服务实现类
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentEntity> implements CommentService {

}

