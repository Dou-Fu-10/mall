package org.example.modules.comment.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.comment.entity.CommentEntity;

/**
 * Created by PanShiFu 2023-07-13 21:35:10
 *
 * @author PanShiFu
 * @date 2023-07-13 21:35:10
 * @Description 商品评价表(Comment)表数据库访问层
 */
@Mapper
public interface CommentMapper extends BaseMapper<CommentEntity> {

}

