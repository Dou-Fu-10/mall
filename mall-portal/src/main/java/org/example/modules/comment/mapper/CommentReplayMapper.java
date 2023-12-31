package org.example.modules.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.comment.entity.CommentReplayEntity;

/**
 * Created by Dou-Fu-10 2023-08-10 15:24:17
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 15:24:17
 * @Description 产品评价回复表(CommentReplay)表数据库访问层
 */
@Mapper
public interface CommentReplayMapper extends BaseMapper<CommentReplayEntity> {

}

