package org.example.modules.comment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.comment.entity.CommentReplayEntity;
import org.example.modules.comment.mapper.CommentReplayMapper;
import org.example.modules.comment.service.CommentReplayService;

/**
 * Created by PanShiFu 2023-07-13 15:19:46
 *
 * @author PanShiFu
 * @date 2023-07-13 15:19:46
 * @Description 产品评价回复表(CommentReplay)表服务实现类
 */
@Service("commentReplayService")
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayMapper, CommentReplayEntity> implements CommentReplayService {

}

