package org.example.modules.comment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.comment.entity.CommentEntity;
import org.example.modules.comment.entity.dto.CommentDto;
import org.example.modules.comment.entity.vo.CommentVo;

/**
 * Created by Dou-Fu-10 2023-08-10 15:24:17
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 15:24:17
 * @Description 商品评价表(Comment)表服务接口
 */
public interface CommentService extends IService<CommentEntity> {
    /**
     * 新增数据
     *
     * @param commentDto 实体对象
     * @return 新增结果
     */
    Boolean save(CommentDto commentDto);

    /**
     * 获取评论数据
     *
     * @param commentId 评论id
     * @return 新增结果
     */
    CommentVo getCommentById(Long commentId);

    /**
     * 修改数据
     *
     * @param commentDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(CommentDto commentDto);

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param commentDto 查询实体
     * @return 所有数据
     */
    Page<CommentVo> page(Page<CommentEntity> page, CommentDto commentDto);
}
