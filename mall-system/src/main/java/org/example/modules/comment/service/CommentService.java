package org.example.modules.comment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.comment.entity.CommentEntity;
import org.example.modules.comment.entity.dto.CommentDto;
import org.example.modules.comment.entity.vo.CommentVo;

import java.io.Serializable;


/**
 * Created by Dou-Fu-10 2023-07-13 21:35:11
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 21:35:11
 * @Description 商品评价表(Comment)表服务接口
 */
public interface CommentService extends IService<CommentEntity> {
    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param commentDto 查询实体
     * @return 所有数据
     */
    Page<CommentVo> page(Page<CommentEntity> page, CommentDto commentDto);
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    CommentVo getByCommentId(Serializable id);
}
