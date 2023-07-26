package org.example.modules.admin.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.admin.comment.entity.CommentEntity;

/**
 * Created by Dou-Fu-10 2023-07-13 21:35:10
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 21:35:10
 * @Description 商品评价表(Comment)表数据库访问层
 */
@Mapper
public interface CommentMapper extends BaseMapper<CommentEntity> {

}

