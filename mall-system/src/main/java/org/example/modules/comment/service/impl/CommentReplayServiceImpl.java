package org.example.modules.comment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.modules.comment.entity.CommentReplayEntity;
import org.example.modules.comment.mapper.CommentReplayMapper;
import org.example.modules.comment.service.CommentReplayService;
import org.springframework.stereotype.Service;

/**
 * Created by PanShiFu 2023-07-13 21:35:11
 *
 * @author PanShiFu
 * @date 2023-07-13 21:35:11
 * @Description 产品评价回复表(CommentReplay)表服务实现类
 */
@Service("commentReplayService")
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayMapper, CommentReplayEntity> implements CommentReplayService {

}

