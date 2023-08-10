package org.example.modules.comment.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.comment.entity.CommentEntity;

/**
 * Created by Dou-Fu-10 2023-08-10 15:24:17
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 15:24:17
 * @Description 商品评价表(Comment)表数据库访问层
 */
@Mapper
public interface CommentMapper extends BaseMapper<CommentEntity> {

}

