package org.example.modules.comment.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.comment.entity.CommentReplayEntity;

/**
 * Created by PanShiFu 2023-07-13 21:35:11
 *
 * @author PanShiFu
 * @date 2023-07-13 21:35:11
 * @Description 产品评价回复表(CommentReplay)表数据库访问层
 */
@Mapper
public interface CommentReplayMapper extends BaseMapper<CommentReplayEntity> {

}

