package org.example.modules.comment.service;

import org.example.modules.comment.entity.CommentReplayEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.modules.comment.entity.dto.CommentReplayDto;
import org.example.modules.comment.entity.vo.CommentReplayVo;

import java.util.List;
import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-08-10 15:24:17
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 15:24:17
 * @Description 产品评价回复表(CommentReplay)表服务接口
 */
public interface CommentReplayService extends IService<CommentReplayEntity> {
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

    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param commentReplayDto 查询实体
     * @return 所有数据
     */
    Page<CommentReplayVo> page(Page<CommentReplayEntity> page, CommentReplayDto commentReplayDto);

    /**
     * 通过评价ids 获取追评
     * @param commentIds 通过评价ids
     * @return 获取追评
     */
    List<CommentReplayVo> getByCommentIds(Set<Long> commentIds);
}
