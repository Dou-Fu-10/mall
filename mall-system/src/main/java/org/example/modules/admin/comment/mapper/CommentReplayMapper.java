package org.example.modules.admin.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.admin.comment.entity.CommentReplayEntity;

/**
 * Created by Dou-Fu-10 2023-07-13 21:35:11
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 21:35:11
 * @Description 产品评价回复表(CommentReplay)表数据库访问层
 */
@Mapper
public interface CommentReplayMapper extends BaseMapper<CommentReplayEntity> {

}

