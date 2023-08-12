package org.example.modules.comment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.modules.comment.entity.CommentReplayEntity;
import org.example.modules.comment.entity.dto.CommentReplayDto;
import org.example.modules.comment.entity.vo.CommentReplayVo;

import java.io.Serializable;


/**
 * Created by Dou-Fu-10 2023-07-13 21:35:11
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 21:35:11
 * @Description 产品评价回复表(CommentReplay)表服务接口
 */
public interface CommentReplayService extends IService<CommentReplayEntity> {
    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param commentReplayDto 查询实体
     * @return 所有数据
     */
    Page<CommentReplayVo> page(Page<CommentReplayEntity> page, CommentReplayDto commentReplayDto);

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    CommentReplayVo getByCommentReplayId(Serializable id);

    /**
     * 新增数据
     *
     * @param commentReplayDto 实体对象
     * @return 新增结果
     */
    Boolean save(CommentReplayDto commentReplayDto);

    /**
     * 修改数据
     *
     * @param commentReplayDto 实体对象
     * @return 修改结果
     */
    Boolean updateById(CommentReplayDto commentReplayDto);
}
